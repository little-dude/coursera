Score: 91/100

```
ASSESSMENT SUMMARY

Compilation:  PASSED
API:          PASSED

Findbugs:     PASSED
PMD:          PASSED
Checkstyle:   FAILED (0 errors, 1 warning)

Correctness:  23/27 tests passed
Memory:       8/8 tests passed
Timing:       19/20 tests passed

Aggregate score: 90.11%
[Compilation: 5%, API: 5%, Findbugs: 0%, PMD: 0%, Checkstyle: 0%, Correctness: 60%, Memory: 10%, Timing: 20%]

ASSESSMENT DETAILS

The following files were submitted:
----------------------------------
3.1K Aug 13 20:51 Percolation.java
2.9K Aug 13 20:51 PercolationStats.java


********************************************************************************
*  COMPILING                                                                    
********************************************************************************


% javac Percolation.java
*-----------------------------------------------------------

% javac PercolationStats.java
*-----------------------------------------------------------


================================================================


Checking the APIs of your programs.
*-----------------------------------------------------------
Percolation:

PercolationStats:

================================================================


********************************************************************************
*  CHECKING STYLE AND COMMON BUG PATTERNS                                       
********************************************************************************


% findbugs *.class
*-----------------------------------------------------------


================================================================


% pmd *.java
*-----------------------------------------------------------


================================================================


% checkstyle *.java
*-----------------------------------------------------------
[WARN] Percolation.java:13:19: The modifier 'static' is out of order. The preferred order is ['public', 'protected', 'private', 'abstract', 'static', 'final', 'transient', 'volatile', 'synchronized', 'native', and 'strictfp']. [ModifierOrder]
Checkstyle ends with 0 errors and 1 warnings.

% custom checkstyle checks for Percolation.java
*-----------------------------------------------------------

% custom checkstyle checks for PercolationStats.java
*-----------------------------------------------------------


================================================================


********************************************************************************
*  TESTING CORRECTNESS
********************************************************************************

Testing correctness of Percolation
*-----------------------------------------------------------
Running 15 total tests.

Tests 1 through 8 create a Percolation object using your code, then repeatedly
open sites by calling open(). After each call to open(), it checks the return
values of isOpen(), percolates(), numberOfOpenSites(), and isFull() in that order.
Except as noted, a site is opened at most once.

Test 1: Open predetermined list of sites using file inputs
  * filename = input6.txt
  * filename = input8.txt
  * filename = input8-no.txt
  * filename = input10-no.txt
  * filename = greeting57.txt
  * filename = heart25.txt
==> passed

Test 2: Open random sites until just before system percolates
  * n = 3
  * n = 5
  * n = 10
  * n = 10
  * n = 20
  * n = 20
  * n = 50
  * n = 50
==> passed

Test 3: Open predetermined sites for n = 1 and n = 2
  * filename = input1.txt
  * filename = input1-no.txt
  * filename = input2.txt
  * filename = input2-no.txt
==> passed

Test 4: Check for backwash with predetermined sites
  * filename = input20.txt
    - isFull() returns wrong value after 231 sites opened
    - student   isFull(18, 1) = true
    - reference isFull(18, 1) = false

    - failed after call 231 to isOpen()

  * filename = input10.txt
    - isFull() returns wrong value after 56 sites opened
    - student   isFull(9, 1) = true
    - reference isFull(9, 1) = false

    - failed after call 56 to isOpen()

  * filename = input50.txt
    - isFull() returns wrong value after 1412 sites opened
    - student   isFull(22, 28) = true
    - reference isFull(22, 28) = false

    - failed after call 1412 to isOpen()

  * filename = jerry47.txt
    - isFull() returns wrong value after 1076 sites opened
    - student   isFull(11, 47) = true
    - reference isFull(11, 47) = false

    - failed after call 1076 to isOpen()

==> FAILED

Test 5: Check for backwash with predetermined sites that have
        multiple percolating paths
  * filename = input3.txt
    - isFull() returns wrong value after 4 sites opened
    - student   isFull(3, 1) = true
    - reference isFull(3, 1) = false

    - failed after call 4 to isOpen()

  * filename = input4.txt
    - isFull() returns wrong value after 7 sites opened
    - student   isFull(4, 4) = true
    - reference isFull(4, 4) = false

    - failed after call 7 to isOpen()

  * filename = input7.txt
    - isFull() returns wrong value after 12 sites opened
    - student   isFull(6, 1) = true
    - reference isFull(6, 1) = false

    - failed after call 12 to isOpen()

==> FAILED

Test 6: Open predetermined sites with long percolating path
  * filename = snake13.txt
  * filename = snake101.txt
==> passed

Test 7: Open every site
  * filename = input5.txt
==> passed

Test 8: Open random sites until just before system percolates,
        allowing open() to be called on a site more than once
  * n = 3
  * n = 5
  * n = 10
  * n = 10
  * n = 20
  * n = 20
  * n = 50
  * n = 50
==> passed

Test 9: Call methods with invalid arguments
  * n = 10, (row, col) = (0, 6)
  * n = 10, (row, col) = (12, 6)
  * n = 10, (row, col) = (11, 6)
  * n = 10, (row, col) = (6, 0)
  * n = 10, (row, col) = (6, 12)
  * n = 10, (row, col) = (6, 11)
  * n = 10, (row, col) = (-2147483648, -2147483648)
  * n = 10, (row, col) = (1000, 1000)
==> passed

Test 10: Call constructor with invalid argument
  * n = -10
    - the constructor throws the wrong exception
    - the constructor throws a java.lang.NegativeArraySizeException
    - the constructor should throw a java.lang.IllegalArgumentException

  * n = -1
    - the constructor throws the wrong exception
    - the constructor throws a java.lang.NegativeArraySizeException
    - the constructor should throw a java.lang.IllegalArgumentException

  * n = 0
    - the constructor fails to throw an exception
    - the constructor should throw a java.lang.IllegalArgumentException

  * n = -2147483648
    - the constructor throws the wrong exception
    - the constructor throws a java.lang.NegativeArraySizeException
    - the constructor should throw a java.lang.IllegalArgumentException

==> FAILED

Test 11: Create multiple Percolation objects at the same time
         (to make sure you didn't store data in static variables)
==> passed

Test 12: Open predetermined list of sites using file inputs,
         but permute the order in which methods are called
  * filename = input8.txt;  order =     isFull(),     isOpen(), percolates()
  * filename = input8.txt;  order =     isFull(), percolates(),     isOpen()
  * filename = input8.txt;  order =     isOpen(),     isFull(), percolates()
  * filename = input8.txt;  order =     isOpen(), percolates(),     isFull()
  * filename = input8.txt;  order = percolates(),     isOpen(),     isFull()
  * filename = input8.txt;  order = percolates(),     isFull(),     isOpen()
==> passed

Test 13: Call all methods in random order until just before system percolates
  * n = 3
  * n = 5
  * n = 7
  * n = 10
  * n = 20
  * n = 50
==> passed

Test 14: Call all methods in random order until almost all sites are open,
         but with inputs not prone to backwash
  * n = 3
  * n = 5
  * n = 7
  * n = 10
  * n = 20
  * n = 50
==> passed

Test 15: Call all methods in random order until all sites are open,
         allowing open() to be called on a site more than once
         (these inputs are prone to backwash)
  * n = 3
    - isFull() returns wrong value after 5 sites opened
    - student   isFull(2, 1) = true
    - reference isFull(2, 1) = false

    - failed on trial 3 of 40

  * n = 5
    - isFull() returns wrong value after 14 sites opened
    - student   isFull(3, 4) = true
    - reference isFull(3, 4) = false

    - failed on trial 1 of 20

  * n = 7
    - isFull() returns wrong value after 34 sites opened
    - student   isFull(7, 2) = true
    - reference isFull(7, 2) = false

    - failed on trial 1 of 10

  * n = 10
    - isFull() returns wrong value after 61 sites opened
    - student   isFull(9, 10) = true
    - reference isFull(9, 10) = false

    - failed on trial 1 of 5

  * n = 20
    - isFull() returns wrong value after 259 sites opened
    - student   isFull(13, 1) = true
    - reference isFull(13, 1) = false

    - failed on trial 1 of 2

  * n = 50
    - isFull() returns wrong value after 1407 sites opened
    - student   isFull(38, 16) = true
    - reference isFull(38, 16) = false

    - failed on trial 1 of 1

==> FAILED


Total: 11/15 tests passed!


================================================================
********************************************************************************
*  TESTING CORRECTNESS (substituting reference Percolation)
********************************************************************************

Testing correctness of PercolationStats
*-----------------------------------------------------------
Running 12 total tests.

Test 1: Check that PercolationStats creates trials Percolation objects, each of size n-by-n
  * n =  20, trials =  10
  * n =  50, trials =  20
  * n = 100, trials =  50
  * n =  64, trials = 150
==> passed

Test 2: Check that PercolationStats calls open() until system percolates
  * n =  20, trials =  10
  * n =  50, trials =  20
  * n = 100, trials =  50
  * n =  64, trials = 150
==> passed

Test 3: Check that PercolationStats does not call open() after system percolates
  * n =  20, trials =  10
  * n =  50, trials =  20
  * n = 100, trials =  50
  * n =  64, trials = 150
==> passed

Test 4: Check that mean() is consistent with the number of intercepted calls to open()
        on blocked sites
  * n =  20, trials =  10
  * n =  50, trials =  20
  * n = 100, trials =  50
  * n =  64, trials = 150
==> passed

Test 5: Check that stddev() is consistent with the number of intercepted calls to open()
        on blocked sites
  * n =  20, trials =  10
  * n =  50, trials =  20
  * n = 100, trials =  50
  * n =  64, trials = 150
==> passed

Test 6: Check that confidenceLo() and confidenceHigh() are consistent with mean() and stddev()
  * n =  20, trials =  10
  * n =  50, trials =  20
  * n = 100, trials =  50
  * n =  64, trials = 150
==> passed

Test 7: Check whether exception is thrown if either n or trials is out of bounds
  * n = -23, trials =  42
  * n =  23, trials =   0
  * n = -42, trials =   0
  * n =  42, trials =  -1
  * n = -2147483648, trials = -2147483648
==> passed

Test 8: Create two PercolationStats objects at the same time and check mean()
        (to make sure you didn't store data in static variables)
  * n1 =  50, trials1 =  10, n2 =  50, trials2 =   5
  * n1 =  50, trials1 =   5, n2 =  50, trials2 =  10
  * n1 =  50, trials1 =  10, n2 =  25, trials2 =  10
  * n1 =  25, trials1 =  10, n2 =  50, trials2 =  10
  * n1 =  50, trials1 =  10, n2 =  15, trials2 = 100
  * n1 =  15, trials1 = 100, n2 =  50, trials2 =  10
==> passed

Test 9: Check that the methods return the same value, regardless of
        the order in which they are called
  * n =  20, trials =  10
  * n =  50, trials =  20
  * n = 100, trials =  50
  * n =  64, trials = 150
==> passed

Test 10: Check that no calls to StdRandom.setSeed()
  * n = 20, trials = 10
  * n = 20, trials = 10
  * n = 40, trials = 10
  * n = 80, trials = 10
==> passed

Test 11: Check distribution of number of sites opened until percolation
  * n = 2, trials = 100000
  * n = 3, trials = 100000
  * n = 4, trials = 100000
==> passed

Test 12: Check that each site is opened the expected number of times
  * n = 2, trials = 100000
  * n = 3, trials = 100000
  * n = 4, trials = 100000
==> passed


Total: 12/12 tests passed!


================================================================
********************************************************************************
*  MEMORY (substituting reference Percolation)
********************************************************************************

Analyzing memory of PercolationStats
*-----------------------------------------------------------
Running 4 total tests.

Test 1a-1d: Memory usage as a function of trials for n = 100
            (max allowed: 8*trials + 128 bytes)

            trials        bytes
--------------------------------------------
=> passed       16          184         
=> passed       32          312         
=> passed       64          568         
=> passed      128         1080         
==> 4/4 tests passed


Estimated student memory = 8.00 T + 56.00   (R^2 = 1.000)

Total: 4/4 tests passed!

================================================================



********************************************************************************
*  TIMING (substituting reference Percolation)                                                                  
********************************************************************************

Timing PercolationStats
*-----------------------------------------------------------
Running 4 total tests.

Test 1: Count calls to StdStats.mean() and StdStats.stddev()
  * n =  20, trials =  10
    - calls StdStats.mean() the wrong number of times
    - number of student   calls to StdStats.mean() = 3
    - number of reference calls to StdStats.mean() = 1

    - calls StdStats.stddev() the wrong number of times
    - number of student   calls to StdStats.stddev() = 3
    - number of reference calls to StdStats.stddev() = 1

  * n =  50, trials =  20
    - calls StdStats.mean() the wrong number of times
    - number of student   calls to StdStats.mean() = 3
    - number of reference calls to StdStats.mean() = 1

    - calls StdStats.stddev() the wrong number of times
    - number of student   calls to StdStats.stddev() = 3
    - number of reference calls to StdStats.stddev() = 1

  * n = 100, trials =  50
    - calls StdStats.mean() the wrong number of times
    - number of student   calls to StdStats.mean() = 3
    - number of reference calls to StdStats.mean() = 1

    - calls StdStats.stddev() the wrong number of times
    - number of student   calls to StdStats.stddev() = 3
    - number of reference calls to StdStats.stddev() = 1

  * n =  64, trials = 150
    - calls StdStats.mean() the wrong number of times
    - number of student   calls to StdStats.mean() = 3
    - number of reference calls to StdStats.mean() = 1

    - calls StdStats.stddev() the wrong number of times
    - number of student   calls to StdStats.stddev() = 3
    - number of reference calls to StdStats.stddev() = 1

==> FAILED

Test 2: Count calls to methods in StdRandom
  * n = 20, trials = 10
  * n = 20, trials = 10
  * n = 40, trials = 10
  * n = 80, trials = 10
==> passed

Test 3: Count calls to methods in Percolation
  * n =  20, trials =  10
  * n =  50, trials =  20
  * n = 100, trials =  50
  * n =  64, trials = 150
==> passed

Test 4: Call PercolationStats methods with trials = 3 and values of n that go up
        by a factor of sqrt(2). The test passes when n reaches 2,896.

     The approximate order-of-growth is n ^ (log ratio)

         n  seconds log ratio
     ------------------------
       724     0.18       2.4
      1024     0.39       2.3
      1448     1.13       3.0
      2048     2.96       2.8
      2896     7.32       2.6
==> passed


Total: 3/4 tests passed!


================================================================



********************************************************************************
*  MEMORY
********************************************************************************

Analyzing memory of Percolation
*-----------------------------------------------------------
Running 4 total tests.

Test 1a-1d: Check that total memory <= 17 n^2 + 128 n + 1024 bytes

                 n        bytes
--------------------------------------------
=> passed       64        39088         
=> passed      256       598192         
=> passed      512      2375856         
=> passed     1024      9470128         
==> 4/4 tests passed


Estimated student memory = 9.00 n^2 + 32.00 n + 176.00   (R^2 = 1.000)


Test 2 (bonus): Check that total memory <= 11 n^2 + 128 n + 1024 bytes
   -  bonus available only if solution passes backwash correctness test
==> FAILED


Total: 4/4 tests passed!

================================================================



********************************************************************************
*  TIMING                                                                  
********************************************************************************

Timing Percolation
*-----------------------------------------------------------
Running 16 total tests.

Test 1a-1e: Creates an n-by-n percolation system; open sites at random until
            the system percolates, interleaving calls to percolates() and open().
            Count calls to connected(), union() and find() in WeightedQuickUnionUF.

                                       2 * connected()
                 n       union()              + find()        constructor
-----------------------------------------------------------------------------------
=> passed       16          195                   312                   1         
=> passed       32          937                  1370                   1         
=> passed       64         3319                  5186                   1         
=> passed      128        11583                 19476                   1         
=> passed      256        48208                 79578                   1         
=> passed      512       184185                310558                   1         
=> passed     1024       731383               1237766                   1         
==> 7/7 tests passed


If one of the values in the table violates the performance limits
the factor by which you failed the test appears in parentheses.
For example, (9.6x) in the union() column indicates that it uses
9.6x too many calls.


Tests 2a-2f: Checks whether the number of calls to union(), connected(), and find()
             is a constant per call to open(), isOpen(), isFull(), and percolates().
             The table shows the maximum number of union(), connected(), and
             find() calls made during a single call to open(), isOpen(), isFull(),
             and percolates().

                 n     per open()      per isOpen()    per isFull()    per percolates() 
---------------------------------------------------------------------------------------------
=> passed       16        4               0               1               1         
=> passed       32        4               0               1               1         
=> passed       64        4               0               1               1         
=> passed      128        4               0               1               1         
=> passed      256        4               0               1               1         
=> passed      512        4               0               1               1         
=> passed     1024        4               0               1               1         
==> 7/7 tests passed



Running time (in seconds) depends on the machine on which the script runs.


Test 3: Create an n-by-n percolation system; interleave calls to percolates()
        and open() until the system percolates. The values of n go up by a
        factor of sqrt(2). The test is passed if n >= 4096 in under 10 seconds.

     The approximate order-of-growth is n ^ (log ratio)

                        log   union-find     log
         n  seconds   ratio   operations   ratio
     -------------------------------------------
      1448     0.23     2.4      5422840     2.0
      2048     0.68     3.1     10873662     2.0
      2896     1.88     3.0     21663628     2.0
      4096     4.66     2.6     43626730     2.0
==> passed



Test 4: Create an n-by-n percolation system; interleave calls to open(),
        percolates(), isOpen(), isFull(), and numberOfOpenSites() until.
        the system percolates. The values of n go up by a factor of sqrt(2).
        The test is passed if n >= 4096 in under 10 seconds.

                        log   union-find     log
         n  seconds   ratio   operations   ratio
     -------------------------------------------
      1448     0.22     2.5      5351572     2.0
      2048     0.68     3.3     10795664     2.0
      2896     1.99     3.1     21689210     2.0
      4096     5.14     2.7     43523122     2.0
==> passed


Total: 16/16 tests passed!


================================================================
```
