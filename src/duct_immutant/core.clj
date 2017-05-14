(ns duct-immutant.core
  (:require [duct.logger :refer [log]]
            [integrant.core :as ig]
            [immutant.web :as web]
            [duct-immutant.inspect :refer [inspect]]))

(derive :duct-immutant/immutant :duct.server/http)

(defmethod ig/init-key :duct-immutant/immutant [_ {:keys [logger] :as opts}]
  (let [handler (atom (delay (:handler opts)))
        logger  (atom logger)
        options (dissoc opts :handler :logger)]
    (log @logger :report ::starting-server (select-keys opts [:port]))
    {:handler handler
     :logger  logger
     :server  (web/run (fn [req] (@@handler req)) options)}))

(defmethod ig/halt-key! :duct-immutant/immutant [_ {:keys [server logger]}]
  (log @logger :report ::stopping-server)
  (web/stop server))

(defmethod ig/suspend-key! :duct-immutant/immutant [_ {:keys [handler]}]
  (reset! handler (promise)))

(defmethod ig/resume-key :duct-immutant/immutant [key opts old-opts old-impl]
  (if (= (dissoc opts :handler :logger) (dissoc old-opts :handler :logger))
    (do (deliver @(:handler old-impl) (:handler opts))
        (reset! (:logger old-impl) (:logger opts))
        old-impl)
    (do (ig/halt-key! key old-impl)
        (ig/init-key key opts))))
