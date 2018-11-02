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

(defn initialize-types
  [result current]
  (assoc result current []))

(defn populate-types-with-pokemons
  [result current]
  (assoc
    result
    (:type current)
    (conj (get result (:type current)) (:name current))))

(defn -main []
  (loop [types    (pokemons/types pokemons/pokedex)
         pokemons pokemons/pokedex]
    (when (not-empty types)
      (let [current-type (first types)]
        (println (pokemons-by-type current-type pokemons)))
      (recur (rest types) pokemons)))

  (println)

  (println
    (string/join
      "\n"
      (let [pokemons-by-type (group-by :type pokemons/pokedex)]
        (for [[type pokemons] pokemons-by-type]
          (str
            type
            ": "
            (clojure.string/join
              ", "
              (map :name pokemons)))))))

  (println)

  (println
    (reduce
      populate-types-with-pokemons
      (reduce initialize-types {} (pokemons/types pokemons/pokedex))
      pokemons/pokedex))

  (println)

  (println
    (str
      "All types: "
      (string/join
        ", "
        (pokemons/types pokemons/pokedex)))))
