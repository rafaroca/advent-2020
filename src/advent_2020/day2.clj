(ns advent-2020.day2
  (:require [clojure.string :as str]))

(defn extract-policy [line]
  (let [[min-max char _ password] (str/split line #"\s|:")
        [min max] (map #(Integer/parseInt %) (str/split min-max #"-"))]
    {:first min :second max :char (first char) :password password}))

(defn validate-password-part1 [policy]
  (let [cnt (count (filter #(= (:char policy) %) (:password policy)))]
    (and (<= (:first policy) cnt)
         (>= (:second policy) cnt))))

(defn validate-password-part2 [policy]
  (let [first-index (dec (:first policy))
        char-at-first (get (:password policy) first-index)
        second-index (dec (:second policy))
        char-at-second (get (:password policy) second-index)]
    (= 1 (->> [char-at-first char-at-second]
              (filter #(= (:char policy) %))
              count))))

(defn count-valid-passwords-part1 [input]
  (->> input
       (map extract-policy)
       (map validate-password-part1)
       (filter true?)
       count))

(defn count-valid-passwords-part2 [input]
  (->> input
       (map extract-policy)
       (map validate-password-part2)
       (filter true?)
       count))
