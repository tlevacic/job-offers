(ns app.ui.components.header
  (:require [helix.dom :as d]
            [helix.core :as hx :refer [<> $]]
            [helix.hooks :as hooks]
            [keechma.next.helix.core :refer [with-keechma use-sub dispatch]]
            [keechma.next.helix.lib :refer [defnc]]))

(defnc Tag [props]
       (d/div {:class "flex mx-2"}
              (d/div {:class "text-xs text-darkCyan rounded-l-md bg-filterTablets px-2 py-1"}
                     (:title props))
              (d/div {:class "bg-darkCyan text-white px-2 rounded-r-md cursor-pointer hover:bg-veryDarkGrayishCyan"
                      :on-click #(dispatch props :filters :remove-filter (:title props))} "x")))


(defnc Header [props]
       (let [filters (use-sub props :filters)]
         (d/div {:class "w-full bg-darkCyan relative"
                 :style {:background-image "url(./images/bg-header-desktop.svg)"
                         :height "100px"}}
                (when filters
                  (d/div {:class "absolute h-30 z-20 bg-white px-4 py-4 rounded-lg mx-10 items-center flex justify-between"
                          :style {:top "80px"
                                  :left "50%"
                                  :minWidth "600px"
                                  :transform "translate(-50%)"}}
                         (d/div {:class "flex mr-48"}
                           (map #($ Tag {:title % & props}) filters))
                         (d/div {:class "cursor-pointer hover:text-veryDarkGrayishCyan text-xs font-bold text-darkCyan underline"
                                 :on-click #(dispatch props :filters :remove-all-filters)} "Clear"))))))
