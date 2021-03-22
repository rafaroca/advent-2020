(ns advent-2020.day6
  (:require [clojure.set :as set]))

(defn group-answer [[current-group & rest-groups :as groups] answer]
  (if (seq answer)
    (cons (apply str answer current-group) rest-groups)
    (cons () groups)))

(defn distinct-group-answers [answer-lines]
  (->> answer-lines
       (reduce group-answer ())
       (map distinct)
       (map #(apply conj #{} %))))

(defn sum-distinct-group-answers [answer-lines]
  (->> (distinct-group-answers answer-lines)
       (map count)
       (reduce +)))

(defn group-answer-after-emtpy-line [[current-group & rest-groups :as groups] answer]
  (let [answer-as-set (apply conj #{} answer)]
    (if (seq answer)
        (cons (cons answer-as-set current-group) rest-groups)
        (cons () groups))))

(defn intersection-group-answers [answer-lines]
  (->> answer-lines
       (reduce group-answer-after-emtpy-line ())
       (map #(apply set/intersection %))))

(defn count-intersected-answers [intersected-answers]
  (->> (intersection-group-answers intersected-answers)
       (map count)
       (reduce +)))
