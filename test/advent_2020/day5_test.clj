(ns advent-2020.day5-test
  (:require [clojure.test :refer :all])
  (:require [advent-2020.day5 :refer :all]))

(def test-boarding-passes
  ["BFFFBBFRRR"
   "FFFBBBFRRR"
   "BBFFBBFRLL"])

(deftest should-calculate-seat-no-from-boarding-pass
  """
  BFFFBBFRRR: row 70, column 7, seat ID 567.
  FFFBBBFRRR: row 14, column 7, seat ID 119.
  BBFFBBFRLL: row 102, column 4, seat ID 820.
  multiply the row by 8, then add the column
  """
  (is (= 567 (seat-id-from-boarding-pass "BFFFBBFRRR")))
  (is (= 119 (seat-id-from-boarding-pass "FFFBBBFRRR")))
  (is (= 820 (seat-id-from-boarding-pass "BBFFBBFRLL"))))

(deftest should-map-pass-to-binary
  (is (= "0001110111" (map-boarding-pass-to-binary "FFFBBBFRRR"))))

(deftest should-find-max-seat-id
  (is (= 820 (max-seat-id test-boarding-passes))))
