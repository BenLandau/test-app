(ns test-app.core
  (:require
   [reagent.core :as reagent]
   [re-frame.core :as re-frame]
   [test-app.events :as events]
   [test-app.views :as views]
   [test-app.config :as config]

   ;;
   ["@material-ui/core" :as mc]
   ))


(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [ views/main-panel ]
                  (.getElementById js/document "app")))

(defn init []
  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))
