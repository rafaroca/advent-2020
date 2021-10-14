(ns advent-2020.core
  (:require [advent-2020.day1 :as day1])
  (:require [advent-2020.day2 :as day2])
  (:require [advent-2020.day3 :as day3])
  (:require [advent-2020.day4 :as day4])
  (:require [advent-2020.day5 :as day5])
  (:require [advent-2020.day6 :as day6])
  (:require [advent-2020.day7 :as day7])
  (:require [advent-2020.day8 :as day8])
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
        input2 (read-input "input_day2.txt")
        input3 (read-input "input_day3.txt")
        input4 (read-input "input_day4.txt")
        input5 (read-input "input_day5.txt")
        input6 (read-input "input_day6.txt")
        input7 (read-input "input_day7.txt")
        input8 (read-input "input_day8.txt")
        day1-part1-tuple (day1/sum2020 2 input1)
        day1-part2-tuple (day1/sum2020 3 input1)
        day1-part1-solution (day1/product2020 day1-part1-tuple)
        day1-part2-solution (day1/product2020 day1-part2-tuple)]
    (println "Day 1: tuple " day1-part1-tuple " product " day1-part1-solution)
    (println "Day 1 part 2: tuple " day1-part2-tuple " product " day1-part2-solution)
    (println "Day 2: count " (day2/count-valid-passwords-part1 input2))
    (println "Day 2 part 2: " (day2/count-valid-passwords-part2 input2))
    (println "Day 3: " (day3/tree-hit-count [{:right 3 :down 1}] input3) " part 2: " (day3/tree-hit-count day3/sledding-patterns input3))
    (println "Day 4: " (day4/count-valid-passports-part1 input4) " part 2: " (day4/count-valid-passports-part2 input4))
    (println "Day 5: " (day5/max-seat-id input5) " part 2: " (day5/find-my-seat input5))
    (println "Day 6: " (day6/sum-distinct-group-answers input6) " part 2: " (day6/count-intersected-answers input6))
    (println "Day 7: " (day7/count-all-bags-possibly-containing "shiny gold" input7) " part 2: " (day7/count-all-contained-bags "shiny gold" input7))
    (println "Day 8: " (:acc (day8/find-acc-at-infinite-loop input8)) " part 2: " (:acc (day8/acc-for-correctly-flipped-jmp-nop input8)))))
