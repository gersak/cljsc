(ns helix-sc.app
  (:require
   [shadow.resource :as resource]
   ["react" :as react]
   ["react-dom" :as rdom]
   [helix.core :refer [defnc $ <>]]
   [helix.experimental.refresh :as refresh]
   [cljsc.core :refer [global-style]]
   [helix-sc.components.button.core :refer [button]]
   [helix-sc.components.mui-button.core :refer [mui-button]]))

(def reset-styles (resource/inline "ress/ress.css"))

(refresh/inject-hook!)

(defn ^:dev/after-load after-load []
  (refresh/refresh!))

(def root-el (js/document.getElementById "root"))

; (defonce r (react/useRef nil))

(comment
  (.log js/console "hi")
  (global-style reset-styles))

(defnc App
  []
  (<>
    ; ($ global-style reset-styles)
    ($ :div
       ($ button
          {:class-name "primary-global-class"
           :color      "primary"
           :on-click   #(js/alert "Primary")}
          "Primary")
       " "
       ($ button
          {:class-name "secondary-global-class"
           :color      "secondary"
           :on-click   #(js/alert "Secondary")}
          "Secondary"))
    ($ :br)
    #_($ :div
       ($ mui-button
          {:className "primary-global-class"
           :color     "primary"
           :variant   "contained"
           :onClick   #(js/alert "Primary MUI")}
          "Primary MUI")
       " "
       ($ mui-button
          {:className "secondary-global-class"
           :color     "secondary"
           :variant   "contained"
           :onClick   #(js/alert "Secondary MUI")}
          "Secondary MUI"))))

(defn render! []
  (rdom/render ($ App) root-el))

(defn unmount! [dom-el]
  (rdom/unmountComponentAtNode dom-el))

(defn init []
  (render!))
