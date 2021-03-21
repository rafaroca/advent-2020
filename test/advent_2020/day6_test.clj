(ns advent-2020.day6-test
  (:require [clojure.test :refer :all])
  (:require [advent-2020.day6 :refer :all]))

(def test-lines ["abc" "" "a" "b" "c" "" "ab" "ac" "" "a" "a" "a" "a" "" "b"])

(deftest to-answer-group-test
  (is (= 5 (count (distinct-group-answers test-lines))))
  (is (= '(#{\b} #{\a} #{\a \b \c} #{\a \b \c} #{\a \b \c})
         (distinct-group-answers test-lines))))

