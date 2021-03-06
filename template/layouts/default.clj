; @title  default title
; @format html5

;; Your own function in template
(defn post-header [heading title & link]
 [:div {:class "page-header"}
  ; post title
  (if (nil? link)
    [heading title]
    [:a {:href (first link)} [heading title]])
  ; post tags
  (post-tags)
  ; post date
  (post-date)
  ])

(defn page-header [heading title & link]
  [:div {:class "page-title"}
   (if (nil? link)
     [heading title]
     [:a {:href (first link)} [heading title]])])

(defn nav-section [twitter-name]
  [:p
    (str "// ")
    (link "rss" "//csbook.club/atom.xml")
    (str " / ")
    (link "github" "https://github.com/csbookclub-pdx/csbook.club")])
[:head
 [:meta {:charset (:charset site)}]
 [:meta {:name    "viewport"
         :content "width=device-width, initial-scale=1.0, user-scalable=yes"}]

 [:title
  (if (= (:title site) "home")
    (:site-title site)
    (str (:site-title site) " - " (:title site)))]

 [:link {:rel   "shortcut icon"
         :href  "/favicon.ico"}]
 [:link {:href  "/atom.xml"
         :rel   "alternate"
         :title (:title site)
         :type  "application/atom-xml"}]

 (absolute-css ["/css/prettify.css" (:css site ())])
 (absolute-css {:media "only screen and (max-device-width:480px)"} (:device-css site))]
; /head

[:body

 ; github ribbon
 (github-ribbon "https://github.com/csbookclub-pdx/csbook.club")

 (container
  [:div {:style "overflow: hidden"}
    (page-header :h1 (:site-title site) "/" )
    [:div {:style "float: left; margin: 2px 0px 0px 30px;"}
      (nav-section (:twitter site))]
  ]

  contents

;;  (footer (nav-section (:twitter site)))

  (footer  (p {:class "pager"}
    (if-let [url (:next-page site)]
      (link "« Older Posts" url))
    (if (and (:prev-page site) (:next-page site))
      (str  " | ")) 
    (if-let [url (:prev-page site)]
      (link "Newer Posts »" url))))
  
;;  (link (img "/img/poweredby-misaki.png") "https://github.com/liquidz/misaki"))
  ) 

 ; /container

 (absolute-js ["/js/prettify.js"
               "/js/lang-clj.js"
               "/js/lang-lisp.js"
               (:js site ())])]
; /body
