(ns pokemon.core
  (:require [pokemon.pokemons :as pokemons]
            [clojure.pprint :as pprint]
            [clojure.string :as string]))

(defn -main []
  (println
    (string/join "\n\n"
      (map
        (fn [type]
          (str
            "\""
            type
            " pokemons\": "
            (string/join
              ", "
              (map
                #(:name %)
                (filter
                  #(= type (:type %))
                  pokemons/pokedex)))))
        (pokemons/types pokemons/pokedex))))

  (println)

  (println
    (str
      "All types: "
      (string/join
        ", "
        (pokemons/types pokemons/pokedex)))))
