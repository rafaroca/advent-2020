(ns advent-2020.day4
  (:require [clojure.string :as str]))

(defn parse-attribute-str [attr-string]
  (let [[attr-name attr-value] (str/split attr-string #":")]
    {(keyword attr-name) attr-value}))

(defn line-to-partial-passport [line]
  (let [attributes (str/split line #" ")]
    (->> attributes
         (map parse-attribute-str)
         (into {}))))

(defn parse-input-to-passports [input]
  (reduce (fn [[current-passport & rx :as passports] line]
            (if (seq line)
              (cons (into current-passport (line-to-partial-passport line)) rx)
              (cons () passports)))
    ()
    input))

(defn contains-all-required-attributes? [passport]
  (let [passport-map (into {} passport)]
    (every? passport-map [:byr :iyr :eyr :hgt :hcl :ecl :pid])))

(defn count-valid-passports-part1 [input]
  (->> input
       parse-input-to-passports
       (filter contains-all-required-attributes?)
       count))

(defn parse-number [number-string]
  (try
    (Integer/parseInt number-string)
    (catch Exception _
      nil)))

(defn parse-height [v]
  (let [height-match (re-seq #"^(\d+)(cm|in)$" v)
        [_ height unit] (first height-match)]
    (and height
         unit
         {:height (parse-number height) :unit unit})))

(defn validate-color [v]
  (some? (re-matches #"^#[0-9a-f]{6}" v)))

(defn parse-eye-color [v]
  (if (contains? #{"amb" "blu" "brn" "gry" "grn" "hzl" "oth"} v)
    (keyword v)
    nil))

(defn validate-passport-id [v]
  (some? (re-matches #"^\d{9}$" v)))

(defn parse-attribute [[k v]]
  [k (case k
       :byr (parse-number v)
       :iyr (parse-number v)
       :eyr (parse-number v)
       :hgt (parse-height v)
       :ecl (parse-eye-color v)
       v)])

(defn parse-passport [passport]
  (map parse-attribute passport))

(defn valid-attribute? [[k v]]
  (case k
    :byr (and (>= v 1920)
              (<= v 2002))
    :iyr (and (>= v 2010)
              (<= v 2020))
    :eyr (and (>= v 2020)
              (<= v 2030))
    :hgt (let [height (:height v)]
           (case (:unit v)
             "cm" (and (>= height 150)
                       (<= height 193))
             "in" (and (>= height 59)
                       (<= height 76))
             false))
    :ecl (some? v)
    :pid (validate-passport-id v)
    :hcl (validate-color v)
    true))

(defn valid-passport? [passport]
  (every? valid-attribute? passport))

(defn count-valid-passports-part2 [input]
  (->> input
       parse-input-to-passports
       (filter contains-all-required-attributes?)
       (map parse-passport)
       (filter valid-passport?)
       count))
