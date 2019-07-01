package com.tts.logdome.common.queue.stack;

import java.util.EmptyStackException;

/**
 * @创建人 pc801
 * @创建时间 2019/6/28
 * @描述
 */
public class SeqStack<T> implements Stack<T> {

    //栈顶元素
    private int top = -1;

    //默认容量
    private int capacity = 10;

    //存放元素的数组
    private T[] arrays;

    //存放元素的个数
    private int size;

    //顺序栈构造
    public SeqStack(int capacity){
        arrays = (T[]) new Object[capacity];
    }

    //顺序栈构造
    public SeqStack(){
        arrays = (T[]) new Object[this.capacity];
    }

    @Override
    public void push(T t) {
        //判断容量是否充足
        if(arrays.length == size){
            ensureCapacity(size * 2 + 1);
        }
        arrays[++top] = t;

        size ++;
    }

    /**
     * 判断是否为空
     * @return
     */
    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 获取栈顶元素的值，不能删除
     * @return
     */
    @Override
    public T peek() {
        if(isEmpty()){
            new EmptyStackException();
        }
        return arrays[top];
    }

    @Override
    public T pop() {
        if(isEmpty()){
            new EmptyStackException();
        }
        size -- ;
        return arrays[top--];
    }

    /**
     * 扩容
     * @param capacity
     */
    public void ensureCapacity(int capacity){
        //如果需要拓展的容量比现在数组的容量还小,则无需扩容
        if(capacity < size){
            return;
        }

        T[] old = arrays;
        arrays = (T[]) new Object[capacity];
        for (int i = 0; i < size ; i ++){
            arrays[i] = old[i];
        }
    }

}
