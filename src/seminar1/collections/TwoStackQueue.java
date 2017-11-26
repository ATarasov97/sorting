package seminar1.collections;

import java.util.Iterator;

public class TwoStackQueue<Item> implements IQueue<Item> {

    private IStack<Item> stack1;
    private IStack<Item> stack2;

    public TwoStackQueue() {
        stack1 = new ArrayStack<>();
        stack2 = new ArrayStack<>();
    }

    @Override
    public void enqueue(Item item) {
        stack1.push(item);
    }

    @Override
    public Item dequeue() {
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        return stack2.pop();
    }

    @Override
    public boolean isEmpty() {
        return (stack1.isEmpty() && stack2.isEmpty());
    }

    @Override
    public int size() {
        return (stack1.size() + stack2.size());
    }

    @Override
    public Iterator<Item> iterator() {
        /*TODO - optional*/
        return null;
    }

}
