(ns test-app.events
  (:require
   [re-frame.core :as re-frame]
   [test-app.db :as db]
   ))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))

(re-frame/reg-event-db
  ::name-change
  (fn [db [_ new-name-val]]
    (assoc db :name new-name-val)))

(re-frame/reg-event-db
  ::change
  (fn [db [_ _]]
    (let [nv (inc (:num-changes db))
          is-checked (:is-checked db)]
      (if is-checked
        (assoc db :num-changes nv)
        db))))

(re-frame/reg-event-db
  ::checkbox
  (fn [db [_ nv]]
    (println "change to " nv)
    (assoc db :is-checked nv)))

(re-frame/reg-event-db
  ::reset
  (fn [db [_ _]]
    (assoc db :num-changes 0)))

(re-frame/reg-event-db
  ::clear-name
  (fn [db [_ _]]
    (assoc db :name "")))

