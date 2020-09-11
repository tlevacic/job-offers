(ns app.ui.components.main
  (:require [helix.dom :as d]
            [keechma.next.helix.core :refer [with-keechma use-sub]]
            [keechma.next.helix.classified :refer [defclassified]]
            [helix.core :as hx :refer [$]]
            [app.ui.components.item :refer [Item]]
            [keechma.next.helix.lib :refer [defnc]]))

(defclassified MainWrap :div "w-full h-full px-10 py-10 relative")

(defnc MainRenderer [props]
       (let [data (use-sub props :jobs)]
         ($ MainWrap
            (map #($ Item {:key % :data % & props}) data))))

(def Main (with-keechma MainRenderer))