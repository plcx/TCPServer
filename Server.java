package server;


import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;



public class Server implements Runnable{
            private final static int PORT = 8888;
            public static HashMap<String, Socket> socketList = new HashMap<>();
            public static String channelToken;  //socket 令牌
            private static int num=0;
            
           
                

        public void run() {
                 try {
                    ServerSocket server =  new ServerSocket(PORT);
                    Socket clientSocket=null;
                    System.out.println("server is listenning...");
                    Receiver receiver[]=new Receiver[2];
                    Thread thread[]=new Thread[2];
                    while(true){
                        
                        
                        if(num<2) {
                                clientSocket = server.accept();
                                socketList.put(Integer.toString(num),clientSocket);
                              
                                        
                        }
                        if(num==2) {
                                receiver[0]=new Receiver(socketList.get("0"),socketList);
                                thread[0]=new Thread(receiver[0]);
                                thread[0].start();              
                                receiver[1]=new Receiver(socketList.get("1"),socketList);
                                thread[1]=new Thread(receiver[1]);
                                thread[1].start();
                                
                        }
                        
                   
                        
                   
                       num++;
                       
                        
                    }
                
                         
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }


        public static void main(String[] args) {
             Thread ServerThread = new Thread(new Server());
              ServerThread.start();
           
        }
    }


                
