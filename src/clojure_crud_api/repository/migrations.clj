(ns clojure-crud-api.repository.migrations
  (:require [clojure.java.jdbc :as sql]))

(def db {:dbtype "postgresql"
         :dbname "postgres"
         :host "localhost"
         :user "postgres"
         :password "postgres"})

(def artists-table-ddl
  (sql/create-table-ddl "artists" [[:id "SERIAL PRIMARY KEY"]
                                   [:name "VARCHAR(150) NOT NULL"]
                                   [:description "VARCHAR(250) NOT NULL"]
                                   [:created_at :timestamp
                                    "NOT NULL" "DEFAULT CURRENT_TIMESTAMP"]]))

(def songs-table-ddl
  (sql/create-table-ddl "songs" [[:id "SERIAL PRIMARY KEY"]
                                 [:title "VARCHAR(150) NOT NULL"]
                                 [:length "INTEGER NOT NULL"]
                                 [:genre "VARCHAR(250)[] NOT NULL"]
                                 [:artist_id "INTEGER REFERENCES artists(id) ON DELETE CASCADE ON UPDATE CASCADE"]
                                 [:created_at :timestamp
                                  "NOT NULL" "DEFAULT CURRENT_TIMESTAMP"]]))

(defn migrated? [table-name]
  (-> (sql/query db
                 [(str "select count(*) from information_schema.tables "
                       "where table_name='" table-name "'")])
      first :count pos?))

(defn run-migrations []
  (when (not (migrated? "artists"))
    (print "Creating artists database...") (flush)
    (sql/db-do-commands db
                        [artists-table-ddl]))

  (when (not (migrated? "songs"))
    (print "Creating songs database...") (flush)
    (sql/db-do-commands db
                        [songs-table-ddl])))

; Extension to convert arrays to sql arrays
(extend-protocol clojure.java.jdbc/ISQLParameter
  clojure.lang.IPersistentVector
  (set-parameter [v ^java.sql.PreparedStatement stmt ^long i]
    (let [conn (.getConnection stmt)
          meta (.getParameterMetaData stmt)
          type-name (.getParameterTypeName meta i)]
      (if-let [elem-type (when (= (first type-name) \_) (apply str (rest type-name)))]
        (.setObject stmt i (.createArrayOf conn elem-type (to-array v)))
        (.setObject stmt i v)))))

(extend-protocol clojure.java.jdbc/IResultSetReadColumn
  java.sql.Array
  (result-set-read-column [val _ _]
    (into [] (.getArray val))))

