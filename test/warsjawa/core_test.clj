(ns warsjawa.core-test
  (:require [clojure.test :refer :all]
            [warsjawa.core :refer :all]))

#_
(deftest t-hello-handler
  (is (= {:status 200
          :body "Hello, World!"
          :headers {"content-type" "text/plain"}}
         (hello-handler {:uri "/"
                         :request-method :get}))))

#_
(deftest t-hello-handler-with-uri
  (is (= {:status 200
          :body "Hello, World!"
          :headers {"content-type" "text/plain"}}
         (hello-handler {:uri "/"
                         :request-method :get})))
  (is (not= 200 (:status (hello-handler {:uri "/wp-login.php"})))))

#_
(deftest t-hello-handler-with-pretty-404
  (is (= {:status 200
          :body "Hello, World!"
          :headers {"content-type" "text/plain"}}
         (hello-handler {:uri "/"
                         :request-method :get})))
  (is (= {:status 404
          :body "Not found: /no-such-url"
          :headers {"content-type" "text/plain"}}
         (hello-handler {:uri "/no-such-url"
                         :request-method :get}))))

#_
(deftest t-hello-handler-with-params
  (is (= {:status 200
          :body "Hello, Warsaw!"
          :headers {"content-type" "text/plain"}}
         (hello-handler {:uri "/"
                         :params {"name" "Warsaw"}
                         :request-method :get}))))

#_
(deftest t-hello-handler-with-query-string
  (is (= {:status 200
          :body "Hello, Warsjawa!"
          :headers {"content-type" "text/plain"}}
         (hello-handler {:uri "/"
                         :query-string "name=Warsjawa"
                         :request-method :get}))))

#_
(deftest t-hello-handler-html-route
  (is (= {:status 200
          :body "<p><strong>Hello</strong>, Warsaw!</p>"
          :headers {"content-type" "text/html"}}
         (hello-handler {:uri "/Warsaw.html"
                         :request-method :get}))))

#_
(deftest t-hello-handler-no-post
  (is (= 404 (:status (hello-handler {:request-method :post
                                      :uri "/"})))))

#_
(deftest t-hello-handler-hiccup
  (is (= {:status 200
          :body "<html><body><div id=\"content\">It works!</div></body></html>"
          :headers {"content-type" "text/html"}}
         (hello-handler {:uri "/hiccup"
                         :request-method :get}))))
