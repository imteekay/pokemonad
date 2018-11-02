(ns pokemon.core
  (:require [pokemon.pokemons :as pokemons]
            [clojure.pprint :as pprint]
            [clojure.string :as string]))

(defn type-with-pokemons
  [type pokemons-string]
  (str
    "\""
    type
    " pokemons\": "
    pokemons-string))

(defn pokemons-by-type
  [type pokemons]
  (->> pokemons
       (filter #(= type (:type %)))
       (map :name)
       (string/join ", ")
       (type-with-pokemons type)))

(defn initialize-types
  [result current]
  (assoc result current []))

(defn populate-types-with-pokemons
  [result current]
  (assoc
    result
    (:type current)
    (conj (get result (:type current)) (:name current))))

(defn show-type-with-pokemons
  [pokemons]
  (loop [types    (pokemons/types pokemons)
         pokemons pokemons]
    (when (not-empty types)
      (let [current-type (first types)]
        (println (pokemons-by-type current-type pokemons)))
      (recur (rest types) pokemons))))

(defn show-pokemons-grouped-by-type
  [pokemons]
  (println
    (string/join
      "\n"
      (let [pokemons-by-type (group-by :type pokemons)]
        (for [[type pokemons] pokemons-by-type]
          (str
            type
            ": "
            (clojure.string/join
              ", "
              (map :name pokemons))))))))

(defn map-type-with-list-of-pokemons
  [pokemons]
  (pprint/pprint
    (reduce
      populate-types-with-pokemons
      (reduce initialize-types {} (pokemons/types pokemons))
      pokemons)))

(defn show-all-types
  [pokemons]
  (println
    (str
      "All types: "
      (string/join
        ", "
        (pokemons/types pokemons)))))

(defn -main []
  (show-type-with-pokemons pokemons/pokedex)
  (println)
  (show-pokemons-grouped-by-type pokemons/pokedex)
  (println)
  (map-type-with-list-of-pokemons pokemons/pokedex)
  (println)
  (show-all-types pokemons/pokedex))
