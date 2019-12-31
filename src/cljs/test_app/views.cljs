(ns test-app.views
  (:require
   [re-frame.core :as re-frame]
   [test-app.subs :as subs]
   [test-app.events :as events]
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
          "Count changes"
          ]]
      [:div#buttons
        [:input {:type "button" :color "green" :value "Reset"
                 :style {:background-color (if (zero? @changes) "grey" "green") }
                 :on-click #(re-frame/dispatch [::events/reset 1])}]

         ]
       ]
      ]
     ))

