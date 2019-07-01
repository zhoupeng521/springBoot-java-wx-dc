package com.tts.logdome.common.queue.twoTree;

/**
 * @创建人 zp
 * @创建时间 2019/7/1
 * @描述  二叉树
 */
public class TwoTree {

    //根节点
    private Node root;

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

    public Node[] sort(){
        int i = 0;
        Node current = root;
        Node[] nodes = new Node[20];
        while (current != null){
            nodes[i] = current;
            current = current.leftNode;
            i ++ ;
        }
        current = root;
        while (current != null){
            nodes[i] = current;
            current = current.leftNode;
            i ++ ;
        }
        return nodes;
    }
}
