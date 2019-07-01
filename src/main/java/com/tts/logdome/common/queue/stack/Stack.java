package com.tts.logdome.common.queue.stack;

/**
 * @创建人 zp
 * @创建时间 2019/6/28
 * @描述 顺序栈的实现
 */
public interface Stack<T> {

    /**
     * 入栈
     * @param t
     */
    void push(T t);

    /**
     * 判断栈是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 栈顶元素
     * @return
     */
    T peek();

    /**
     * 出栈
     * @return
     */
    T pop();
}
