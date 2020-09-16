(ns cljsc.util)

;; From http://dnaeon.github.io/recursively-merging-maps-in-clojure/
(defn deep-merge
  "Recursively merges maps."
  [& maps]
  (letfn [(m [& xs]
            (if (some #(and (map? %) (not (record? %))) xs)
              (apply merge-with m xs)
              (last xs)))]
    (reduce m maps)))


(comment
  (do
    (def text :text)
    (def color :color)
    (def selected? :selected?)
    (def circle-fill :circle-fill)
    (def circle-stroke :circle-stroke)
    (def m1
      {:text {:fill text}
       " .visible" {:stroke color}
       ".process-transition-shadow" 
       {:stroke-width (if selected? "40" "10")
        ":hover" {:cursor (if selected? "inherit" "pointer")}}
       :circle {:fill circle-fill :stroke circle-stroke :stroke-width "0.4"}})
    (def m2
      {:text
       {:user-select "none"}
       " .visible" {:stroke-width "2px"
                    :fill "none"}
       ".process-transition-shadow" 
       {:stroke-opcity "0"
        :pointer-events "stroke"
        :fill "none"}})
    (cljs.pprint/pprint (deep-merge m1 nil m2))))
