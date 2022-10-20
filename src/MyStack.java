// Copyright (C) 2019 Matthias Denu & Steven Than
// Just don't plagiarize us for your homework assignment.

import java.util.LinkedList;

/**
 * This class represents a Stack data structure.
 */
public class MyStack<T> {
  private LinkedList<T> stack;

  /**
   * Constructs a MyStack object.
   */
  public MyStack() {
    this.stack = new LinkedList<>();
  }

  /**
   * Pushes the item onto the top of the Stack.
   *
   * @param t the item to be pushed
   */
  public void push(T t) {
    this.stack.addLast(t);
  }

  /**
   * Gets size of the Stack.
   *
   * @return the size of the stack as int
   */
  public int size() {
    return this.stack.size();
  }

  /**
   * Checks if the Stack is empty.
   *
   * @return true if Stack is empty, false otherwise
   */
  public boolean isEmpty() {
    return this.stack.isEmpty();
  }

  /**
   * Peeks to see what's the first object at the top of the Stack.
   *
   * @return the item at the top of the Stack as type T without actually removing it
   */
  public T top() {
    return this.stack.peekLast();
  }

  /**
   * Removes the first object at the top of the Stack.
   *
   * @return the item at the top of the Stack as type T and removes it from the Stack
   */
  public T pop() {
    return this.stack.removeLast();
  }
}
