(ns advent-2020.day8-test
  (:require [clojure.test :refer :all])
  (:require [advent-2020.day8 :refer :all]))

(def test-input ["nop +0"
                 "acc +1"
                 "jmp +4"
                 "acc +3"
                 "jmp -3"
                 "acc -99"
                 "acc +1"
                 "jmp -4"
                 "acc +6"
                 ""])

(deftest parse-input-should-parse-correctly
  (is (= {:op "acc" :arg 1}
         (second (parse-input test-input))))
  (is (= {:op "acc" :arg -99})
      (get (parse-input test-input) 5)))

(deftest find-acc-at-infinite-loop-should-find-correct-acc
  (is (= 5 (find-acc-at-infinite-loop test-input))))

(deftest should-calculate-accumulator
  (is (= (execute {:op "acc" :arg 2} 5)
         {:next-acc 7 :ip-offset 1}))
  (is (= (execute {:op "nop" :arg 99} 5)
         {:next-acc 5 :ip-offset 1}))
  (is (= (execute {:op "jmp" :arg 4} 5)
         {:next-acc 5 :ip-offset 4})))