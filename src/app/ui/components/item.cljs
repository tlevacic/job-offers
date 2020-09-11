(ns app.ui.components.item
  (:require [helix.dom :as d]
            [helix.core :as hx :refer [<>]]
            [helix.core :as hx :refer [$]]
            [keechma.next.helix.core :refer [with-keechma dispatch]]
            [helix.hooks :as hooks]
            [keechma.next.helix.lib :refer [defnc]]))

(defnc Dot [props]
       (d/div {:class "flex px-2 h-full justify-center items-center"}
              (d/div {:class "bg-darkGrayishCyan"
                      :style {:width "5px"
                              :height "5px"
                              :border-radius "50%"}})))

(defnc JobListingTag [{:keys [bg-color text-color title]}]
       (d/div {:class (str "bg-" bg-color " px-2 mx-1 flex justify-center items-center")
               :style {:border-radius "25px"}}
              (d/p {:class (str "text-" text-color " uppercase text-xs")}
                   title)))

(defnc CompanyName [{:keys [company new featured]}]
       (d/div {:class "flex"}
              (d/p {:class "text-darkCyan mr-3"}
                   company)
              (when new ($ JobListingTag {:bg-color "darkCyan" :text-color "white" :title "new!"}))
              (when featured ($ JobListingTag {:bg-color "black" :text-color "white" :title "featured"}))))

(defnc JobTitle [{:keys [role]}]
       (d/p {:class "text-lg font-bold"} role))

(defnc TimeAndPlaceComponent [{:keys [data]}]
       (d/div {:class "flex flex-col text-sm"}
              ($ CompanyName {:company (:company data) :new (:new data) :featured (:featured data)})
              ($ JobTitle {:role (:role data)})
              (d/div {:class "flex text-darkGrayishCyan"}
                     (d/p (:postedAt data))
                     ($ Dot)
                     (d/p (:contract data))
                     ($ Dot)
                     (d/p (:location data)))))

(defnc ImageWrap [{:keys [src]}]
       (d/div {:style {:width "70px"
                       :height "70px"}
               :class "mr-8"}
              (d/img {:src src :style {:width "100%" :height "100%" :maxWidth "100%" :maxHeight "100%" :border-radius "50%"}})))

(defnc TagItem [{:keys [title] :as props}]
       (d/div {:class "bg-filterTablets  text-darkCyan px-2 py-1 mx-1 cursor-pointer hover:bg-darkCyan hover:text-white"
               :on-click #(dispatch props :filters :add-filter title)}
              (d/p {:class "text-xs"}
                   title)))

(defnc TagsWrapper [{:keys [tools-and-languages] :as props}]
       (d/div {:class "flex justify-center items-center"}
              (map #($ TagItem {:key % :title % & props}) tools-and-languages)))



(defnc Item [{:keys [data] :as props}]
       (d/div {:class (str "w-full bg-white justify-between rounded my-6 py-4 flex px-10 shadow-lg" (when (:new data) " border-l-4 border-darkCyan "))}
              (d/div {:class "flex"}
                ($ ImageWrap {:src (:logo data)})
                ($ TimeAndPlaceComponent {:data data & props}))
              ($ TagsWrapper {:tools-and-languages (vec (concat (:tools data) (:languages data))) & props})))
