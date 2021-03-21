(ns advent-2020.day6)

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