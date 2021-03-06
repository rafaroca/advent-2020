(ns advent-2020.day1
  (:require [clojure.math.combinatorics :as combo]))

(defn is-tuple-sum2020 [tuple]
  (= (apply + tuple) 2020))

(defn sum2020 [tuple-length input]
  (let [input-as-numbers (map #(Integer/parseInt %) input)
        all-combinations (combo/combinations input-as-numbers tuple-length)]
    (->> all-combinations
         (filter is-tuple-sum2020)
         flatten)))

(defn product2020 [tuple]
  (apply * tuple))
