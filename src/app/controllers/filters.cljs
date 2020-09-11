(ns app.controllers.filters
  (:require [keechma.next.controller :as ctrl]
            [keechma.next.controllers.pipelines :as pipelines]
            [keechma.next.controllers.entitydb :as edb]
            [keechma.next.controllers.dataloader :as dl]
            [oops.core :refer [ocall oset!]]
            [keechma.next.controller :as ctrl]
            [app.data :refer [data]]
            [keechma.pipelines.core :as pp :refer-macros [pipeline!]]))

(derive :filters ::pipelines/controller)

(def pipelines
  {:keechma.on/start (pipeline! [value {:keys [state* deps-state*] :as ctrl}]
                                (reset! state* #{"JavaScript" "CSS"})
                                (ctrl/dispatch ctrl :jobs :filter-jobs value))
   :add-filter (pipeline! [value {:keys [state* deps-state*] :as ctrl}]
                          (set (conj @state* value))
                          (reset! state* value)
                          (ctrl/dispatch ctrl :jobs :filter-jobs value))
   :remove-filter (pipeline! [value {:keys [state* deps-state*] :as ctrl}]
                             (disj @state* value)
                             (reset! state* value)
                             (ctrl/dispatch ctrl :jobs :filter-jobs value))
   :remove-all-filters (pipeline! [value {:keys [state* deps-state*] :as ctrl}]
                             (reset! state* nil)
                             (ctrl/dispatch ctrl :jobs :filter-jobs value))})

(defmethod ctrl/prep :filters [ctrl]
  (-> ctrl
      (pipelines/register pipelines)))

(defmethod ctrl/derive-state :filters [_ state _]
  state)