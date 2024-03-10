//import java.util.ArrayList;
//
//public class DynamicStack {
//    private ArrayList<Integer> stackArray;
//    private int top;
//    private final int MAX_SIZE;
//
//    public DynamicStack(int size) {
//        stackArray = new ArrayList<Integer>();
//        MAX_SIZE = size;
//        top = -1;
//    }
//
//    public void push(int value) {
//        if (top < MAX_SIZE - 1) {
//            top++;
//            stackArray[top] = value;
//        } else {
//            System.out.println("The stack is full. Cannot push " + value + " to the stack.");
//        }
//    }
//
//    public int pop() {
//        if (!isEmpty()) {
//            int value = stackArray[top];
//            top--;
//            return value;
//        } else {
//            System.out.println("The stack is empty. Cannot pop from the stack.");
//            return -1;
//        }
//    }
//
//    public boolean isEmpty() {
//        return top == -1;
//    }
//
//    public int getTop() {
//        if (!isEmpty()) {
//            return stackArray[top];
//        } else {
//            System.out.println("The stack is empty. Cannot get the top element.");
//            return -1;
//        }
//    }
//
//    public int getSize() {
//        return top + 1;
//    }
//
//    public static void main(String[] args) {
//        DynamicStack stack = new DynamicStack(5);
//
//        System.out.println("Pushing elements to the stack...");
//        stack.push(10);
//        stack.push(20);
//        stack.push(30);
//        stack.push(40);
//        stack.push(50);
//        stack.push(60);
//
//        System.out.println("The top element is: " + stack.getTop());
//        System.out.println("The stack size is: " + stack.getSize());
//
//        System.out.println("Popping elements from the stack...");
//        System.out.println(stack.pop());
//        System.out.println(stack.pop());
//        System.out.println(stack.pop());
//        System.out.println(stack.pop());
//        System.out.println(stack.pop());
//
//        System.out.println("The top element is: " + stack.getTop());
//        System.out.println("The stack size is: " + stack.getSize());
//    }
//}