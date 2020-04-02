(ns clojure-crud-api.controllers.songs
  (:require [clojure-crud-api.repository.songs :as songs]
            [compojure.core :refer [defroutes GET POST PUT DELETE]]
            [compojure.coercions :refer [as-int]]
            [ring.util.response :refer [response]]))

(defn getAllSongs [] (songs/getAllSongs))

(defn getSongsFromArtist [artistId] (songs/getSongsByArtistId artistId))

(defn getSong [id] (songs/getSong id))

(defn createSong [song] (songs/createSong song))

(defn updateSong [id song] (songs/updateSong id song))

(defn deleteSong [songId] (songs/deleteSong songId))


(defroutes routes
  (GET "/songs" []
    (response
     (getAllSongs)))
  (GET "/songs/artist/:artistId{[0-9]+}" [artistId :<< as-int]
    (response
     (getSongsFromArtist artistId)))
  (GET "/songs/:songId{[0-9]+}" [songId :<< as-int]
    (response
     (getSong songId)))
  (POST "/songs" request
    (response
     (createSong (request :body))))
  (PUT "/songs/:songId{[0-9]+}" [songId :<< as-int :as request]
    (response
     (updateSong songId (request :body))))
  (DELETE "/songs/:songId{[0-9]+}" [songId :<< as-int]
    (response
     (deleteSong songId))))