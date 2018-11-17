(ns pokemon.visualizers
  (:require [pokemon.pokemons :as pokemons]
            [clojure.pprint :as pprint]
            [clojure.string :as string]))

(defn show-type-with-pokemons
  [pokemons]
  (loop [types    (pokemons/types pokemons)
         pokemons pokemons]
    (when (not-empty types)
      (let [current-type (first types)]
        (println (pokemons/pokemons-by-type current-type pokemons)))
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

(defn show-map-type-with-list-of-pokemons
  [pokemons]
  (pprint/pprint (pokemons/map-type-with-list-of-pokemons pokemons)))

(defn show-pokemons-evolutions
  [pokemons]
  (pprint/pprint
   (map
    (partial pokemons/evolve pokemons/pokedex)
    pokemons/pokedex)))

(defn show-all-types
  [pokemons]
  (println
   (str
    "All types: "
    (string/join
     ", "
     (pokemons/types pokemons)))))
