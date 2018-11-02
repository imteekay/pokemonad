(ns pokemon.core
  (:require [pokemon.pokemons :as pokemons]
            [clojure.string :as string]))

(defn visualize-pokemons-by-type
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
       (visualize-pokemons-by-type type)))

(defn -main []
  (loop [types    (pokemons/types pokemons/pokedex)
         pokemons pokemons/pokedex]
    (when (not-empty types)
      (let [current-type (first types)]
        (println (pokemons-by-type current-type pokemons)))
      (recur (rest types) pokemons)))

  (println)

  (println
    (str
      "All types: "
      (string/join
        ", "
        (pokemons/types pokemons/pokedex)))))
