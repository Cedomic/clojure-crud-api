(ns clojure-crud-api.repository.artists
  (:require [clojure.java.jdbc :as sql]
            [clojure-crud-api.repository.migrations :refer [db]]))

(defn createArtist [artist]
  (->
   (sql/insert! db :artists artist)
   (first)))

(defn getAllArtists []
  (into [] (sql/query db ["select * from artists order by id desc limit 1000"])))

(defn getArtist [id]
  (first (sql/query db ["select * from artists where id = ?" id])))

(defn updateArtist [id artist]
  (sql/update! db :artists artist ["id = ?" id])
  (getArtist id))

(defn deleteArtist [id]
  (sql/delete! db :artists ["id = ?" id])
  [])