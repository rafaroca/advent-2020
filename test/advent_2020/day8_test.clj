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

(def parsed-test-input (parse-instruction-txt-lines test-input))

(deftest parse-input-should-parse-correctly
  (is (= {:op "acc" :arg 1}
         (second parsed-test-input)))
  (is (= {:op "acc" :arg -99})
      (get parsed-test-input 5)))

(deftest find-acc-at-infinite-loop-should-find-correct-acc
  (is (= 5 (find-acc-at-infinite-loop test-input))))

(deftest should-calculate-accumulator
  (is (= (execute {:op "acc" :arg 2} 5)
         {:next-acc 7 :ip-offset 1}))
  (is (= (execute {:op "nop" :arg 99} 5)
         {:next-acc 5 :ip-offset 1}))
  (is (= (execute {:op "jmp" :arg 4} 5)
         {:next-acc 5 :ip-offset 4})))

(deftest count-jmp-and-nop-test
  (is (= 4 (count-jmp-and-nop parsed-test-input))))

(deftest flip-nth-jmp-nop-test
  (is (= 9 (count (flip-nth-jmp-nop 0 parsed-test-input))))
  (is (= "jmp" (:op (first (flip-nth-jmp-nop 0 parsed-test-input)))))
  (is (= "nop" (:op (second (flip-nth-jmp-nop 1 parsed-test-input))))))

(deftest swap-jmp-nop-test
  (is (= {:op nil :arg 2}
         (swap-jmp-nop {:op "acc" :arg 2})))
  (is (= {:op "jmp" :arg 2}
         (swap-jmp-nop {:op "nop" :arg 2})))
  (is (= {:op "nop" :arg 2}
         (swap-jmp-nop {:op "jmp" :arg 2}))))

(deftest contains-nil-op?-test
  (is (true? (contains-nil-op? [{:op "jmp" :arg 3} {:op nil :arg 2}])))
  (is (false? (contains-nil-op? [{:op "jmp" :arg 3} {:op "jmp" :arg 2}]))))
