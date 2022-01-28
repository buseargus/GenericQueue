package DataStructures.Stack;

public class StackGeneric<T> {
    private int maxSize;
    private int stackPointer = 0;
    private T[] stackItems;

    public StackGeneric(){
        this(100);
    }

    public int getMaxSize() {
        return maxSize;
    }

    public int getStackPointer() {
        return stackPointer;
    }

    public StackGeneric(int maxSize) {
        this.maxSize = maxSize;
        stackItems = (T[]) new Object[maxSize];
    }

    public void push(T item) {
        if(stackPointer >= maxSize) {
            throw new StackOverflowError();
        }
        stackItems[stackPointer] = item;
        stackPointer++;
    }

    public T pop() {
        stackPointer--;
        if (stackPointer >= 0) {
            return stackItems[stackPointer];
        }
        stackPointer = 0;
        System.out.println("Cannot pop an empty stack");
        return null;
    }

    public T peek() {
        return stackItems[stackPointer - 1];
    }

    public boolean isEmpty() {
        return stackPointer == 0;
    }
}
