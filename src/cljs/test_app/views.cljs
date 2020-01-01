(ns test-app.views
  (:require
   [re-frame.core :as re-frame]
   [test-app.subs :as subs]
   [test-app.events :as events]

   ["@material-ui/core" :as mc]
   [reagent.core :as r]
   ))

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])
        changes (re-frame/subscribe [::subs/num-changes])
        is-checked (re-frame/subscribe [::subs/is-checked])]
    [:div.val
     [:h1 "Hello from " @name]
     [:input {:type "text"
              :value @name
              :on-change #(do
                            (re-frame/dispatch [::events/name-change (-> % .-target .-value)])
                            (re-frame/dispatch [::events/change 1]))}]
     [:div.changes "There have been " @changes " changes to the name field."]
     [:div.controls
      [:div#checkbox
        [:p
          [:input {:type "checkbox"
                   :value @is-checked
                   :on-change #(re-frame/dispatch [::events/checkbox (not @is-checked)])}]
          "Don't count changes"
          ]]

       [:> mc/Button
        {:variant "outlined"
         :color   (if (empty? @name) "secondary" "primary")
         :on-click #(re-frame/dispatch [::events/clear-name 1])
         :disabled (empty? @name)} "CLEAR" ]

       [:> mc/Button
        {:variant "contained"
         :color (if (zero? @changes) "secondary" "primary")
         :on-click #(re-frame/dispatch [::events/reset 1])
         :disabled (zero? @changes)} "RESET"]]]))






