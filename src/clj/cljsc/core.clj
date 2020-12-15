(ns cljsc.core
  (:refer-clojure :exclude [import])
  (:require 
    cljsc.util
    clojure.java.io))

(defmacro defstyled
  "Macro takes style name component type and default style
  as input. In addition mixins can be used to compute additional
  style attributes where mixins are pure fn that will receive
  props converted by cljs-bean.core/->clj function."
  [cname ctype cstyle & mixins]
  (let [props-sym (gensym "props")]
    (if (not-empty mixins)
      `(def ~cname
         ((cljsc.core/styled ~ctype)
          (fn [~props-sym]
            (let [clj-props# (cljs-bean.core/->clj ~props-sym)] 
              (cljs-bean.core/->js
                (reduce 
                  cljsc.core/deep-merge
                  ~cstyle 
                  ((juxt ~@mixins) clj-props#)))))))
      `(def ~cname
         ((cljsc.core/styled ~ctype) (fn [~props-sym] (cljs-bean.core/->js ~cstyle)))))))


(defmacro import-resource
  "Macro imports resource as string. Slurps file at
  compile time that bound to input 'sym'"
  [resource]
  (let [data (clojure.core/slurp (clojure.java.io/resource resource))]
    `(identity ~data)))
