Notes:

- This submission has Junit tests which is not supported by autograder, so
  you'll need to remove the tests.
- I used an array to implement `Deque` but I'm not sure that was ok because
  operations are not constant time or constant memory when we resize. See:

```
Test 7: Worst-case constant memory allocated or deallocated
        per deque operation?
  * 128 random operations
    - failed on trial 24 of 128
    - when current size of Deque was 16 objects;
    - the call to addFirst()
    - caused a change in memory of 128 bytes
    - any change of more than 96 bytes fails the test
  * 256 random operations
    - failed on trial 23 of 256
    - when current size of Deque was 16 objects;
    - the call to addLast()
    - caused a change in memory of 128 bytes
    - any change of more than 96 bytes fails the test
  * 512 random operations
    - failed on trial 28 of 512
    - when current size of Deque was 16 objects;
    - the call to addLast()
    - caused a change in memory of 128 bytes
    - any change of more than 96 bytes fails the test
==> FAILED
```

Score: 82/100

```
See the Assessment Guide for information on how to interpret this report.

ASSESSMENT SUMMARY

Compilation:  PASSED (0 errors, 2 warnings)
API:          PASSED

Findbugs:     PASSED
PMD:          PASSED
Checkstyle:   FAILED (0 errors, 11 warnings)

Correctness:  32/43 tests passed
Memory:       51/53 tests passed
Timing:       114/136 tests passed

Aggregate score: 81.04%
[Compilation: 5%, API: 5%, Findbugs: 0%, PMD: 0%, Checkstyle: 0%, Correctness: 60%, Memory: 10%, Timing: 20%]

ASSESSMENT DETAILS

The following files were submitted:
----------------------------------
5.4K Aug 17 04:45 Deque.java
 929 Aug 17 04:45 Permutation.java
2.6K Aug 17 04:45 RandomizedQueue.java


********************************************************************************
*  COMPILING                                                                    
********************************************************************************


% javac Deque.java
*-----------------------------------------------------------
Deque.java:66: warning: [unchecked] unchecked cast
        array = (Item[])new Object[4];
                        ^
  required: Item[]
  found:    Object[]
  where Item is a type-variable:
    Item extends Object declared in class Deque
Deque.java:107: warning: [unchecked] unchecked cast
        Item[] newArray = (Item[])new Object[capacity];
                                  ^
  required: Item[]
  found:    Object[]
  where Item is a type-variable:
    Item extends Object declared in class Deque
2 warnings

% javac RandomizedQueue.java
*-----------------------------------------------------------

% javac Permutation.java
*-----------------------------------------------------------


================================================================


Checking the APIs of your programs.
*-----------------------------------------------------------
Deque:

RandomizedQueue:

Permutation:

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
[WARN] Deque.java:16:13: The instance (or static) variable 'position' must be private. [VisibilityModifier]
[WARN] Deque.java:45:15: The comment is empty. [IllegalTokenText]
[WARN] Deque.java:66:25: Typecast is not followed by whitespace. [WhitespaceAfter]
[WARN] Deque.java:90:9: Conditional logic can be removed. [SimplifyBooleanReturn]
[WARN] Deque.java:107:35: Typecast is not followed by whitespace. [WhitespaceAfter]
[WARN] RandomizedQueue.java:34:14: The instance (or static) variable 'item' must be private. [VisibilityModifier]
[WARN] RandomizedQueue.java:35:14: The instance (or static) variable 'next' must be private. [VisibilityModifier]
[WARN] RandomizedQueue.java:89:19: '=' is not preceded with whitespace. [WhitespaceAround]
[WARN] RandomizedQueue.java:89:20: '=' is not followed by whitespace. [WhitespaceAround]
[WARN] RandomizedQueue.java:89:24: '<' is not preceded with whitespace. [WhitespaceAround]
[WARN] RandomizedQueue.java:89:25: '<' is not followed by whitespace. [WhitespaceAround]
Checkstyle ends with 0 errors and 11 warnings.

% custom checkstyle checks for Deque.java
*-----------------------------------------------------------

% custom checkstyle checks for RandomizedQueue.java
*-----------------------------------------------------------

% custom checkstyle checks for Permutation.java
*-----------------------------------------------------------


================================================================


********************************************************************************
*  TESTING CORRECTNESS
********************************************************************************

Testing correctness of Deque
*-----------------------------------------------------------
Running 16 total tests.

Tests 1-6 make random calls to addFirst(), addLast(), removeFirst(),
removeLast(), isEmpty(), and size(). The probabilities of each
operation are (p1, p2, p3, p4, p5, p6), respectively.

Test 1: Check random calls to addFirst(), addLast(), and size()
  *    5 random calls (0.4, 0.4, 0.0, 0.0, 0.0, 0.2)
  *   50 random calls (0.4, 0.4, 0.0, 0.0, 0.0, 0.2)
  *  500 random calls (0.4, 0.4, 0.0, 0.0, 0.0, 0.2)
  * 1000 random calls (0.4, 0.4, 0.0, 0.0, 0.0, 0.2)
==> passed

Test 2: Check random calls to addFirst(), removeFirst(), and isEmpty()
  *    5 random calls (0.8, 0.0, 0.1, 0.0, 0.1, 0.0)
  *   50 random calls (0.8, 0.0, 0.1, 0.0, 0.1, 0.0)
  *  500 random calls (0.8, 0.0, 0.1, 0.0, 0.1, 0.0)
  * 1000 random calls (0.8, 0.0, 0.1, 0.0, 0.1, 0.0)
  *    5 random calls (0.1, 0.0, 0.8, 0.0, 0.1, 0.0)
  *   50 random calls (0.1, 0.0, 0.8, 0.0, 0.1, 0.0)
  *  500 random calls (0.1, 0.0, 0.8, 0.0, 0.1, 0.0)
  * 1000 random calls (0.1, 0.0, 0.8, 0.0, 0.1, 0.0)
==> passed

Test 3: Check random calls to addFirst(), removeLast(), and isEmpty()
  *    5 random calls (0.8, 0.0, 0.0, 0.1, 0.1, 0.0)
  *   50 random calls (0.8, 0.0, 0.0, 0.1, 0.1, 0.0)
  *  500 random calls (0.8, 0.0, 0.0, 0.1, 0.1, 0.0)
  * 1000 random calls (0.8, 0.0, 0.0, 0.1, 0.1, 0.0)
  *    5 random calls (0.1, 0.0, 0.0, 0.8, 0.1, 0.0)
  *   50 random calls (0.1, 0.0, 0.0, 0.8, 0.1, 0.0)
  *  500 random calls (0.1, 0.0, 0.0, 0.8, 0.1, 0.0)
  * 1000 random calls (0.1, 0.0, 0.0, 0.8, 0.1, 0.0)
==> passed

Test 4: Check random calls to addLast(), removeLast(), and isEmpty()
  *    5 random calls (0.0, 0.8, 0.0, 0.1, 0.1, 0.0)
  *   50 random calls (0.0, 0.8, 0.0, 0.1, 0.1, 0.0)
  *  500 random calls (0.0, 0.8, 0.0, 0.1, 0.1, 0.0)
  * 1000 random calls (0.0, 0.8, 0.0, 0.1, 0.1, 0.0)
  *    5 random calls (0.0, 0.1, 0.0, 0.8, 0.1, 0.0)
  *   50 random calls (0.0, 0.1, 0.0, 0.8, 0.1, 0.0)
  *  500 random calls (0.0, 0.1, 0.0, 0.8, 0.1, 0.0)
  * 1000 random calls (0.0, 0.1, 0.0, 0.8, 0.1, 0.0)
==> passed

Test 5: Check random calls to addLast(), removeFirst(), and isEmpty()
  *    5 random calls (0.0, 0.8, 0.1, 0.0, 0.1, 0.0)
  *   50 random calls (0.0, 0.8, 0.1, 0.0, 0.1, 0.0)
  *  500 random calls (0.0, 0.8, 0.1, 0.0, 0.1, 0.0)
  * 1000 random calls (0.0, 0.8, 0.1, 0.0, 0.1, 0.0)
  *    5 random calls (0.0, 0.1, 0.8, 0.0, 0.1, 0.0)
  *   50 random calls (0.0, 0.1, 0.8, 0.0, 0.1, 0.0)
  *  500 random calls (0.0, 0.1, 0.8, 0.0, 0.1, 0.0)
  * 1000 random calls (0.0, 0.1, 0.8, 0.0, 0.1, 0.0)
==> passed

Test 6: Check random calls to addFirst(), addLast(), removeFirst(),
        removeLast(), isEmpty(), and size()
  *    5 random calls (0.3, 0.3, 0.1, 0.1, 0.1, 0.1)
  *   50 random calls (0.3, 0.3, 0.1, 0.1, 0.1, 0.1)
  *  500 random calls (0.3, 0.3, 0.1, 0.1, 0.1, 0.1)
  * 1000 random calls (0.3, 0.3, 0.1, 0.1, 0.1, 0.1)
  *    5 random calls (0.1, 0.1, 0.3, 0.3, 0.1, 0.1)
  *   50 random calls (0.1, 0.1, 0.3, 0.3, 0.1, 0.1)
  *  500 random calls (0.1, 0.1, 0.3, 0.3, 0.1, 0.1)
  * 1000 random calls (0.1, 0.1, 0.3, 0.3, 0.1, 0.1)
==> passed

Test 7: Check removeFirst() and removeLast() from an empty deque
  * removeFirst()
  * removeLast()
==> passed

Test 8: Check where two Deque objects can be created at the same time
==> passed

Test 9: Check iterator() after calls only to addFirst()
    java.lang.ArrayIndexOutOfBoundsException: 1024

    Deque$DequeIterator.next(Deque.java:26)
    UtilCOS.compareAsSequences(UtilCOS.java:686)
    UtilCOS.compareAsSequences(UtilCOS.java:679)
    TestDeque.test9(TestDeque.java:356)
    TestDeque.main(TestDeque.java:772)

==> FAILED

Test 10: Check iterator() after intermixed calls to addFirst(), addLast(),
         removeFirst(), and removeLast()
    java.lang.ArrayIndexOutOfBoundsException: 4

    Deque$DequeIterator.next(Deque.java:26)
    UtilCOS.compareAsSequences(UtilCOS.java:686)
    UtilCOS.compareAsSequences(UtilCOS.java:679)
    TestDeque.test10(TestDeque.java:424)
    TestDeque.main(TestDeque.java:773)

    - sequence of dequeue operations was:
          deque.addFirst(1)
          deque.removeLast()    ==> 1
          deque.addLast(3)
          deque.addLast(4)
          deque.addFirst(5)

==> FAILED

Test 11: Create two nested iterators to same deque
  * n = 10
  * n = 1000
==> passed

Test 12: Create two parallel iterators to same deque
  * n = 10
  * n = 1000
==> passed

Test 13: Create Deque objects of different parameterized types
==> passed

Test 14: Call addFirst() and addLast() with null argument
==> passed

Test 15: Check that remove() and next() throw the specified exceptions in iterator()
    - java.util.NoSuchElementException not thrown for next()
==> FAILED

Test 16: Check iterator() when Deque is empty
==> passed


Total: 13/16 tests passed!


================================================================
Testing correctness of RandomizedQueue
*-----------------------------------------------------------
Running 18 total tests.

Tests 1-4 make random calls to enqueue(), dequeue(), sample(),
isEmpty(), and size(). The probabilities of each operation are
(p1, p2, p3, p4, p5), respectively.

Test 1: Check random calls to enqueue() and size()
  *    5 random calls (0.8, 0.0, 0.0, 0.0, 0.2)
  *   50 random calls (0.8, 0.0, 0.0, 0.0, 0.2)
  *  500 random calls (0.8, 0.0, 0.0, 0.0, 0.2)
  * 1000 random calls (0.8, 0.0, 0.0, 0.0, 0.2)
==> passed

Test 2: Check random calls to enqueue() and dequeue()
  *    5 random calls (0.7, 0.1, 0.0, 0.1, 0.1)
  *   50 random calls (0.7, 0.1, 0.0, 0.1, 0.1)
  *  500 random calls (0.7, 0.1, 0.0, 0.1, 0.1)
  * 1000 random calls (0.7, 0.1, 0.0, 0.1, 0.1)
  *    5 random calls (0.1, 0.7, 0.0, 0.1, 0.1)
  *   50 random calls (0.1, 0.7, 0.0, 0.1, 0.1)
  *  500 random calls (0.1, 0.7, 0.0, 0.1, 0.1)
  * 1000 random calls (0.1, 0.7, 0.0, 0.1, 0.1)
==> passed

Test 3: Check random calls to enqueue(), sample(), and size()
  *    5 random calls (0.8, 0.0, 0.1, 0.0, 0.1)
  *   50 random calls (0.8, 0.0, 0.1, 0.0, 0.1)
  *  500 random calls (0.8, 0.0, 0.1, 0.0, 0.1)
  * 1000 random calls (0.8, 0.0, 0.1, 0.0, 0.1)
  *    5 random calls (0.1, 0.0, 0.8, 0.0, 0.1)
  *   50 random calls (0.1, 0.0, 0.8, 0.0, 0.1)
  *  500 random calls (0.1, 0.0, 0.8, 0.0, 0.1)
  * 1000 random calls (0.1, 0.0, 0.8, 0.0, 0.1)
==> passed

Test 4: Check random calls to enqueue(), dequeue(), sample(), isEmpty(), and size()
  *    5 random calls (0.6, 0.1, 0.1, 0.1, 0.1)
  *   50 random calls (0.6, 0.1, 0.1, 0.1, 0.1)
  *  500 random calls (0.6, 0.1, 0.1, 0.1, 0.1)
  * 1000 random calls (0.6, 0.1, 0.1, 0.1, 0.1)
  *    5 random calls (0.1, 0.1, 0.6, 0.1, 0.1)
  *   50 random calls (0.1, 0.1, 0.6, 0.1, 0.1)
  *  500 random calls (0.1, 0.1, 0.6, 0.1, 0.1)
  * 1000 random calls (0.1, 0.1, 0.6, 0.1, 0.1)
==> passed

Test 5: Call dequeue() and sample() from an empty randomized queue
  * dequeue()
  * sample()
==> passed

Test 6: Create multiple randomized queue objects at the same time
==> passed

Test 7: Check that iterator() returns correct items after a sequence of
        enqueue() operations
    - number of entries in student   solution: 0
    - number of entries in reference solution: 1000
    - 1000 missing entries in student solution, including: '1000'

==> FAILED

Test 8: Check that iterator() returns correct items after sequence of enqueue()
        and dequeue() operations
    - number of entries in student   solution: 504
    - number of entries in reference solution: 521
    - 17 missing entries in student solution, including: '999'

==> FAILED

Test 9: Create two nested iterators over the same randomized queue
  * n = 10
    - outer iterator returned fewer than 10 items

  * n = 1000
    - outer iterator returned fewer than 1000 items

==> FAILED

Test 10: Create two parallel iterators over the same randomized queue
  * n = 10
    - student   iterator 1 hasNext() = false
    - student   iterator 2 hasNext() = false
    - reference iterator   hasNext() = true

  * n = 1000
    - student   iterator 1 hasNext() = false
    - student   iterator 2 hasNext() = false
    - reference iterator   hasNext() = true

==> FAILED

Test 11: Create two iterators over different randomized queues
    - number of entries in student   solution: 0
    - number of entries in reference solution: 10
    - 10 missing entries in student solution, including: '9'

    - number of entries in student   solution: 0
    - number of entries in reference solution: 10
    - 10 missing entries in student solution, including: '9'

==> FAILED

Test 12: Create RandomizedQueue objects of different parameterized types
==> passed

Test 13: Check randomness of sample() by enqueueing n items, repeatedly calling
         sample(), and counting the frequency of each item
  * n = 3, trials = 12000

            value  observed  expected   2*O*ln(O/E)
        -------------------------------------------
                A         0    4000.0          0.00
                B      3963    4000.0        -73.66
                C      8037    4000.0      11215.82
        -------------------------------------------
                      12000   12000.0      11142.16
    
    G-statistic = 11142.16 (p-value = 0.000000, reject if p-value <= 0.0001)
    Note: a correct solution will fail this test by bad luck 1 time in 10,000.

  * n = 5, trials = 12000

            value  observed  expected   2*O*ln(O/E)
        -------------------------------------------
                A         0    2400.0          0.00
                B      2385    2400.0        -29.91
                C      2410    2400.0         20.04
                D      2398    2400.0         -4.00
                E      4807    2400.0       6677.93
        -------------------------------------------
                      12000   12000.0       6664.06
    
    G-statistic = 6664.06 (p-value = 0.000000, reject if p-value <= 0.0001)
    Note: a correct solution will fail this test by bad luck 1 time in 10,000.

  * n = 8, trials = 12000

            value  observed  expected   2*O*ln(O/E)
        -------------------------------------------
                A         0    1500.0          0.00
                B      1452    1500.0        -94.45
                C      1488    1500.0        -23.90
                D      1479    1500.0        -41.70
                E      1532    1500.0         64.68
                F      1539    1500.0         79.01
                G      1527    1500.0         54.48
                H      2983    1500.0       4101.41
        -------------------------------------------
                      12000   12000.0       4139.52
    
    G-statistic = 4139.52 (p-value = 0.000000, reject if p-value <= 0.0001)
    Note: a correct solution will fail this test by bad luck 1 time in 10,000.

  * n = 10, trials = 12000

            value  observed  expected   2*O*ln(O/E)
        -------------------------------------------
                A         0    1200.0          0.00
                B      1233    1200.0         66.90
                C      1195    1200.0         -9.98
                D      1180    1200.0        -39.66
                E      1207    1200.0         14.04
                F      1193    1200.0        -13.96
                G      1229    1200.0         58.70
                H      1174    1200.0        -51.43
                I      1216    1200.0         32.21
                J      2373    1200.0       3235.98
        -------------------------------------------
                      12000   12000.0       3292.79
    
    G-statistic = 3292.79 (p-value = 0.000000, reject if p-value <= 0.0001)
    Note: a correct solution will fail this test by bad luck 1 time in 10,000.

==> FAILED

Test 14: Check randomness of dequeue() by enqueueing n items, dequeueing n items,
         and seeing whether each of the n! permutations is equally likely
  * n = 2, trials = 12000
  * n = 3, trials = 12000
  * n = 4, trials = 12000
  * n = 5, trials = 12000
==> passed

Test 15: Check randomness of iterator() by enqueueing n items, iterating over those
         n items, and seeing whether each of the n! permutations is equally likely
  * n = 2, trials = 12000
    java.lang.NullPointerException

    TestRandomizedQueue.checkRandomnessOfIterator(TestRandomizedQueue.java:856)
    TestRandomizedQueue.test15(TestRandomizedQueue.java:920)
    TestRandomizedQueue.main(TestRandomizedQueue.java:1084)

  * n = 3, trials = 12000
    java.lang.NullPointerException

    TestRandomizedQueue.checkRandomnessOfIterator(TestRandomizedQueue.java:856)
    TestRandomizedQueue.test15(TestRandomizedQueue.java:921)
    TestRandomizedQueue.main(TestRandomizedQueue.java:1084)

  * n = 4, trials = 12000
    java.lang.NullPointerException

    TestRandomizedQueue.checkRandomnessOfIterator(TestRandomizedQueue.java:856)
    TestRandomizedQueue.test15(TestRandomizedQueue.java:922)
    TestRandomizedQueue.main(TestRandomizedQueue.java:1084)

  * n = 5, trials = 12000
    java.lang.NullPointerException

    TestRandomizedQueue.checkRandomnessOfIterator(TestRandomizedQueue.java:856)
    TestRandomizedQueue.test15(TestRandomizedQueue.java:923)
    TestRandomizedQueue.main(TestRandomizedQueue.java:1084)

==> FAILED

Test 16: Call enqueue() with a null argument
==> passed

Test 17: Check that remove() and next() throw the specified exceptions in iterator()
==> passed

Test 18: Check iterator() when RandomizedQueue is empty
    - hasNext() returns true
==> FAILED


Total: 10/18 tests passed!


================================================================
********************************************************************************
*  TESTING CORRECTNESS (substituting reference RandomizedQueue and Deque)
********************************************************************************

Testing correctness of Permutation
*-----------------------------------------------------------
Tests 1-5 call the main() function directly, resetting standard input
before each call.

Running 9 total tests.

Test 1a: Check formatting for sample inputs from assignment specification
  % java Permutation 3 < distinct.txt
  E
  D
  B

  % java Permutation 3 < distinct.txt
  B
  C
  H

  % java Permutation 8 < duplicates.txt
  BB
  BB
  AA
  BB
  BB
  BB
  CC
  CC

==> passed

Test 1b: Check formatting for other inputs
  % java Permutation 8 < mediumTale.txt
  was
  of
  wisdom
  the
  times
  the
  times
  was

  % java Permutation 0 < distinct.txt
  [no output]

==> passed

Test 2: Check that main() reads all data from standard input
  * filename = distinct.txt, k = 3
  * filename = distinct.txt, k = 3
  * filename = duplicates.txt, k = 8
  * filename = mediumTale.txt, k = 8
==> passed

Test 3a: Check that main() prints each item from the sequence at most once
         (for inputs with no duplicate strings)
  * filename = distinct.txt, k = 3
  * filename = distinct.txt, k = 1
  * filename = distinct.txt, k = 9
  * filename = permutation6.txt, k = 6
  * filename = permutation10.txt, k = 10
==> passed

Test 3b: Check that main() prints each item from the sequence at most once
         (for inputs with duplicate strings)
  * filename = duplicates.txt, k = 8
  * filename = duplicates.txt, k = 3
  * filename = permutation8.txt, k = 6
  * filename = permutation8.txt, k = 2
  * filename = tinyTale.txt, k = 10
==> passed

Test 3c: Check that main() prints each item from the sequence at most once
         (for inputs with newlines)
  * filename = mediumTale.txt, k = 10
  * filename = mediumTale.txt, k = 20
  * filename = tale.txt, k = 10
  * filename = tale.txt, k = 50
==> passed

Test 4: Check main() when k = 0
  * filename = distinct.txt, k = 0
  * filename = distinct.txt, k = 0
==> passed

Test 5a: Check that permutations are uniformly random
         (for inputs with no duplicate strings)
  * filename = permutation4.txt, k = 1
  * filename = permutation4.txt, k = 2
  * filename = permutation4.txt, k = 3
  * filename = permutation4.txt, k = 4
  * filename = permutation6.txt, k = 2
==> passed

Test 5b: Check that permutations are uniformly random
         (for inputs with duplicate strings)
  * filename = permutation5.txt, k = 1
  * filename = permutation5.txt, k = 2
  * filename = permutation5.txt, k = 3
  * filename = duplicates.txt, k = 3
  * filename = permutation8.txt, k = 2
==> passed

Total: 9/9 tests passed!


================================================================
********************************************************************************
*  MEMORY
********************************************************************************

Analyzing memory of Permutation
*-----------------------------------------------------------
Running 2 total tests.

Test 1: Check that only one Deque or RandomizedQueue object is created
  * filename = distinct.txt, n = 9, k = 1
  * filename = distinct.txt, n = 9, k = 2
  * filename = distinct.txt, n = 9, k = 4
  * filename = tinyTale.txt, n = 12, k = 10
  * filename = tale.txt, n = 138653, k = 50
==> passed

Test 2: Check that the maximum size of any Deque or RandomizedQueue object
        created is between k and n
  * filename = distinct.txt, n = 9, k = 1
  * filename = distinct.txt, n = 9, k = 2
  * filename = distinct.txt, n = 9, k = 4
  * filename = tinyTale.txt, n = 12, k = 10
  * filename = tale.txt, n = 138653, k = 5
  * filename = tale.txt, n = 138653, k = 50
  * filename = tale.txt, n = 138653, k = 500
  * filename = tale.txt, n = 138653, k = 5000
  * filename = tale.txt, n = 138653, k = 50000
==> passed

Test 3 (bonus): Check that maximum size of any or Deque or RandomizedQueue object
                created is equal to k
  * filename = tale.txt, n = 138653, k = 5
    - max size of RandomizedQueue object = 138653

  * filename = tale.txt, n = 138653, k = 50
    - max size of RandomizedQueue object = 138653

  * filename = tale.txt, n = 138653, k = 500
    - max size of RandomizedQueue object = 138653

  * filename = tale.txt, n = 138653, k = 5000
    - max size of RandomizedQueue object = 138653

  * filename = tale.txt, n = 138653, k = 50000
    - max size of RandomizedQueue object = 138653

==> FAILED

Total: 2/2 tests passed!

================================================================



********************************************************************************
*  TESTING CORRECTNESS (substituting reference RandomizedQueue and Deque)                                                                  
********************************************************************************

Timing Permutation
*-----------------------------------------------------------
Running 23 total tests.

Test 1: Count calls to methods in StdIn
  * java Permutation 5 < distinct.txt
    - main() should call readString() once per string
    - number of strings            = 9
    - number of readString() calls = 10

  * java Permutation 10 < permutation10.txt
    - main() should call readString() once per string
    - number of strings            = 10
    - number of readString() calls = 11

  * java Permutation 1 < mediumTale.txt
    - main() should call readString() once per string
    - number of strings            = 24
    - number of readString() calls = 25

  * java Permutation 20 < tale.txt
    - main() should call readString() once per string
    - number of strings            = 138653
    - number of readString() calls = 138654

  * java Permutation 100 < tale.txt
    - main() should call readString() once per string
    - number of strings            = 138653
    - number of readString() calls = 138654

  * java Permutation 16412 < tale.txt
    - main() should call readString() once per string
    - number of strings            = 138653
    - number of readString() calls = 138654

==> FAILED

Test 2: Count calls to methods in Deque and RandomizedQueue
  * java Permutation 5 < distinct.txt
  * java Permutation 10 < permutation10.txt
  * java Permutation 1 < mediumTale.txt
  * java Permutation 20 < tale.txt
  * java Permutation 100 < tale.txt
  * java Permutation 16412 < tale.txt
==> passed

Test 3: Count calls to methods in StdRandom
  * java Permutation 5 < distinct.txt
  * java Permutation 10 < permutation10.txt
  * java Permutation 1 < mediumTale.txt
  * java Permutation 20 < tale.txt
  * java Permutation 100 < tale.txt
  * java Permutation 16412 < tale.txt
==> passed

Test 4: Time main() with k = 5, for inputs containing n random strings

                    n  seconds
------------------------------
=> passed        1000     0.00
=> passed        2000     0.00
=> passed        4000     0.00
=> passed        8000     0.00
=> passed       16000     0.01
=> passed       32000     0.01
=> passed       64000     0.02
=> passed      128000     0.04
=> passed      256000     0.07
=> passed      512000     0.15
==> 10/10 tests passed


Test 5: Time main() with k = 1000, for inputs containing n random strings

                    n  seconds
------------------------------
=> passed        1000     0.00
=> passed        2000     0.00
=> passed        4000     0.00
=> passed        8000     0.00
=> passed       16000     0.01
=> passed       32000     0.01
=> passed       64000     0.02
=> passed      128000     0.04
=> passed      256000     0.12
=> passed      512000     0.30
==> 10/10 tests passed


Total: 22/23 tests passed!


================================================================



********************************************************************************
*  MEMORY
********************************************************************************

Analyzing memory of Deque
*-----------------------------------------------------------
For tests 1-4, the maximum amount of memory allowed for a Deque
containing n items is 48n + 192.

Running 28 total tests.

Test 1a-1e: Total memory usage after inserting n items,
            where n is a power of 2.

                 n        bytes
----------------------------------------------------------
=> passed        8          120         
=> passed       64          568         
=> passed      256         2104         
=> passed     1024         8248         
=> passed     4096        32824         
==> 5/5 tests passed

Memory: 8.00 n + 56.00   (R^2 = 1.000)



Test 2a-2e: Total memory usage after inserting n+1 items,
            where n is a power of 2.

                 n        bytes
----------------------------------------------------------
=> passed        8          184         
=> passed       64         1080         
=> passed      256         4152         
=> passed     1024        16440         
=> passed     4096        65592         
==> 5/5 tests passed

Memory after adding n = 2^i + 1 items: 16.00 n + 40.00   (R^2 = 1.000)



Test 3a-3e: Total memory usage after inserting 2n+1 items
            and deleting n items, where n is a power of 2.

                 n        bytes
----------------------------------------------------------
=> passed        8          312         
=> passed       64         2104         
=> passed      256         8248         
=> passed     1024        32824         
=> passed     4096       131128         
==> 5/5 tests passed

Memory: 32.00 n + 24.00   (R^2 = 1.000)



Test 4a-4e: Total memory usage after inserting n items and then
            deleting all but one item, where n is a power of 2.
            (should not grow with n or be too large of a constant)

                 n        bytes
----------------------------------------------------------
=> passed        8           88         
=> passed       64           88         
=> passed      256           88         
=> passed     1024           88         
=> passed     4096           88         
==> 5/5 tests passed

Memory after adding n = 2^i items: 88.00   (R^2 = 1.000)



Test 5a-5e: Total memory usage of iterator after inserting n items.
            (should not grow with n or be too large of a constant)

                 n        bytes
----------------------------------------------------------
=> passed        8           32         
=> passed       64           32         
=> passed      256           32         
=> passed     1024           32         
=> passed     4096           32         
==> 5/5 tests passed

Memory of iterator after adding n = 2^i items: 32.00   (R^2 = 1.000)



Test 6a: Insert n strings; delete them one at a time, checking for
         loitering after each deletion. The probabilities of addFirst()
         and addLast() are (p1, p2), respectively. The probabilities of
         removeFirst() and removeLast() are (q1, q2), respectively
  * 100 random insertions (1.0, 0.0) and 100 random deletions (1.0, 0.0)
  * 100 random insertions (1.0, 0.0) and 100 random deletions (0.0, 1.0)
  * 100 random insertions (0.0, 1.0) and 100 random deletions (1.0, 0.0)
  * 100 random insertions (0.0, 1.0) and 100 random deletions (0.0, 1.0)
  * 100 random insertions (0.5, 0.5) and 100 random deletions (0.5, 0.5)
==> passed

Test 6b: Perform random operations, checking for loitering after
         each operation. The probabilities of addFirst(), addLast(),
         removeFirst(), and removeLast() are (p1, p2, p3, p4),
         respectively.
  * 100 random operations (0.8, 0.0, 0.2, 0.0)
  * 100 random operations (0.8, 0.0, 0.0, 0.2)
  * 100 random operations (0.0, 0.8, 0.2, 0.0)
  * 100 random operations (0.0, 0.8, 0.0, 0.2)
  * 100 random operations (0.4, 0.4, 0.1, 0.1)
  * 100 random operations (0.2, 0.2, 0.3, 0.3)
==> passed

Test 7: Worst-case constant memory allocated or deallocated
        per deque operation?
  * 128 random operations
    - failed on trial 24 of 128
    - when current size of Deque was 16 objects;
    - the call to addFirst()
    - caused a change in memory of 128 bytes
    - any change of more than 96 bytes fails the test
  * 256 random operations
    - failed on trial 23 of 256
    - when current size of Deque was 16 objects;
    - the call to addLast()
    - caused a change in memory of 128 bytes
    - any change of more than 96 bytes fails the test
  * 512 random operations
    - failed on trial 28 of 512
    - when current size of Deque was 16 objects;
    - the call to addLast()
    - caused a change in memory of 128 bytes
    - any change of more than 96 bytes fails the test
==> FAILED

Total: 27/28 tests passed!

================================================================



Analyzing memory of RandomizedQueue
*-----------------------------------------------------------
For tests 1-4, the maximum amount of memory allowed for
a RandomizedQueue containing n items is 48n + 192.

Running 23 total tests.

Test 1a-1d: Total memory usage after inserting n integers.

                 n        bytes
----------------------------------------------------------
=> passed       64         2592         
=> passed      256        10272         
=> passed     1024        40992         
=> passed     4096       163872         
==> 4/4 tests passed


Memory: 40.00 n + 32.00   (R^2 = 1.000)



Test 2a-2d: Total memory usage after inserting n+1 items.

                 n        bytes
----------------------------------------------------------
=> passed       64         2632         
=> passed      256        10312         
=> passed     1024        41032         
=> passed     4096       163912         
==> 4/4 tests passed


Memory: 40.00 n + 72.00   (R^2 = 1.000)



Test 3a-3d: Total memory usage after inserting 2n+1 items, and
            then deleting n items.

                 n        bytes
----------------------------------------------------------
=> passed       64         2632         
=> passed      256        10312         
=> passed     1024        41032         
=> passed     4096       163912         
==> 4/4 tests passed


Memory: 40.00 n + 72.00   (R^2 = 1.000)



Test 4a-4d: Total memory usage after inserting n items, and
            then deleting all but one item.

                 n        bytes
----------------------------------------------------------
=> passed       64           72         
=> passed      256           72         
=> passed     1024           72         
=> passed     4096           72         
==> 4/4 tests passed


Memory: 72.00   (R^2 = 1.000)



Test 5a-5d: Total memory usage of iterator after inserting n items.

                 n        bytes
----------------------------------------------------------
=> passed       64           24         
=> passed      256           24         
=> passed     1024           24         
=> passed     4096           24         
==> 4/4 tests passed


Memory: 24.00   (R^2 = 1.000)



Test 6a: Insert 100 strings; delete them one at a time, checking
         for loitering after each deletion.
==> passed

Test 6b: Perform random operations, checking for loitering after
         each operation. The probabilities of enqueue(), dequeue(),
         and sample() are (p1, p2, p3), respectively.
  * 200 random operations (0.8, 0.2, 0.0)
  * 200 random operations (0.2, 0.8, 0.0)
  * 200 random operations (0.6, 0.2, 0.2)
  * 200 random operations (0.2, 0.4, 0.4)
==> passed

Test 7: Insert T items into queue; then iterate over queue and check
        that worst-case constant memory is allocated or deallocated
        per iterator operation.
  * T = 64
    java.util.NoSuchElementException

    RandomizedQueue$RandomizedQueueIterator.next(RandomizedQueue.java:19)
    MemoryOfRandomizedQueue.constant(MemoryOfRandomizedQueue.java:557)
    MemoryOfRandomizedQueue.test7(MemoryOfRandomizedQueue.java:589)
    MemoryOfRandomizedQueue.main(MemoryOfRandomizedQueue.java:619)

  * T = 128
    java.util.NoSuchElementException

    RandomizedQueue$RandomizedQueueIterator.next(RandomizedQueue.java:19)
    MemoryOfRandomizedQueue.constant(MemoryOfRandomizedQueue.java:557)
    MemoryOfRandomizedQueue.test7(MemoryOfRandomizedQueue.java:590)
    MemoryOfRandomizedQueue.main(MemoryOfRandomizedQueue.java:619)

  * T = 256
    java.util.NoSuchElementException

    RandomizedQueue$RandomizedQueueIterator.next(RandomizedQueue.java:19)
    MemoryOfRandomizedQueue.constant(MemoryOfRandomizedQueue.java:557)
    MemoryOfRandomizedQueue.test7(MemoryOfRandomizedQueue.java:591)
    MemoryOfRandomizedQueue.main(MemoryOfRandomizedQueue.java:619)

==> FAILED

Total: 22/23 tests passed!

================================================================



********************************************************************************
*  TIMING                                                                  
********************************************************************************

Timing Deque
*-----------------------------------------------------------
Running 55 total tests.

Test 1a-1g: Make n random calls to addFirst(), removeFirst(), isEmpty(), and size()
            with probabilities (0.7, 0.1, 0.1, 0.1)

                    n  seconds
------------------------------
=> passed        1024     0.00
=> passed        2048     0.00
=> passed        4096     0.00
=> passed        8192     0.00
=> passed       16384     0.00
=> passed       32768     0.00
=> passed       65536     0.01
=> passed      128000     0.01
=> passed      256000     0.01
=> passed      512000     0.03
=> passed     1024000     0.06
=> passed     2048000     0.12
==> 12/12 tests passed


Test 2a-2g: Make n random calls to addFirst(), removeFirst(), isEmpty(), and size(),
            with probabilities (0.7, 0.1, 0.1, 0.1)

                    n  seconds
------------------------------
=> passed        1024     0.00
=> passed        2048     0.00
=> passed        4096     0.00
=> passed        8192     0.00
=> passed       16384     0.00
=> passed       32768     0.00
=> passed       65536     0.00
=> passed      128000     0.01
=> passed      256000     0.01
=> passed      512000     0.03
=> passed     1024000     0.05
=> passed     2048000     0.08
==> 12/12 tests passed


Test 3a-3g: Make n random calls to addFirst(), addLast(), removeFirst(), removeLast(),
            isEmpty(), and size() with probabilities (0.3, 0.3, 0.1, 0.1, 0.1, 0.1)

                    n  seconds
------------------------------
=> passed        1024     0.00
=> passed        2048     0.00
=> passed        4096     0.00
=> passed        8192     0.00
=> passed       16384     0.00
=> passed       32768     0.00
=> passed       65536     0.00
=> passed      128000     0.01
=> passed      256000     0.01
=> passed      512000     0.02
=> passed     1024000     0.04
=> passed     2048000     0.08
==> 12/12 tests passed


Test 4a-4g: Create a deque of n objects, then iterate over the n objects
            by calling next() and hasNext().

                    n  seconds
------------------------------
    java.lang.ArrayIndexOutOfBoundsException: 1024

    Deque$DequeIterator.next(Deque.java:26)
    TimeDeque.runIterator(TimeDeque.java:76)
    TimeDeque.test4(TimeDeque.java:198)
    TimeDeque.main(TimeDeque.java:260)

    java.lang.ArrayIndexOutOfBoundsException: 1024

    Deque$DequeIterator.next(Deque.java:26)
    TimeDeque.runIterator(TimeDeque.java:76)
    TimeDeque.test4(TimeDeque.java:199)
    TimeDeque.main(TimeDeque.java:260)

=> FAILED        1024 Infinity
   [ Most likely one of your operations is not constant time. ]
==> 0/12 tests passed


Test 5a-5g: Create a deque of n objects, then interleave n calls each to
            removeFirst()/removeLast() and addFirst()/addLast().

                    n  seconds
----------------------------------
=> passed        1025     0.00
=> passed        2049     0.00
=> passed        4097     0.00
=> passed       16385     0.00
=> passed       32767     0.00
=> passed       32768     0.00
=> passed       32769     0.00
==> 7/7 tests passed

Total: 43/55 tests passed!


================================================================



Timing RandomizedQueue
*-----------------------------------------------------------
Running 58 total tests.

Test 1: Call enqueue() and dequeue() n times each; count calls to StdRandom
  * n = 10
  * n = 100
  * n = 1000
==> passed

Test 2: Call sample() n times and count calls to StdRandom
  * n = 10
  * n = 100
  * n = 1000
==> passed

Test 3: Iterate over queue of size n and count calls to StdRandom
  * n = 10
  * n = 100
  * n = 1000
==> passed

Test 4a-g: Make n random calls to enqueue(), sample(), dequeue(), isEmpty(),
           and size() with probabilities (0.2, 0.2, 0.2, 0.2, 0.2)

                    n  seconds
----------------------------------
=> passed        1024     0.00
=> passed        2048     0.00
=> passed        4096     0.00
=> passed        8192     0.00
=> passed       16384     0.00
=> passed       32768     0.00
=> passed       65536     0.01
=> passed      128000     0.01
=> passed      256000     0.02
=> passed      512000     0.05
=> passed     1024000     0.13
=> passed     2048000     0.43
==> 12/12 tests passed


Test 5a-g: Make n random calls to enqueue(), sample(), dequeue(), isEmpty(),
           and size() with probabilities (0.6, 0.1, 0.1, 0.1, 0.1)

                    n  seconds
----------------------------------
=> passed        1024     0.00
=> passed        2048     0.00
=> passed        4096     0.00
=> passed        8192     0.00
=> passed       16384     0.02
=> passed       32768     0.06
=> passed       65536     0.25
=> FAILED      128000     0.94
   [ Most likely one of your operations is not constant time. ]
==> 7/12 tests passed


Test 6a-g: Make n random calls to enqueue(), sample(), dequeue(), isEmpty(),
            and size() with probabilities (0.1, 0.1, 0.6, 0.1, 0.1)

                    n  seconds
----------------------------------
=> passed        1024     0.00
=> passed        2048     0.00
=> passed        4096     0.00
=> passed        8192     0.00
=> passed       16384     0.00
=> passed       32768     0.00
=> passed       65536     0.01
=> passed      128000     0.01
=> passed      256000     0.02
=> passed      512000     0.05
=> passed     1024000     0.14
=> FAILED     2048000     0.88
   [ Most likely one of your operations is not constant time. ]
==> 11/12 tests passed


Test 7a-g: Create randomized queue of n objects, then iterate
           over the n objects by calling next() and hasNext().

                    n  seconds
----------------------------------
=> passed        1024     0.00
=> passed        2048     0.00
=> passed        4096     0.00
=> passed        8192     0.00
=> passed       16384     0.00
=> passed       32768     0.00
=> passed       65536     0.00
=> passed      128000     0.01
=> passed      256000     0.01
=> passed      512000     0.03
=> passed     1024000     0.02
=> passed     2048000     0.01
==> 12/12 tests passed


Test 8a-g: Create randomized queue of n objects, then interleave
           n calls each to dequeue() and enqueue().

                    n  seconds
----------------------------------
=> passed        1025     0.00
=> passed        2049     0.01
=> passed        4097     0.02
=> passed       16385     0.41
=> FAILED       32767     1.61
   [ Most likely one of your operations is not constant time. ]
==> 4/7 tests passed

Total: 49/58 tests passed!


================================================================
```
