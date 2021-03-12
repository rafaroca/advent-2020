(ns advent-2020.day3)

(def sledding-patterns
  [{:right 1 :down 1}
   {:right 3 :down 1}
   {:right 5 :down 1}
   {:right 7 :down 1}
   {:right 1 :down 2}])

(defn tree? [x line]
  (let [n (count line)
        line-char (get line (mod x n))]
    (= \# line-char)))

(defn current-line-ridden? [idx pattern]
  (= 0 (mod idx (:down pattern))))

(defn count-hits-while-sledding [forest pattern]
  (loop [x 0
         hits 0
         idx 0
         [line & forest-rs] forest]
    (if (nil? line)
      hits
      (let [line-ridden (current-line-ridden? idx pattern)
            hit (tree? x line)
            right-inc (if line-ridden
                        (:right pattern)
                        0)]
        (recur (+ x right-inc)
               (+ hits (if (and line-ridden hit)
                         1 0))
               (inc idx)
               forest-rs)))))

(defn tree-hit-count [patterns forest]
  (->> patterns
       (map #(count-hits-while-sledding forest %))
       (reduce *)))

