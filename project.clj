(defproject duct-immutant "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [duct/logger "0.1.1"]
                 [integrant "0.4.0"]
                 [org.immutant/web "2.1.6"]]
  :profiles {:dev {:dependencies [[clj-http "3.5.0"]]
                   :plugins [[test2junit "1.2.5"]]
                   :test2junit-output-dir "target/test2junit"}})
