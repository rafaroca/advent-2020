(ns advent-2020.day8
  (:require [clojure.string :as str]))

(defn parse-input [input]
  (->> input
       (filter not-empty)
       (map #(str/split % #" "))
       (map #(hash-map :op (first %) :arg (Integer/parseInt (second %))))))

(defn execute [op-count acc]
  (let [{:keys [op arg]} op-count
        next-acc (case op
                   "nop" acc
                   "jmp" acc
                   "acc" (+ acc arg))
        next-op (case op
                  "nop" 1
                  "acc" 1
                  "jmp" arg)]
    {:next-acc next-acc :ip-offset next-op}))

(defn find-acc-at-infinite-loop [input]
  (let [ops (parse-input input)]
    (loop [acc 0
           ip 0
           visited #{}]
      (if (contains? visited ip)
        acc
        (let [next-op (nth ops ip)
              {:keys [next-acc ip-offset]} (execute next-op acc)]
          (recur next-acc (+ ip ip-offset) (conj visited ip)))))))

