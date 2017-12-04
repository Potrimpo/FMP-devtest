# fmp-devtest

## Usage

clone this repository and run
`java -jar target/fmp-devtest-0.1.0-SNAPSHOT-standalone.jar n`
where n is the size of your n*n primes table.

You can also run it easily via leiningen (clojure build tool) if you have that, in order to validate
that it is _actually_ my code in the JAR

`lein run` and `lein test`

Remember, the algorithm for _calculating_ primes is performant, but actually printing 
thousands of numbers to a single line is not.

## Pleased with:

The algorithm itself (flavour of siev of erastosthenes). Didn't invent this one,
but the implemenation is pretty good. Could always be better with more mutable hacking, but it's
easily enough for this size.

## More time:

An implemenation of the `tables` function that is also lazy.
Doesn't help for the actual printing, but it would make it less of a risk factor in a codebase.
