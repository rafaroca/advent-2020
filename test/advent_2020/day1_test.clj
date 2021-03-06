(ns advent-2020.day1-test
  (:require [clojure.test :refer :all])
  (:require [advent-2020.day1 :refer :all]))

(def input ["1721" "979" "366" "299" "675" "1456"])

(deftest test-sum2020-part1
  "Should return the 2-tuple out of the input which sums to 2020"
  (is (= [1721 299] (sum2020 2 input))))

(deftest test-product2020-part1
  (is (= 514579 (product2020 [1721 299]))))

(deftest test-sum2020-part2
  "Should return the 3-tuple out of the input which sums to 2020"
  (is (= [979 366 675] (sum2020 3 input))))

(deftest test-product2020-part2
  (is (= 241861950 (product2020 [979 366 675]))))
