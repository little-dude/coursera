Score: 90/100

```
See the Assessment Guide for information on how to interpret this report.

ASSESSMENT SUMMARY

Compilation:  PASSED
API:          PASSED

Findbugs:     FAILED (7 warnings)
PMD:          FAILED (1 warning)
Checkstyle:   FAILED (0 errors, 12 warnings)

Correctness:  34/41 tests passed
Memory:       1/1 tests passed
Timing:       41/41 tests passed

Aggregate score: 89.76%
[Compilation: 5%, API: 5%, Findbugs: 0%, PMD: 0%, Checkstyle: 0%, Correctness: 60%, Memory: 10%, Timing: 20%]

ASSESSMENT DETAILS

The following files were submitted:
----------------------------------
4.2K Aug 21 05:29 BruteCollinearPoints.java
4.4K Aug 21 05:29 FastCollinearPoints.java
4.1K Aug 21 05:29 Point.java


********************************************************************************
*  COMPILING                                                                    
********************************************************************************


% javac Point.java
*-----------------------------------------------------------

% javac BruteCollinearPoints.java
*-----------------------------------------------------------

% javac FastCollinearPoints.java
*-----------------------------------------------------------


================================================================


Checking the APIs of your programs.
*-----------------------------------------------------------
Point:

BruteCollinearPoints:

FastCollinearPoints:

================================================================


********************************************************************************
*  CHECKING STYLE AND COMMON BUG PATTERNS                                       
********************************************************************************


% findbugs *.class
*-----------------------------------------------------------
L C SCRV_SUSPICIOUS_COMPARATOR_RETURN_VALUES SCRV: Comparator method Point.compareTo(Point) doesn't seem to return all ordering values  At Point.java:[line 100]
L C SCRV_SUSPICIOUS_COMPARATOR_RETURN_VALUES SCRV: Comparator method Point$PointComparator.compare(Point, Point) doesn't seem to return all ordering values  At Point.java:[line 22]
L D FE_FLOATING_POINT_EQUALITY FE: Tests for exact floating-point equality. Because floating-point calculations may involve rounding, the calculated values may be imprecise.  At Point.java:[line 27]
M P NMCS_NEEDLESS_MEMBER_COLLECTION_SYNCHRONIZATION NMCS: Class BruteCollinearPoints defines unneeded synchronization on member collection  At BruteCollinearPoints.java:[line 20]
L D FE_FLOATING_POINT_EQUALITY FE: Tests for exact floating-point equality. Because floating-point calculations may involve rounding, the calculated values may be imprecise.  At BruteCollinearPoints.java:[line 40]
M P NMCS_NEEDLESS_MEMBER_COLLECTION_SYNCHRONIZATION NMCS: Class FastCollinearPoints defines unneeded synchronization on member collection  At FastCollinearPoints.java:[line 21]
L D FE_FLOATING_POINT_EQUALITY FE: Tests for exact floating-point equality. Because floating-point calculations may involve rounding, the calculated values may be imprecise.  At FastCollinearPoints.java:[line 45]
The following classes needed for analysis were missing:
  LineSegment226
  Point226
Warnings generated: 7
Missing classes: 2


================================================================


% pmd *.java
*-----------------------------------------------------------
BruteCollinearPoints.java:58: Avoid using a 'continue' or 'break' statement as the last in a loop. [AvoidBranchingStatementAsLastInLoop]
PMD ends with 1 warning.


================================================================


% checkstyle *.java
*-----------------------------------------------------------
[WARN] BruteCollinearPoints.java:14:19: Do not use 'java.util.Vector' in this course. Instead, use 'java.util.ArrayList' or 'java.util.LinkedList'. [IllegalType]
[WARN] BruteCollinearPoints.java:44:30: Do not use the letter 'l' as a variable name (or other identifier). It is hard to distinguish from the number '1'. [IllegalTokenText]
[WARN] BruteCollinearPoints.java:44:39: Do not use the letter 'l' as a variable name (or other identifier). It is hard to distinguish from the number '1'. [IllegalTokenText]
[WARN] BruteCollinearPoints.java:44:58: Do not use the letter 'l' as a variable name (or other identifier). It is hard to distinguish from the number '1'. [IllegalTokenText]
[WARN] BruteCollinearPoints.java:45:43: Do not use the letter 'l' as a variable name (or other identifier). It is hard to distinguish from the number '1'. [IllegalTokenText]
[WARN] FastCollinearPoints.java:15:19: Do not use 'java.util.Vector' in this course. Instead, use 'java.util.ArrayList' or 'java.util.LinkedList'. [IllegalType]
[WARN] FastCollinearPoints.java:51:26: Control variable 'j' is modified inside loop. [ModifiedControlVariable]
[WARN] FastCollinearPoints.java:91:19: '=' is not preceded with whitespace. [WhitespaceAround]
[WARN] FastCollinearPoints.java:91:20: '=' is not followed by whitespace. [WhitespaceAround]
[WARN] FastCollinearPoints.java:102:19: '=' is not preceded with whitespace. [WhitespaceAround]
[WARN] FastCollinearPoints.java:102:20: '=' is not followed by whitespace. [WhitespaceAround]
[WARN] Point.java:84:27: Typecast is not followed by whitespace. [WhitespaceAfter]
Checkstyle ends with 0 errors and 12 warnings.

% custom checkstyle checks for Point.java
*-----------------------------------------------------------

% custom checkstyle checks for BruteCollinearPoints.java
*-----------------------------------------------------------

% custom checkstyle checks for FastCollinearPoints.java
*-----------------------------------------------------------


================================================================


********************************************************************************
*  TESTING CORRECTNESS
********************************************************************************

Testing correctness of Point
*-----------------------------------------------------------
Running 3 total tests.

Test 1: p.slopeTo(q)
  * positive infinite slope, where p and q have coordinates in [0, 500)
  * positive infinite slope, where p and q have coordinates in [0, 32768)
  * negative infinite slope, where p and q have coordinates in [0, 500)
  * negative infinite slope, where p and q have coordinates in [0, 32768)
  * positive zero     slope, where p and q have coordinates in [0, 500)
  * positive zero     slope, where p and q have coordinates in [0, 32768)
  * symmetric for random points p and q with coordinates in [0, 500)
  * symmetric for random points p and q with coordinates in [0, 32768)
  * transitive for random points p, q, and r with coordinates in [0, 500)
  * transitive for random points p, q, and r with coordinates in [0, 32768)
  * slopeTo(), where p and q have coordinates in [0, 500)
     Failed on trial 1 of 100000
     p                        = (293, 230)
     q                        = (105, 128)
     student   p.slopeTo(q) = 0.542553186416626
     reference p.slopeTo(q) = 0.5425531914893617
  * slopeTo(), where p and q have coordinates in [0, 32768)
     Failed on trial 1 of 100000
     p                        = (3147, 18659)
     q                        = (28972, 15372)
     student   p.slopeTo(q) = -0.12727977335453033
     reference p.slopeTo(q) = -0.12727976766698934
  * slopeTo(), where p and q have coordinates in [0, 10)
     Failed on trial 5 of 100000
     p                        = (7, 6)
     q                        = (4, 7)
     student   p.slopeTo(q) = -0.3333333432674408
     reference p.slopeTo(q) = -0.3333333333333333
  * throw a java.lang.NullPointerException if argument is null
==> FAILED

Test 2: p.compareTo(q)
  * reflexive, where p and q have coordinates in [0, 500)
  * reflexive, where p and q have coordinates in [0, 32768)
  * antisymmetric, where p and q have coordinates in [0, 500)
  * antisymmetric, where p and q have coordinates in [0, 32768)
  * transitive, where p, q, and r have coordinates in [0, 500)
  * transitive, where p, q, and r have coordinates in [0, 32768)
  * sign of compareTo(), where p and q have coordinates in [0, 500)
  * sign of compareTo(), where p and q have coordinates in [0, 32768)
  * sign of compareTo(), where p and q have coordinates in [0, 10)
  * throw java.lang.NullPointerException exception if argument is null
==> passed

Test 3: p.slopeOrder().compare(q, r)
  * reflexive, where p and q have coordinates in [0, 500)
  * reflexive, where p and q have coordinates in [0, 32768)
  * antisymmetric, where p, q, and r have coordinates in [0, 500)
  * antisymmetric, where p, q, and r have coordinates in [0, 32768)
  * transitive, where p, q, r, and s have coordinates in [0, 500)
  * transitive, where p, q, r, and s have coordinates in [0, 32768)
  * sign of compare(), where p, q, and r have coordinates in [0, 500)
  * sign of compare(), where p, q, and r have coordinates in [0, 32768)
  * sign of compare(), where p, q, and r have coordinates in [0, 10)
  * throw java.lang.NullPointerException if either argument is null
==> passed


Total: 2/3 tests passed!


================================================================
********************************************************************************
*  TESTING CORRECTNESS (substituting reference Point and LineSegment)
********************************************************************************

Testing correctness of BruteCollinearPoints
*-----------------------------------------------------------
Running 17 total tests.

The inputs satisfy the following conditions:
  - no duplicate points
  - no 5 (or more) points are collinear
  - all x- and y-coordinates between 0 and 32,767

Test 1: points from a file
  * filename = input8.txt
  * filename = equidistant.txt
    - number of entries in student   solution: 3
    - number of entries in reference solution: 4
    - 1 missing entry in student solution: '(10000, 0) -> (13000, 0) -> (20000, 0) -> (30000, 0)'


  * filename = input40.txt
    - number of entries in student   solution: 3
    - number of entries in reference solution: 4
    - 1 missing entry in student solution: '(1000, 17000) -> (13000, 17000) -> (17000, 17000) -> (29000, 17000)'


  * filename = input48.txt
==> FAILED

Test 2a: points from a file with horizontal line segments
  * filename = horizontal5.txt
  * filename = horizontal25.txt
==> passed

Test 2b: random horizontal line segments
  *  1 random horizontal line segment
  *  5 random horizontal line segments
  * 10 random horizontal line segments
  * 15 random horizontal line segments
==> passed

Test 3a: points from a file with vertical line segments
  * filename = vertical5.txt
  * filename = vertical25.txt
==> passed

Test 3b: random vertical line segments
  *  1 random vertical line segment
  *  5 random vertical line segments
  * 10 random vertical line segments
  * 15 random vertical line segments
==> passed

Test 4a: points from a file with no line segments
  * filename = random23.txt
  * filename = random38.txt
==> passed

Test 4b: random points with no line segments
  *  5 random points
  * 10 random points
  * 20 random points
  * 50 random points
==> passed

Test 5: points from a file with fewer than 4 points
  * filename = input1.txt
  * filename = input2.txt
  * filename = input3.txt
==> passed

Test 6: check for dependence on either compareTo() or compare()
        returning { -1, +1, 0 } instead of { negative integer,
        positive integer, zero }
  * filename = equidistant.txt
    - number of entries in student   solution: 3
    - number of entries in reference solution: 4
    - 1 missing entry in student solution: '(10000, 0) -> (13000, 0) -> (20000, 0) -> (30000, 0)'


  * filename = input40.txt
    - number of entries in student   solution: 3
    - number of entries in reference solution: 4
    - 1 missing entry in student solution: '(1000, 17000) -> (13000, 17000) -> (17000, 17000) -> (29000, 17000)'


  * filename = input48.txt
==> FAILED

Test 7: check for fragile dependence on return value of toString()
  * filename = equidistant.txt
    - number of entries in student   solution: 3
    - number of entries in reference solution: 4
    - 1 missing entry in student solution: '(10000, 0) -> (13000, 0) -> (20000, 0) -> (30000, 0)'


  * filename = input40.txt
    - number of entries in student   solution: 3
    - number of entries in reference solution: 4
    - 1 missing entry in student solution: '(1000, 17000) -> (13000, 17000) -> (17000, 17000) -> (29000, 17000)'


  * filename = input48.txt

It is bad style to write code that depends on the particular format of
the output from the toString() method, especially if your reason for
doing so is to circumvent the public API (which intentionally does not
provide access to the x- and y-coordinates).

==> FAILED

Test 8: random line segments, none vertical or horizontal
  *  1 random line segment
  *  5 random line segments
  * 10 random line segments
  * 15 random line segments
==> passed

Test 9: random line segments
  *  1 random line segment
  *  5 random line segments
  * 10 random line segments
  * 15 random line segments
==> passed

Test 10: check that data type is immutable by testing whether each method
         returns the same value, regardless of any intervening operations
  * input8.txt
  * equidistant.txt
==> passed

Test 11: check that data type does not mutate the constructor argument
  * input8.txt
  * equidistant.txt
==> passed

Test 12: numberOfSegments() is consistent with segments()
  * filename = input8.txt
  * filename = equidistant.txt
  * filename = input40.txt
  * filename = input48.txt
  * filename = horizontal5.txt
  * filename = vertical5.txt
  * filename = random23.txt
==> passed

Test 13: throws an exception if either the constructor argument is null
         or any entry in array is null
  * argument is null
  * Point[] of length 10, number of null entries = 1
  * Point[] of length 10, number of null entries = 10
  * Point[] of length 4, number of null entries = 1
  * Point[] of length 3, number of null entries = 1
    - constructor fails to throw an exception
     3
     30133  9674
     24741 15477
     null


  * Point[] of length 2, number of null entries = 1
    - constructor fails to throw an exception
     2
     null
     15634  1662


  * Point[] of length 1, number of null entries = 1
    - constructor fails to throw an exception
     1
     null


==> FAILED

Test 14: check that the constructor throws an exception if duplicate points
  * 50 points
  * 25 points
  * 5 points
    - failed on trial 1 of 100
    - constructor fails to throw a java.lang.IllegalArgumentException when passed duplicate points
     5
       650 18590
     13278  2035
     26106 24328
     11596 23472
     11596 23472


  * 4 points
    - failed on trial 1 of 100
    - constructor fails to throw a java.lang.IllegalArgumentException when passed duplicate points
     4
     28115  4886
     19811  1046
      1549  8293
      1549  8293


  * 3 points
    - failed on trial 1 of 100
    - constructor fails to throw a java.lang.IllegalArgumentException when passed duplicate points
     3
     20996  1885
      9621 13796
     20996  1885


  * 2 points
    - failed on trial 1 of 100
    - constructor fails to throw a java.lang.IllegalArgumentException when passed duplicate points
     2
      5738 13763
      5738 13763


==> FAILED


Total: 12/17 tests passed!


================================================================
Testing correctness of FastCollinearPoints
*-----------------------------------------------------------
Running 21 total tests.

The inputs satisfy the following conditions:
  - no duplicate points
  - all x- and y-coordinates between 0 and 32,767

Test 1: points from a file
  * filename = input8.txt
  * filename = equidistant.txt
  * filename = input40.txt
  * filename = input48.txt
  * filename = input299.txt
==> passed

Test 2a: points from a file with horizontal line segments
  * filename = horizontal5.txt
  * filename = horizontal25.txt
  * filename = horizontal50.txt
  * filename = horizontal75.txt
  * filename = horizontal100.txt
==> passed

Test 2b: random horizontal line segments
  *  1 random horizontal line segment
  *  5 random horizontal line segments
  * 10 random horizontal line segments
  * 15 random horizontal line segments
==> passed

Test 3a: points from a file with vertical line segments
  * filename = vertical5.txt
  * filename = vertical25.txt
  * filename = vertical50.txt
  * filename = vertical75.txt
  * filename = vertical100.txt
==> passed

Test 3b: random vertical line segments
  *  1 random vertical line segment
  *  5 random vertical line segments
  * 10 random vertical line segments
  * 15 random vertical line segments
==> passed

Test 4a: points from a file with no line segments
  * filename = random23.txt
  * filename = random38.txt
  * filename = random91.txt
  * filename = random152.txt
==> passed

Test 4b: random points with no line segments
  *  5 random points
  * 10 random points
  * 20 random points
  * 50 random points
==> passed

Test 5a: points from a file with 5 or more on some line segments
  * filename = input9.txt
  * filename = input10.txt
  * filename = input20.txt
  * filename = input50.txt
  * filename = input80.txt
  * filename = input300.txt
  * filename = inarow.txt
==> passed

Test 5b: points from a file with 5 or more on some line segments
  * filename = kw1260.txt
  * filename = rs1423.txt
==> passed

Test 6: points from a file with fewer than 4 points
  * filename = input1.txt
    java.lang.ArrayIndexOutOfBoundsException: 1

    FastCollinearPoints.<init>(FastCollinearPoints.java:33)
    TestFastCollinearPoints.testSegments(TestFastCollinearPoints.java:107)
    TestFastCollinearPoints.file(TestFastCollinearPoints.java:151)
    TestFastCollinearPoints.test6(TestFastCollinearPoints.java:314)
    TestFastCollinearPoints.main(TestFastCollinearPoints.java:807)

  * filename = input2.txt
  * filename = input3.txt
==> FAILED

Test 7: check for dependence on either compareTo() or compare()
        returning { -1, +1, 0 } instead of { negative integer,
        positive integer, zero }
  * filename = equidistant.txt
  * filename = input40.txt
  * filename = input48.txt
  * filename = input299.txt
==> passed

Test 8: check for fragile dependence on return value of toString()
  * filename = equidistant.txt
  * filename = input40.txt
  * filename = input48.txt
==> passed

Test 9: random line segments, none vertical or horizontal
  *  1 random line segment
  *  5 random line segments
  * 25 random line segments
  * 50 random line segments
  * 100 random line segments
==> passed

Test 10: random line segments
  *  1 random line segment
  *  5 random line segments
  * 25 random line segments
  * 50 random line segments
  * 100 random line segments
==> passed

Test 11: random distinct points in a given range
  * 5 random points in a 10-by-10 grid
  * 10 random points in a 10-by-10 grid
  * 50 random points in a 10-by-10 grid
  * 90 random points in a 10-by-10 grid
  * 200 random points in a 50-by-50 grid
==> passed

Test 12: m*n points on an m-by-n grid
  * 3-by-3 grid
  * 4-by-4 grid
  * 5-by-5 grid
  * 10-by-10 grid
  * 20-by-20 grid
  * 5-by-4 grid
  * 6-by-4 grid
  * 10-by-4 grid
  * 15-by-4 grid
  * 25-by-4 grid
==> passed

Test 13: check that data type is immutable by testing whether each method
         returns the same value, regardless of any intervening operations
  * input8.txt
  * equidistant.txt
==> passed

Test 14: check that data type does not mutate the constructor argument
  * input8.txt
  * equidistant.txt
==> passed

Test 15: numberOfSegments() is consistent with segments()
  * filename = input8.txt
  * filename = equidistant.txt
  * filename = input40.txt
  * filename = input48.txt
  * filename = horizontal5.txt
  * filename = vertical5.txt
  * filename = random23.txt
==> passed

Test 16: throws an exception if either constructor argument is null
         or any entry in array is null
  * argument is null
  * Point[] of length 10, number of null entries = 1
  * Point[] of length 10, number of null entries = 10
  * Point[] of length 4, number of null entries = 1
  * Point[] of length 3, number of null entries = 1
  * Point[] of length 2, number of null entries = 1
  * Point[] of length 1, number of null entries = 1
==> passed

Test 17: check that the constructor throws an exception if duplicate points
  * 50 points
  * 25 points
  * 5 points
  * 4 points
  * 3 points
  * 2 points
==> passed


Total: 20/21 tests passed!


================================================================
********************************************************************************
*  MEMORY
********************************************************************************

Analyzing memory of Point
*-----------------------------------------------------------
Running 1 total tests.

The maximum amount of memory per Point object is 32 bytes.

Student memory = 24 bytes (passed)

Total: 1/1 tests passed!

================================================================



********************************************************************************
*  TIMING                                                                  
********************************************************************************

Timing BruteCollinearPoints
*-----------------------------------------------------------
Running 10 total tests.

Test 1a-1e: Find collinear points among n random distinct points


                                                      slopeTo()
             n    time     slopeTo()   compare()  + 2*compare()        compareTo()
-----------------------------------------------------------------------------------------------
=> passed    16   0.00         546           0            546                 1001         
=> passed    32   0.00        4930           0           4930                 9425         
=> passed    64   0.01       41602           0          41602                81313         
=> passed   128   0.02      341250           0         341250               674625         
=> passed   256   0.03     2763266           0        2763266              5494401         
==> 5/5 tests passed

Test 2a-2e: Find collinear points among n/4 arbitrary line segments


                                                      slopeTo()
             n    time     slopeTo()   compare()  + 2*compare()        compareTo()
-----------------------------------------------------------------------------------------------
=> passed    16   0.00         368           0            368                  730         
=> passed    32   0.00        3457           0           3457                 6770         
=> passed    64   0.00       29486           0          29486                58209         
=> passed   128   0.01      238745           0         238745               473831         
=> passed   256   0.03     1949603           0        1949603              3884054         
==> 5/5 tests passed

Total: 10/10 tests passed!


================================================================



Timing FastCollinearPoints
*-----------------------------------------------------------
Running 31 total tests.

Test 1a-1g: Find collinear points among n random distinct points


                                                      slopeTo()
             n    time     slopeTo()   compare()  + 2*compare()        compareTo()
-----------------------------------------------------------------------------------------------
=> passed    64   0.01        4096       18821          41738                   64         
=> passed   128   0.01       16384       88025         192434                  128         
=> passed   256   0.04       65536      415535         896606                  256         
=> passed   512   0.16      262144     1900544        4063232                  512         
=> passed  1024   0.39     1048576     8559350       18167276                 1024         
=> passed  2048   0.89     4194304    37679468       79553240                 2048         
==> 6/6 tests passed

lg ratio(slopeTo() + 2*compare()) = lg (79553240 / 18167276) = 2.13
=> passed

==> 7/7 tests passed

Test 2a-2g: Find collinear points among the n points on an n-by-1 grid

                                                      slopeTo()
             n    time     slopeTo()   compare()  + 2*compare()        compareTo()
-----------------------------------------------------------------------------------------------
=> passed    64   0.00        4096        4764          13624                 8192         
=> passed   128   0.00       16384       17796          51976                32768         
=> passed   256   0.01       65536       68717         202970               131072         
=> passed   512   0.02      262144      269399         800942               524288         
=> passed  1024   0.05     1048576     1065026        3178628              2097152         
=> passed  2048   0.10     4194304     4231214       12656732              8388608         
=> passed  4096   0.40    16777216    16859163       50495542             33554432         
==> 7/7 tests passed

lg ratio(slopeTo() + 2*compare()) = lg (50495542 / 12656732) = 2.00
=> passed

==> 8/8 tests passed

Test 3a-3g: Find collinear points among the n points on an n/4-by-4 grid

                                                      slopeTo()
             n    time     slopeTo()   compare()  + 2*compare()        compareTo()
-----------------------------------------------------------------------------------------------
=> passed    64   0.00        4096       17774          39644                 4456         
=> passed   128   0.00       16384       73138         162660                17768         
=> passed   256   0.01       65536      284625         634786                71016         
=> passed   512   0.03      262144     1113842        2489828               284008         
=> passed  1024   0.10     1048576     4408781        9866138              1135976         
=> passed  2048   0.38     4194304    17535753       39265810              4543848         
=> passed  4096   1.34    16777216    69922222      156621660             18175336         
==> 7/7 tests passed

lg ratio(slopeTo() + 2*compare()) = lg (156621660 / 39265810) = 2.00
=> passed

==> 8/8 tests passed

Test 4a-4g: Find collinear points among the n points on an n/8-by-8 grid

                                                      slopeTo()
             n    time     slopeTo()   compare()  + 2*compare()        compareTo()
-----------------------------------------------------------------------------------------------
=> passed    64   0.00        4096       18554          41204                 4264         
=> passed   128   0.00       16384       86468         189320                16800         
=> passed   256   0.01       65536      382552         830640                66960         
=> passed   512   0.04      262144     1619507        3501158               267568         
=> passed  1024   0.15     1048576     6777240       14603056              1070040         
=> passed  2048   0.59     4194304    27715048       59624400              4279896         
=> passed  4096   2.26    16777216   112966940      242711096             17119336         
==> 7/7 tests passed

lg ratio(slopeTo() + 2*compare()) = lg (242711096 / 59624400) = 2.03
=> passed

==> 8/8 tests passed

Total: 31/31 tests passed!


================================================================
```
