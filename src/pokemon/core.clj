(ns pokemon.core
  (:require [pokemon.pokemons :as pokemons]
            [pokemon.visualizers :as visualizers]
            [clojure.pprint :as pprint]
            [clojure.string :as string]))

(defn -main []
  (visualizers/show-type-with-pokemons pokemons/pokedex)
  (println)
  (visualizers/show-pokemons-grouped-by-type pokemons/pokedex)
  (println)
  (visualizers/show-map-type-with-list-of-pokemons pokemons/pokedex)
  (println)
  (visualizers/show-pokemons-evolutions pokemons/pokedex)
  (println)
  (visualizers/show-all-types pokemons/pokedex))
