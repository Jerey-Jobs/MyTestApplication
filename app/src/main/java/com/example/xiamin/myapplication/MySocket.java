package com.example.xiamin.myapplication;

import android.util.Log;

import java.io.InputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Xiamin on 2016/7/6.
 */
public class MySocket {

    private Socket socket;
    private InputStream mInputStream;
    private PrintWriter mPrintWriter;
    private static final String SERVER_IP   = "159.203.215.58";
    private static final int    SERVER_PORT = 8000;

    public MySocket()
    {

    }

    public boolean connection()
    {
        boolean bRet = false;
        try
        {
            //创建一个客户端连接
            InetAddress ia = InetAddress.getByName(SERVER_IP);
            socket = new Socket(ia, SERVER_PORT);

            //获取这个客户端连接SOCKET的输入输出
            mPrintWriter = new PrintWriter(socket.getOutputStream(), true);
            mInputStream = socket.getInputStream();

            bRet = true;
        } catch (Exception e)
        {
            Log.i("iii","创建一个客户端连接yichang");
            // TODO: handle exception
        }

        return bRet;
    }

    //发送消息
    public void sendMsg(String msg)
    {
        mPrintWriter.print(msg);
        mPrintWriter.flush();
    }

    //读取消息
    public String readMsg()
    {
        String msgString = "";
        try
        {
            byte buffer[] = new byte[1024];

            int reCount = mInputStream.read(buffer);

            msgString = new String(buffer, "GB2312");

        } catch (Exception e)
        {
            // TODO: handle exception
        }

        return msgString;
    }


    public void close()
    {
        try
        {
            socket.close();
        } catch (Exception e)
        {
            // TODO: handle exception
        }
    }
}
