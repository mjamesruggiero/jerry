(ns jerry.core
  (:require cljsjs.react
            cljsjs.react.dom))

(enable-console-print!)

(extend-type js/Symbol
  IPrintWithWriter
  (-pr-writer [obj writer _]
    (write-all writer (str "#object[]" (.toString obj) "]"))))

(set! (.-innerHTML (js/document.getElementById "app"))
      "<h1>Hello Jerry!</h1>")

(defn element [type props & children]
  (js/React.createElement type (clj->js props) children))

(def jerry-gif
  "http://25.media.tumblr.com/1e2b2139f003508783fecb2ee641df47/tumblr_mg8vud1kfY1r0e42eo6_r2_250.gif")


(def vdom (element "div" {}
                   (element "p" {} "hello from React")
                   (element "img" {:src jerry-gif})))

(js/ReactDOM.render vdom (js/document.getElementById "app"))
