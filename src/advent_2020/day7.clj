(ns advent-2020.day7
  (:require [clojure.string :as str]
            [clojure.set :as set]
            [clojure.spec.alpha :as s]))

(s/def ::bag (s/keys :req [::parent-color ::contained]))

(defn de-bag [s]
  (clojure.string/replace s #" bag[s,\.]*" ""))

(defn parse-counted-bags [counted-bag]
  (let [[_ count color] (re-matches #"(\d+) ([\w\s]+)" counted-bag)]
    (if count
      {:count (Integer/parseInt count) :bag-color color}
      nil)))


(defn parse-bags-line [line]
  (let [[parent-bags contained-bags-str] (str/split line #" contain ")
        parent (de-bag parent-bags)
        contained (->> (str/split (str contained-bags-str) #", |\.")
                       (map de-bag)
                       (map parse-counted-bags)
                       (filter some?))]
    {:parent-color parent :contained contained}))

(defn intersection-contained-bag-matching-colors [colors bag]
  (->> bag
       :contained
       (map :bag-color)
       set
       (set/intersection (set colors))))

(defn rec-find-all-containing-bags [colors parsed-bags]
  (let [parent-colors (->> parsed-bags
                           (filter #(seq (intersection-contained-bag-matching-colors colors %)))
                           (map :parent-color)
                           set)
        all-combined-colors (set/union parent-colors colors)]
    (println "colors " colors " parent colors " parent-colors)
    (if (= colors all-combined-colors)
      colors
      (recur all-combined-colors parsed-bags))))

(defn find-all-containing-bags [color parsed-bags]
  (let [parent-bags-including-start-bag (rec-find-all-containing-bags #{color} parsed-bags)]
    (disj (set parent-bags-including-start-bag) color)))

(defn count-all-bags-possibly-containing [color input]
  (count (find-all-containing-bags color (map parse-bags-line input))))

(defn rec-count-all-contained-bags [color parsed-bags]
  (let [bag-list (filter #(= (:parent-color %) color) parsed-bags)
        bag-content (-> bag-list
                        first
                        :contained)]
    (if (empty? bag-content)
      1
      (reduce + 1 (map #(* (:count % 1) (rec-count-all-contained-bags (:bag-color %) parsed-bags)) bag-content)))))


(defn count-all-contained-bags [color input]
  (dec (rec-count-all-contained-bags color (map parse-bags-line input)))) ; dec to exclude the outer bag