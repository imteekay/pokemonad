(ns pokemon.core
  (:require [pokemon.pokemons :as pokemons]
            [clojure.pprint :as pprint]
            [clojure.string :as string]))

(defn -main []
  (pprint/pprint pokemons/pokedex)

  (println
    (str
      "Psychic pokemons: "
      (string/join
        ", "
        (map
          #(:name %)
          (filter
            #(= "Psychic" (:type %))
            pokemons/pokedex))))))
