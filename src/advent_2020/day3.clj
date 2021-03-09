(ns advent-2020.day3)

(defn tree? [x line]
  (let [n (count line)
        line-char (get line (mod x n))]
    (= \# line-char)))

(defn ride-through-forest [index line]
  (tree? (* index 3) line))

(defn tree-hit-count-part1 [forest]
  (count (->> forest
             (map-indexed ride-through-forest)
             (filter true?))))