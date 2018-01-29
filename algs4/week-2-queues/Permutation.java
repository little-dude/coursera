/******************************************************************************
 *  Compilation:  javac Permutation.java
 *  Execution:    java Permutation 5 < input.txt
 *  Dependencies: RandomizedQueue.java
 ******************************************************************************/
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;


public class Permutation {

    public static void main(String[] args) {
        int size = Integer.parseInt(args[0]);
        RandomizedQueue<String> q = new RandomizedQueue<>();

        String word;
        while (true) {
            try {
                word = StdIn.readString();
            } catch (NoSuchElementException e) {
                break;
            }
            q.enqueue(word);
        }

        for (int i=0; i<size; i++) {
            StdOut.printf("%s\n", q.dequeue());
        }
    }

}
