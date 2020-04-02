(ns clojure-crud-api.repository.songs
  (:require [clojure.java.jdbc :as sql]
            [clojure-crud-api.repository.migrations :refer [db]]))

(defn createSong [song]
  (->
   (sql/insert! db :songs song)
   (first)))

(defn getAllSongs []
  (into []
        (sql/query db ["select * from songs order by id desc limit 1000"])))

(defn getSongsByArtistId [artistId]
  (into []
        (sql/query db ["select * from songs where artist_id = ? order by id desc limit 1000", artistId])))

(defn getSong [id]
  (first
   (sql/query db ["select * from songs where id = ?" id])))

(defn updateSong [id, song]
  (sql/update! db :songs song ["id = ?" id])
  (getSong id))

(defn deleteSong [id]
  (sql/delete! db :songs ["id = ?" id])
  [])