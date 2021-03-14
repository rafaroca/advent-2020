(ns advent-2020.day4
  (:require [clojure.string :as str]))

(defn parse-attribute [attr-string]
  (let [[attr-name attr-value] (str/split attr-string #":")]
    {(keyword attr-name) attr-value}))

(defn line-to-partial-passport [line]
  (let [attributes (str/split line #" ")]
    (->> attributes
         (map parse-attribute)
         (into {}))))

(defn parse-input-to-passports [input]
  (loop [passports []
         current-passport {}
         lines input]
    (if (nil? lines)
      (cons current-passport passports)
      (let [[current-line & lines-rs] lines]
        (if (empty? current-line)
          (recur (cons current-passport passports)
                 {}
                 lines-rs)
          (recur passports
                 (into current-passport (line-to-partial-passport current-line))
                 lines-rs))))))

(defn contains-all-attributes? [passport]
  (every? passport [:byr :iyr :eyr :hgt :hcl :ecl :pid]))

(defn count-valid-passports-part1 [input]
  (->> input
      parse-input-to-passports
      (filter contains-all-attributes?)
      count))