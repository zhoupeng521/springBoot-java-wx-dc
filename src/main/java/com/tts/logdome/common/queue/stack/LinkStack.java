package com.tts.logdome.common.queue.stack;

import com.tts.logdome.common.queue.Node;

import java.util.EmptyStackException;

/**
 * @创建人 zp
 * @创建时间 2019/6/28
 * @描述 链式栈的实现
 */
public class LinkStack<T> implements Stack<T> {

    //栈顶top
    private Node<T> top;

    private int size;

    public LinkStack(){
        top = new Node<>();
    }

    public int size(){
        return size;
    }

    /**
     * 入栈操作
     * @param t
     */
    @Override
    public void push(T t) {
        if(t == null){
            throw new EmptyStackException();
        }
        //调用pop后top可能为null
        if(this.top == null){
            this.top = new Node<>(t);
        }else if(this.top.getData() == null){
            this.top.setData(t);
        }else{
            Node<T> node = new Node<>(t);
            node.setNextNode(top);
            top = node;
        }
        size ++;
    }

    @Override
    public boolean isEmpty() {
        return top == null || top.getData() == null;
    }

    @Override
    public T peek() {
        if(isEmpty()){
            throw new EmptyStackException();
        }
        return top.getData();
    }

    @Override
    public T pop() {
        if(isEmpty()){
            throw new EmptyStackException();
        }
        Node<T> node = top;
        top = node.getNextNode();
        size --;
        return node.getData();
    }
}
