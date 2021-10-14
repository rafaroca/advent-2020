(ns advent-2020.day8
  (:require [clojure.string :as str]))

(defn parse-instruction-txt-lines [instruction-txt-lines]
  (->> instruction-txt-lines
       (filter not-empty)
       (map #(str/split % #" "))
       (map #(hash-map :op (first %) :arg (Integer/parseInt (second %))))))

(defn execute [instr acc]
  (let [{:keys [op arg]} instr
        next-acc (case op
                   "nop" acc
                   "jmp" acc
                   "acc" (+ acc arg))
        next-op (case op
                  "nop" 1
                  "acc" 1
                  "jmp" arg)]
    {:next-acc next-acc :ip-offset next-op}))

(defn find-acc-at-loop-or-end [instructions]
  (let [input-len (count instructions)]
    (loop [acc 0
           ip 0
           visited #{}]
      (if (or (contains? visited ip)
              (>= ip input-len))
        {:acc acc :ip ip :ip-past-last-instr (>= ip input-len)}
        (let [next-op (nth instructions ip)
              {:keys [next-acc ip-offset]} (execute next-op acc)]
          (recur next-acc (+ ip ip-offset) (conj visited ip)))))))

(defn find-acc-at-infinite-loop [instruction-txt-lines]
  (let [ops (parse-instruction-txt-lines instruction-txt-lines)]
    (find-acc-at-loop-or-end ops)))

(defn count-jmp-and-nop [instr]
  (->> instr
       (filter #(or (= (:op %) "jmp")
                    (= (:op %) "nop")))
       count))

(defn swap-jmp-nop [instr]
  (let [op (:op instr)
        swapped-op (case op
                     "nop" "jmp"
                     "jmp" "nop"
                     nil)]
    (assoc instr :op swapped-op)))

(defn flip-nth-jmp-nop [n instructions]
  (let [instruction (nth instructions n)
        swapped-instruction (swap-jmp-nop instruction)
        head-instructions (into [] (take n instructions))
        rest-instructions (drop (inc n) instructions)]
    (concat (conj head-instructions swapped-instruction) rest-instructions)))

(defn contains-nil-op? [instructions]
  (->> instructions
       (not-every? #(some? (:op %)))))

(defn generate-all-inputs-with-every-possible-jmp-nop-flipped [input]
  (->> input
       (repeat (count input))
       (map-indexed flip-nth-jmp-nop)
       (remove contains-nil-op?)))

(defn acc-for-correctly-flipped-jmp-nop [instr-txt-lines]
  (let [instructions (parse-instruction-txt-lines instr-txt-lines)
        all-flipped-inputs (generate-all-inputs-with-every-possible-jmp-nop-flipped instructions)]
    (->> all-flipped-inputs
         (map find-acc-at-loop-or-end)
         (drop-while #(false? (:ip-past-last-instr %)))
         (first))))
