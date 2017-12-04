(ns fmp-devtest.core-test
  (:require [clojure.test :refer :all]
            [fmp-devtest.core :refer [primes
                                      tables]]))

(def real-primes
  [2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67
   71 73 79 83 89 97 101 103 107 109 113])

(deftest large-primes
  (testing "validate the first 30 primes"
    (is (= real-primes (take 30 primes))))
  (testing "ensure the algorithm does not overflow at large numbers"
    (is (= 20000 (count (take 20000 primes))))))

(defn- tables-2d-shape
  "n primes -> list of n-length lists"
  [n]
  (= n
     (count (first (tables (take n primes))))))

(defn- nth-prime-squares
  "nth element in each line is the nth prime squared"
  [n]
  (let [x (nth primes n)
        sq (* x x)]
    (= sq (nth
            (nth (tables (take (inc n) primes)) n)
            n))))

(deftest multiplication-tables
  (testing "output is of the expected shape"
    (is (seq? (first (tables (take 2 primes)))))
    (is (tables-2d-shape 3)))
  (testing "check for multiplication table correctness"
    (is (nth-prime-squares 4))))
