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

(def test-passports-part2-invalid
  ["eyr:1972 cid:100"
   "hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926"
   ""
   "pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980"
   "hcl:623a2f"
   ""
   "iyr:2019"
   "hcl:#602927 eyr:1967 hgt:170cm"
   "ecl:grn pid:012533040 byr:1946"
   ""
   "hcl:dab227 iyr:2012"
   "ecl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277"
   ""
   "hgt:59cm ecl:zzz"
   "eyr:2038 hcl:74454a iyr:2023"
   "pid:3556412378 byr:2007"])


(def test-passports-part2-valid
  ["pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980"
   "hcl:#623a2f"
   ""
   "eyr:2029 ecl:blu cid:129 byr:1989"
   "iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm"
   ""
   "hcl:#888785"
   "hgt:164cm byr:2001 iyr:2015 cid:88"
   "pid:545766238 ecl:hzl"
   "eyr:2022"
   ""
   "iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719"])

(deftest should-parse-input-into-passports
  (is (= 4 (count (parse-input-to-passports test-passports)))))

(deftest should-parse-line-to-partial-passport
  (is (= {:ecl "gry" :pid "860033327" :eyr "2020" :hcl "#fffffd"}
         (line-to-partial-passport (first test-passports)))))

(deftest should-parse-attribute
  (is (= {:ecl "gry"} (parse-attribute-str "ecl:gry"))))

(deftest should-parse-lines-to-passport
  (is (= [{:ecl "gry" :pid "860033327" :eyr "2020" :hcl "#fffffd" :byr "1937" :iyr "2017" :cid "147" :hgt "183cm"}]
         (parse-input-to-passports (take 2 test-passports)))))

(deftest should-recognise-invalid-passport
  (is (false?
        (contains-all-required-attributes? {:iyr "2013" :ecl "amb" :cid "350" :eyr "2023" :pid "028048884" :hcl "#cfa07d" :byr "1929"}))))

(deftest should-count-valid-passports
  (is (= 2 (count-valid-passports-part1 test-passports))))

(deftest should-parse-height
  (is (= {:height 159 :unit "cm"} (parse-height "159cm")))
  (is (= {:height 159 :unit "in"} (parse-height "159in")))
  (is (nil? (parse-height "180")))
  (is (nil? (parse-height "cm")))
  (is (nil? (parse-height "12cm2"))))

(deftest should-parse-eyecolor
  (is (= :amb (parse-eye-color "amb")))
  (is (nil? (parse-eye-color "purple"))))

(deftest should-validate-color
  (is (true? (validate-color "#aabbff")))
  (is (false? (validate-color "#YYZZWW")))
  (is (false? (validate-color "112233"))
      "Color without hash sign is invalid"))

(deftest should-validate-passports
  (is (= 0 (count-valid-passports-part2 test-passports-part2-invalid)))
  (is (= 4 (count-valid-passports-part2 test-passports-part2-valid))))
