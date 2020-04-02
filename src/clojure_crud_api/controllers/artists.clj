(ns clojure-crud-api.controllers.artists
  (:require [clojure-crud-api.repository.artists :as artists]
            [compojure.core :refer [defroutes GET POST PUT DELETE]]
            [compojure.coercions :refer [as-int]]
            [ring.util.response :refer [response]]))

(defn getAllArtists [] (artists/getAllArtists))

(defn getArtist [id] (artists/getArtist id))

(defn createArtist [artist] (artists/createArtist artist))

(defn updateArtist [id artist] (artists/updateArtist id artist))

(defn deleteArtist [id] (artists/deleteArtist id))


(defroutes routes
  (GET "/artists" []
    (response
     (getAllArtists)))
  (GET "/artists/:artistId{[0-9]+}" [artistId :<< as-int]
    (response
     (getArtist artistId)))
  (POST "/artists" request
    (response
     (createArtist (request :body))))
  (PUT "/artists/:artistId{[0-9]+}" [artistId :<< as-int :as request]
    (response
     (updateArtist artistId (request :body))))
  (DELETE "/artists/:artistId{[0-9]+}" [artistId :<< as-int]
    (response
     (deleteArtist artistId))))