
public class MyCircularDeque {

    private int[] arr;
    int cap;
    int front;
    int rear;

    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque(int k) {

        cap = k + 1;
        arr = new int[cap];
        front = 0;
        rear = 0;

    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {

        if(isFull()){
            return false;
        }

        front = (front - 1 + cap) % cap;
        arr[front] = value;
        return true;

    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {

        if(isFull()){
            return false;
        }

        arr[rear] = value;
        rear = (rear + 1) % cap;
        return true;

    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {

        if(isEmpty()){
            return false;
        }

        front = (front + 1) % cap;
        return true;

    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {

        if(isEmpty()){
            return false;
        }

        rear = (rear - 1 + cap) % cap;
        return true;
    }

    /** Get the front item from the deque. */
    public int getFront() {
        if(isEmpty()){
            return -1;
        }
        return arr[front];
    }

    /** Get the last item from the deque. */
    public int getRear() {
        if (isEmpty()){
            return -1;
        }
        return arr[(rear - 1 + cap) % cap];
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return front == rear;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return (rear + 1) % cap == front;
    }

    public int[] getArr(){
        return arr;
    }

    public static void main(String[] args) {
        MyCircularDeque queue = new MyCircularDeque(5);
        queue.insertFront(1);
        int[] arr = queue.getArr();
        queue.insertLast(2);
        queue.insertFront(3);
        System.out.println(queue.getFront());
        queue.deleteFront();
        System.out.println(queue.getFront());
        for (int a : arr){
            System.out.print(a + ",");
        }



    }
}
