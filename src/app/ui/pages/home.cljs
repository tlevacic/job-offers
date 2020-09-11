(ns app.ui.pages.home
  (:require [helix.dom :as d]
            [helix.core :as hx :refer [$]]
            [keechma.next.helix.core :refer [with-keechma]]
            [keechma.next.helix.lib :refer [defnc]]
            [app.ui.components.header :refer [Header]]
            [app.ui.components.main :refer [Main]]
            [keechma.next.helix.classified :refer [defclassified]]))

(defclassified HomepageWrapper :div "h-screen w-screen bg-background")

(defnc HomeRenderer [props]
  ($ HomepageWrapper
     ($ Header {& props})
     ($ Main)))

(def Home (with-keechma HomeRenderer))