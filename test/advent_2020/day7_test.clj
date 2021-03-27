(ns advent-2020.day7-test
  (:require [clojure.test :refer :all])
  (:require [advent-2020.day7 :refer :all]))

(def test-input [
                 "light red bags contain 1 bright white bag, 2 muted yellow bags."
                 "dark orange bags contain 3 bright white bags, 4 muted yellow bags."
                 "bright white bags contain 1 shiny gold bag."
                 "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags."
                 "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags."
                 "dark olive bags contain 3 faded blue bags, 4 dotted black bags."
                 "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags."
                 "faded blue bags contain no other bags."
                 "dotted black bags contain no other bags."
                 ""])

(deftest should-parse-bags-line
  (is (= "bright white" (:parent-color (parse-bags-line (get test-input 2)))))
  (is (= [{:count 1 :bag-color "shiny gold"}] (:contained (parse-bags-line (get test-input 2)))))
  (is (= "muted yellow" (:parent-color (parse-bags-line (get test-input 3)))))
  (is (= [{:count 2 :bag-color "shiny gold"} {:count 9 :bag-color "faded blue"}] (:contained (parse-bags-line (get test-input 3))))))

(deftest should-find-all-containing-colors
  (is (= #{"muted yellow" "light red" "dark orange" "bright white"} (find-all-containing-bags "shiny gold" (map parse-bags-line test-input)))))

(deftest should-count-possible-outer-bags
  (is (= 4 (count-all-bags-possibly-containing "shiny gold" test-input))))
