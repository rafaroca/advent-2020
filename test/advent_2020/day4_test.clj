(ns advent-2020.day4-test
  (:require [clojure.test :refer :all])
  (:require [advent-2020.day4 :refer :all]))

(def test-passports
  ["ecl:gry pid:860033327 eyr:2020 hcl:#fffffd"
   "byr:1937 iyr:2017 cid:147 hgt:183cm"
   ""
   "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884"
   "hcl:#cfa07d byr:1929"
   ""
   "hcl:#ae17e1 iyr:2013"
   "eyr:2024"
   "ecl:brn pid:760753108 byr:1931"
   "hgt:179cm"
   ""
   "hcl:#cfa07d eyr:2025 pid:166559648"
   "iyr:2011 ecl:brn hgt:59in"])

(deftest should-parse-input-into-passports
  (is (= 4 (count (parse-input-to-passports test-passports)))))

(deftest should-parse-line-to-partial-passport
  (is (= {:ecl "gry" :pid "860033327" :eyr "2020" :hcl "#fffffd"}
         (line-to-partial-passport (first test-passports)))))

(deftest should-parse-attribute
  (is (= {:ecl "gry"} (parse-attribute "ecl:gry"))))

(deftest should-parse-lines-to-passport
  (is (= [{:ecl "gry" :pid "860033327" :eyr "2020" :hcl "#fffffd" :byr "1937" :iyr "2017" :cid "147" :hgt "183cm"}]
         (parse-input-to-passports (take 2 test-passports)))))

(deftest should-recognise-invalid-passport
  (is (= false
         (contains-all-attributes? {:iyr "2013" :ecl "amb" :cid "350" :eyr "2023" :pid "028048884" :hcl "#cfa07d" :byr "1929"}))))

(deftest should-count-valid-passports
  (is (= 2 (count-valid-passports-part1 test-passports))))