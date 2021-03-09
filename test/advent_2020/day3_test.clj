(ns advent-2020.day3-test
  (:require [clojure.test :refer :all])
  (:require [advent-2020.day3 :refer [tree-hit-count-part1 tree?]]))

(def test-forest
  ["..##......."
   "#...#...#.."
   ".#....#..#."
   "..#.#...#.#"
   ".#...##..#."
   "..#.##....."
   ".#.#.#....#"
   ".#........#"
   "#.##...#..."
   "#...##....#"
   ".#..#...#.#"])

(deftest tree-hit-count-test
  (is (= 7 (tree-hit-count-part1 test-forest))))

(deftest is-tree-tist
  (is (= true (tree? 14 (first test-forest))))
  (is (= false (tree? 0 (first test-forest)))))