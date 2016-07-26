(ns jerry.core)

(enable-console-print!)

(set! (.-innerHTML (js/document.getElementById "app"))
      "<h1>Hello Jerry!</h1>")
