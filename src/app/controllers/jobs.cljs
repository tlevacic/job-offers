(ns app.controllers.jobs
  (:require [keechma.next.controller :as ctrl]
            [keechma.next.controllers.pipelines :as pipelines]
            [keechma.next.controllers.entitydb :as edb]
            [keechma.next.controllers.dataloader :as dl]
            [oops.core :refer [ocall oset!]]
            [app.data :refer [data]]
            [keechma.pipelines.core :as pp :refer-macros [pipeline!]]))

(derive :jobs ::pipelines/controller)

(def any-exists? (complement not-any?))

(defn filter-data [data filter]
  (reduce (fn [res ele]
            (let [tools (:tools ele)
                  languages (:languages ele)
                  all (vec (concat tools languages))]
              (if (any-exists? filter all)
                (conj res ele)
                res)))
          []
          data))

(def pipelines
  {:keechma.on/start (pipeline! [value {:keys [state* deps-state*] :as ctrl}]
                                (reset! state* data))
   :filter-jobs      (pipeline! [value {:keys [state* deps-state*] :as ctrl}]
                                (if (empty? value)
                                  data
                                  (filter-data data value))
                                (reset! state* value))})

(defmethod ctrl/prep :jobs [ctrl]
  (-> ctrl
      (pipelines/register pipelines)))

(defmethod ctrl/derive-state :jobs [_ state _]
  state)