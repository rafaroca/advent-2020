(ns advent-2020.day5
  (:require [clojure.string :as str]
            [clojure.set :as set]))

(defn map-boarding-pass-to-binary [pass]
  (-> pass
      (str/replace #"F|L" "0")
      (str/replace #"B|R" "1")))

(defn seat-id-from-boarding-pass [pass]
  (let [bin-pass (map-boarding-pass-to-binary pass)]
    (Integer/parseInt bin-pass 2)))

(defn max-seat-id [input]
  (->> input
       (map seat-id-from-boarding-pass)
       (reduce max)))

(defn seq-difference [seq1 seq2]
  (set/difference (set seq1) (set seq2)))

(defn find-my-seat [input]
  (->> input
       (map seat-id-from-boarding-pass)
       (seq-difference (range 1 1024))
       (filter #(> % 100))
       (filter #(< % 800))
       first))