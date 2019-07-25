package com.tts.logdome.common.queue.twoTree;

import java.util.List;

/**
 * @创建人 zp
 * @创建时间 2019/7/1
 * @描述  二叉树
 */
public class TwoTree {

    //根节点
    private Node root;

    //默认容量
    private int capacity = 10;

    private Node[] nodes = new Node[capacity];

    private int flag = 0;

    /**
     * 插入
     * @param iData
     * @param dData
     */
    public void insert(int iData,double dData){
        //创建node节点
        Node newNode = new Node();
        newNode.iData = iData;
        newNode.dData = dData;
        //判断是否为null
        if(root == null){
            root = newNode;
        }else{//不为null
            Node current = root;
            Node parent;
            while (true){
                parent = current;
                if(iData < current.iData){//插入左节点
                    current = current.leftNode;
                    if (current == null){
                        parent.leftNode = newNode;
                        return;
                    }
                }else{//插入右节点
                    current = current.rightNode;
                    if (current == null){
                        parent.rightNode = newNode;
                        return;
                    }
                }
            }
        }
    }

    //在tree中寻找关键字
    //返回一个Node
    //显示这个Node
    public Node find(int key){
        Node current = root;
        while (current.iData != key){
            if(current.iData > key){
                current = current.leftNode;
            }else{
                current = current.rightNode;
            }
            if(current == null){
                return null;
            }
        }
        return current;
    }

    /**
     * 求最值
     * @return
     */
    public Node[] mVal(){
        Node minNode = null;
        Node maxNode = null;
        Node[] mVal = new Node[2];
        Node current =  root;//从根节点开始
        while (current != null){
            minNode = current;
            current = current.leftNode;
        }
        mVal[0] = minNode;
        current = root;
        while (current != null){
            maxNode = current;
            current = current.rightNode;
        }
        mVal[1] = maxNode;
        return mVal;
    }

    public void sort(Node node){
        nodes[flag] = node;
        if(node.leftNode != null){
            flag++;
            sort(node.leftNode);
        }
        if (node.rightNode != null){
            flag++;
            sort(node.rightNode);
        }
    }

    /**
     * 返回当前数的深度
     *  说明:
     *  1、如果一棵树只有一个结点，它的深度为1。
     *  2、如果根结点只有左子树而没有右子树，那么树的深度是其左子树的深度加1；
     *  3、如果根结点只有右子树而没有左子树，那么树的深度应该是其右子树的深度加1；
     *  4、如果既有右子树又有左子树，那该树的深度就是其左、右子树深度的较大值再加1。
     *
     * @return
     */
    public int getTreeDepth(Node node){
        if(node.leftNode == null && node.rightNode == null){
            return 1;
        }
        int left = 0;
        int right = 0;
        if(node.leftNode != null){
            left = getTreeDepth(node.leftNode);
        }
        if(node.rightNode != null){
            right = getTreeDepth(node.rightNode);
        }
        return left > right ? left + 1 : right + 1;
    }

    public Node getRoot() {
        return root;
    }

    public Node[] getNodes() {
        return nodes;
    }
}
