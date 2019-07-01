package com.tts.logdome.common.queue.twoTree;

/**
 * @创建人 zp
 * @创建时间 2019/7/1
 * @描述 树节点
 */
//创建一个树的节点
//每个node存放两个数据
//一个左node引用和一个右node引用
public class Node {

    public int iData;

    public double dData;

    public Node leftNode;

    public Node rightNode;

    public void showNode(){
        System.out.println("{"+iData+","+dData+"}");
    }

}
