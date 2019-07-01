package com.tts.logdome.common.queue.twoTree;

/**
 * @创建人 zp
 * @创建时间 2019/7/1
 * @描述  二叉树测试
 */
public class TwoTreeTest {

    public static void main(String[] args) {

        TwoTree twoTree = new TwoTree();
        twoTree.insert(1,1.12);
        twoTree.insert(2,2.12);
        twoTree.insert(5,5.12);
        twoTree.insert(4,4.12);
        twoTree.insert(32,32.12);
        twoTree.insert(11,11.12);
       /* Node node = twoTree.find(5);
        if(node == null){
            System.out.println("we can\'t find it");
        }else{
            node.showNode();
        }
        Node[] temp = twoTree.mVal();
        temp[0].showNode();
        temp[1].showNode();*/

        Node[] nodes = twoTree.sort();
        for (Node n : nodes){
            n.showNode();
        }
    }

}
