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

(comment
  (def m1
    {:a 1
     :b 2
     :c {:d {:e 3}}})
  (def m2
    {:a 9
     :c {:d {:e 120}
         :g 0}})
  (deep-merge m1 m2))
