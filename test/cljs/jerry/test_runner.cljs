(ns jerry.test-runner
  (:require
   [doo.runner :refer-macros [doo-tests]]
   [jerry.core-test]
   [jerry.common-test]))

(enable-console-print!)

(doo-tests 'jerry.core-test
           'jerry.common-test)
