(ns fmp-devtest.core
  (:gen-class))

(defn- move-prime-to-next-factor
  "adds prime number under key: (prime + composite)"
  [table composite-num prime]
  (update table (+ composite-num prime) conj prime))

(defn prime-gen
  "lazy seq producing successive prime numbers"
  [table n]
  (if-let [prime-factors (get table n)]
    (recur (reduce #(move-prime-to-next-factor %1 n %2) (dissoc table n) prime-factors)
           (inc n))
    (lazy-seq (cons n (prime-gen (assoc table (* n n) [n])
                                 (inc n))))))

(def primes (prime-gen {} 2))

(defn tables [xs]
  "WARNING: GREEDY -- outputs the multiplication table for xs"
  (map #(map (partial * %) xs)
       xs))

(defn- pretty-printer
  [xs]
  (str "| " (clojure.string/join " | " xs) " |"))

(defn -main
  "pretty-prints a multiplication table of prime numbers"
  [n]
  ; no point being lazy for this function, we're going to have to print the whole list out on one line anyway
  (let [ps (take (read-string n) primes)
        t (tables ps)]
    (println (str "|   " (pretty-printer ps)))
    (dorun (map-indexed (fn [i line]
                         (println (pretty-printer (cons (nth ps i) line))))
                       t))))
