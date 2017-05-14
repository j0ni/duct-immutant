(ns duct-immutant.inspect)

(defmacro inspect
  "prints the expression '<name> is <value>', and returns the value. This is a
  valuable debugging tool."
  [value]
  `(do
     (require 'clojure.pprint)
     (let [value# (quote ~value)
           result# ~value]
       (print (str value#
                   " is "
                   (with-out-str (clojure.pprint/pprint result#))
                   "\n"))
       ;; flushing would be provided by println, but println causes
       ;; near-simultaneous inspects to be interspersed
       (when *flush-on-newline*
         (flush))
       result#)))
