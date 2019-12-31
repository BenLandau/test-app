(ns test-app.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::name
 (fn [db]
   (:name db)))

(re-frame/reg-sub
  ::num-changes
  (fn [db]
    (:num-changes db)))


(re-frame/reg-sub
  ::is-checked
  (fn [db]
    (:is-checked db)))

