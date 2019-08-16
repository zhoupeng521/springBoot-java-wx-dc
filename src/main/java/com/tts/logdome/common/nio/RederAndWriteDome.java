package com.tts.logdome.common.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;

/**
 * @创建人 zp
 * @创建时间 2019/8/16
 * @描述 nio--读和写
 */
public class RederAndWriteDome {

    public static void main(String[] args) throws IOException {

       /* //获取通道
        FileInputStream fileInputStream = new FileInputStream("C:/test.txt");

        FileChannel channel = fileInputStream.getChannel();

        //创建缓存区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //将读取得数据读到缓存区
            int read = channel.read(byteBuffer);*/


        //写入文件
        Byte[] bytes = new Byte[]{1,2,3};

        FileOutputStream fileOutputStream = new FileOutputStream("D:\\test.txt");

        FileChannel fileChannel = fileOutputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        for (Byte b : bytes){
            buffer.put(b);
        }
        // 重设一下buffer的position=0，limit=position
        buffer.flip();

        fileChannel.write(buffer);

        //关闭通道和文件流
        fileOutputStream.close();
        fileChannel.close();
    }

}
