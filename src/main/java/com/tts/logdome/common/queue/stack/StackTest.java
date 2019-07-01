package com.tts.logdome.common.queue.stack;

/**
 * @创建人 zp
 * @创建时间 2019/6/28
 * @描述 栈测试
 */
public class StackTest {

    public static void main(String[] args) {
        Stack<String> stack = new SeqStack<>();
        stack.push("张三");
        stack.push("李四");
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.peek());

        Stack<String> stack1 = new LinkStack<>();
        stack1.push("战士");
        stack1.push("法师");
        stack1.push("道士");
        System.out.println(stack1.peek());
        System.out.println(stack1.pop());
        System.out.println(stack1.peek());
    }

}
