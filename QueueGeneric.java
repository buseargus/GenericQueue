package DataStructures.Queue;

public class QueueGeneric<T> {

    private int maxSize;
    private T[] queueArray;
    private int front, rear;
    private int itemNumber;

    public QueueGeneric(int maxSize) {
        this.maxSize = maxSize;
        queueArray = (T[]) new Object[maxSize];
        front = 0;
        rear = -1;
        itemNumber = 0;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public int getFront() {
        return front;
    }

    public int getRear() {
        return rear;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public void enqueue(T j) {
        if(rear == maxSize -1) {
            rear = -1;
        }
        rear++;
        queueArray[rear] = j;
        itemNumber++;
    }

    public T dequeue() {
        T temp = queueArray[front];
        front++;
        if(front == maxSize) {
            front = 0;
        }
        itemNumber--;
        return temp;
    }

    public boolean isEmpty() {
        return itemNumber == 0;
    }
}
