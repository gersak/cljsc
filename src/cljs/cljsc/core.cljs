(ns cljsc.core
  (:require-macros [cljsc.core])
  (:require
    cljsc.util
    ["styled-components"
     :refer [default createGlobalStyle ThemeProvider]
     :rename {default sc}]))


(def styled sc)
(def global-style createGlobalStyle)
(def theme-provider ThemeProvider)

(defn deep-merge
  "Recursively merges maps."
  [& maps]
  (letfn [(m [& xs]
            (if (some #(and (map? %) (not (record? %))) xs)
              (apply merge-with m xs)
              (last xs)))]
    (reduce m maps)))
