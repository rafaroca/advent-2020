(ns advent-2020.day2-test
  (:require [clojure.test :refer :all])
  (:require [advent-2020.day2 :refer :all]))

(def input ["1-3 a: abcde"
            "1-3 b: cdefg"
            "2-9 c: ccccccccc"])

(def valid-policy {:first 1 :second 3 :char \a :password "abcde"})
(def invalid-policy {:first 1 :second 3 :char \b :password "cdefg"})

(deftest should-extract-policy
  (is (= valid-policy
         (extract-policy (first input)))))

(deftest should-validate-policy-part1
  (is (= true (validate-password-part1 valid-policy))))

(deftest should-not-validate-invalid-policy-part1
  (is (= false (validate-password-part1 invalid-policy))))

(deftest should-validate-policy-part2
  (is (= true (validate-password-part2 valid-policy))))

(deftest should-validate-password-based-on-policy
  (is (= 2 (count-valid-passwords-part1 input))))

