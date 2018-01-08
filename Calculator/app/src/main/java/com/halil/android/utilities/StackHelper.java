package com.halil.android.utilities;

/**
 * Created by halil on 08.01.2018.
 */

public class StackHelper {
    private int maxSize;
    private Character[] stackArray;
    private int top;

    public StackHelper(int s) {
        maxSize = s;
        stackArray = new Character[maxSize];
        top = -1;
    }
    public void push(Character j) {
        stackArray[++top] = j;
    }
    public Character pop() {
        return stackArray[top--];
    }
    public Character peek() {
        return stackArray[top];
    }
    public boolean isEmpty() {
        return (top == -1);
    }
    public boolean isFull() {
        return (top == maxSize - 1);
    }


}
