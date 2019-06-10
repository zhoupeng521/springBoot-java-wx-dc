package com.tts.logdome.common.queue;

import lombok.Data;

@Data
public class Node<T> {

    private T data;

    private Node<T> nextNode;

    public Node(T data){
        this.data = data;
    }

}
