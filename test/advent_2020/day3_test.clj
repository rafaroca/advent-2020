(ns advent-2020.day3-test
  (:require [clojure.test :refer :all])
  (:require [advent-2020.day3 :refer :all]))

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
  (is (= 7 (tree-hit-count [{:right 3 :down 1}] test-forest))))

(deftest is-tree-test
  (is (= true (tree? 14 (first test-forest))))
  (is (= false (tree? 0 (first test-forest)))))

(deftest tree-hit-count-test-part2
  (is (= 336 (tree-hit-count sledding-patterns test-forest))))

(deftest tree-hit-count-test-part2
  (is (= 2 (tree-hit-count [{:right 1 :down 2}] test-forest))))
