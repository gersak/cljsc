(ns helix-sc.components.button.core
  (:require
   ["react" :as react]
   [helix.core :refer [$ defnc]]
   [helix-sc.components.button.styled :as styled]))

(defnc button
  [{:keys [class-name
           color
           children
           on-click]}]
  {:helix/features {:fast-refresh true}}
  (let [r (react/useRef nil)] 
    (.log js/console r)
    ($ "button"
       children)
    #_($ styled/button
       {:ref r
        :class-name class-name
        :color      color
        :on-click   on-click}
       children)))
