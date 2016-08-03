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

(defonce app-state (atom {:display 0}))

(defn component [name & {:keys [render]}]
  (js/React.createClass
   #js {:displayName name
        :render (fn []
                  (this-as t
                    (render (js->clj (.-props t) :keywordize-keys t))))}))

(def Display
  (component "Display"
        :render (fn [props]
                  (element "div" {:className "display"}
                             (:value props)))))

(defn render [state]
  (js/ReactDOM.render (element Display {:value (:display state)})
                      (js/document.getElementById "app")))

(render @app-state)
(add-watch app-state :redraw (fn [_ _ _ state] (render state)))

