(ns advent-2020.core
  (:require [advent-2020.day1 :as day1])
  (:gen-class))

(defn read-input [file-name]
  (-> file-name
      clojure.java.io/resource
      slurp
      clojure.string/split-lines))

(defn -main
  "Calculate the correct output for all days."
  [& _]
  (let [input1 (read-input "input_day1.txt")
        day1-part1-tuple (day1/sum2020 2 input1)
        day1-part2-tuple (day1/sum2020 3 input1)
        day1-part1-solution (day1/product2020 day1-part1-tuple)
        day1-part2-solution (day1/product2020 day1-part2-tuple)]
    (println "Day 1: tuple " day1-part1-tuple " product " day1-part1-solution)
    (println "Day 1 part 2: tuple " day1-part2-tuple " product " day1-part2-solution)))
