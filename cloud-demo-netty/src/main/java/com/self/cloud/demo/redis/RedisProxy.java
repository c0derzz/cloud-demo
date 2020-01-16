package com.self.cloud.demo.redis;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: liruichuan
 * @Date: 2019/12/31 10:58
 * @Description: redis 代理 拦截redis 请求 查看请求数据格式
 */
public class RedisProxy {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(6378);
            System.out.println("代理服务器启动成功...");
            //循环接受请求
            int i = 0;
            while (true){
                Socket socket = serverSocket.accept();
                //得到输入流，用于接收数据
                BufferedReader socketInput = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()
                ));
                socket.getOutputStream().write("+OK\\r\\n".getBytes());
                socket.getOutputStream().flush();

                char[] buf = new char[1024];
                while(socketInput.read(buf) != -1){
                    System.out.println(new String(buf));
                }
                socket.close();

/*
                //创建线程处理请求
                Thread clientThread = new Thread(new ClientHandler(socket),"客户端线程:" + i);
                clientThread.start();
                clientThread.join();
                System.out.println(clientThread.getName() + " 执行完成");*/
                i++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

class ClientHandler implements Runnable{
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            //得到输入流，用于接收数据
            BufferedReader socketInput = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()
            ));
            socket.getOutputStream().write("+OK".getBytes());
            socket.getOutputStream().flush();

            char[] buf = new char[1024];
            while(socketInput.read(buf) > 0){
                System.out.println(new String(buf));
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
