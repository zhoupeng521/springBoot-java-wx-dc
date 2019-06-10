package com.tts.logdome.common.queue;

public class LinkQueue<T> {

    //对头
    private Node<T> front;

    //队尾
    private Node<T> rear;

    //元素个数
    private int size;

    /**
     * 创建队列
     */
    public LinkQueue(){
        this.front = this.rear = null;
    }

    /**
     * 入队
     */
    public void enQueue(T data){
        Node<T> node = new Node<>(data);
        if(isEmtity()){
            front = rear = node;
        }else{
            rear.setNextNode(node);
            rear = node;
        }
        size ++;
    }

    /**
     * 离队
     */
    public T deQueue(){
        if(isEmtity()){
            throw new RuntimeException("队列为空");
        }
        Node<T> delete = front;
        front = delete.getNextNode();
        delete.setNextNode(null);
        size --;

        if(size == 0){
            //删除掉最后一个元素时，front值已经为null，但rear还是指向该节点，需要将rear置为null
            front = rear;
        }
        return (T)delete.getData();
    }

    /**
     * 元素个数
     * @return
     */
    public int size(){
        return size;
    }

    /**
     * 判断队列是否为空
     * @return
     */
    public boolean isEmtity(){
        return (front == null && rear == null) ? true : false;
    }

}
