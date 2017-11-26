package seminar1;

import java.util.Iterator;

import seminar1.collections.ArrayPriorityQueue;
import seminar1.collections.CyclicArrayDeque;
import seminar1.collections.CyclicArrayQueue;
import seminar1.collections.IDeque;
import seminar1.collections.IPriorityQueue;
import seminar1.collections.IQueue;
import seminar1.collections.IStack;
import seminar1.collections.LinkedDeque;
import seminar1.collections.LinkedQueue;
import seminar1.collections.LinkedStack;
import seminar1.collections.TwoStackQueue;
import seminar1.iterators.IncreasingIterator;
import seminar1.iterators.MergingIncreasingIterator;
import seminar1.iterators.MergingPeekingIncreasingIterator;
import seminar1.iterators.PeekingIncreasingIterator;

public class Main {

    public static void main(String[] args) {
        System.out.println("LinkedStack");
        IStack<Integer> stack = new LinkedStack<>();
        for (int i = 0; i < 20; i++) {
            stack.push(i);
        }
        for (int i : stack) {
            System.out.print(i + " ");
        }
        System.out.println();
//        for (int i = 0; i < 30; i++) {
//            System.out.print(stack.pop() + " ");
//        }
        System.out.println();

        System.out.println();
        System.out.println("LinkedQueue");
        IQueue<Integer> queue = new LinkedQueue<>();
        for (int i = 0; i < 20; i++) {
            queue.enqueue(i);
        }
//        for (int i : queue) {
//            System.out.print(i + " ");
//        }
        System.out.println();
        for (int i = 0; i < 20; i++) {
            System.out.print(queue.dequeue() + " ");
        }
        System.out.println();

        System.out.println();
        System.out.println("TwoStackQueue");
        queue = new TwoStackQueue<>();
        for (int i = 0; i < 20; i++) {
            queue.enqueue(i);
        }
        for (int i = 0; i < 20; i++) {
            System.out.print(queue.dequeue() + " ");
        }

        System.out.println();
        System.out.println("CyclicArrayDeque");
        queue = new CyclicArrayQueue<>();
        for (int i = 0; i < 20; i++) {
            queue.enqueue(i);
        }
        for (int i = 0; i < 20; i++) {
            System.out.print(queue.dequeue() + " ");
            //((CyclicArrayQueue) queue).test();
        }
//        for (int i : queue) {
//            System.out.print(i + " ");
//        }

        System.out.println();
        System.out.println("MergingIncreasingIterator");
        Iterator first = new IncreasingIterator(1, 2, 100);
        Iterator second = new IncreasingIterator(1, 4, 100);
        Iterator mergingIterator = new MergingIncreasingIterator((IncreasingIterator) first, (IncreasingIterator) second);
        while (mergingIterator.hasNext()) {
            System.out.print(mergingIterator.next() + " ");
        }

        System.out.println();
        System.out.println("Linked Deque");
        IDeque<Integer> deque = new LinkedDeque<>();
//        for (int i = 0; i < 20; i++) {
//            deque.pushBack(i);
//        }
        for (int i = 0; i < 20; i++) {
            deque.pushFront(i);
        }
        for (int i = 0; i < 20; i++) {
            System.out.print(deque.popBack() + " ");
        }
//        for (int i = 0; i < 40; i++) {
//            System.out.print(deque.popFront() + " ");
//        }
//        for (int i:deque) {
//            System.out.print(i + " ");
//        }

        System.out.println();
        System.out.println("CyclicArray Deque");
        deque = new CyclicArrayDeque<>();
        for (int i = 0; i < 20; i++) {
            deque.pushBack(i);
        }
        for (int i = 0; i < 20; i++) {
            deque.pushFront(i);
        }
//        for (int i = 0; i < 20; i++) {
//            System.out.print(deque.popBack() + " ");
//        }
//        for (int i = 0; i < 40; i++) {
//            System.out.print(deque.popFront() + " ");
//        }
        for (int i:deque) {
            System.out.print(i + " ");
        }

        System.out.println();
        System.out.println("ArrayPriorityQueue");
        IPriorityQueue<Integer> pqueue = new ArrayPriorityQueue<>();
        for (int i = 20; i > 0; i--) {
            pqueue.add(i);
        }
        for (int i = 0; i < 20; i++) {
            System.out.print("\"" + pqueue.peek() + " " + pqueue.extractMin() + "\" ");
        }

        System.out.println();
        System.out.println("MergingIncreasingIterator");
        first = new PeekingIncreasingIterator(1, 2, 100);
        second = new PeekingIncreasingIterator(1, 4, 100);
        mergingIterator = new MergingPeekingIncreasingIterator((PeekingIncreasingIterator) first, (PeekingIncreasingIterator) second);
        while (mergingIterator.hasNext()) {
            System.out.print(mergingIterator.next() + " ");
        }

    }
}
