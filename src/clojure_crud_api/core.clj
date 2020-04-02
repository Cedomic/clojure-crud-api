(ns clojure-crud-api.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [compojure.core :refer [defroutes]]
            [compojure.route :refer [not-found]]
            [ring.middleware.json :refer [wrap-json-response wrap-json-body]]
            [clojure-crud-api.controllers.artists :as artistsController]
            [clojure-crud-api.controllers.songs :as songsController]
            [clojure-crud-api.repository.migrations :as migrations]))


(defroutes routes
  artistsController/routes
  songsController/routes
  (not-found
   {:error "Not found"}))

(def app
  (-> routes
      (wrap-json-body {:keywords? true :bigdecimals? true})
      wrap-json-response))

(defn -main
  []
  (migrations/run-migrations)
  (run-jetty app {:port 3000}))
