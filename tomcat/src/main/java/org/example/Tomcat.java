package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Hello world!
 *
 */
public class Tomcat
{
    public void start(){
        try {
            ExecutorService executorService = Executors.newFixedThreadPool(20);

            ServerSocket serverSocket = new ServerSocket(8080);

            while (true){
                Socket socket = serverSocket.accept();
                executorService.execute(new SocketProcessor(socket));
            }

        }catch (IOException e){

        }

    }
    private void processSocket(Socket socket){

    }
    public static void main( String[] args )
    {
        Tomcat tomcat = new Tomcat();
        tomcat.start();
    }
}
