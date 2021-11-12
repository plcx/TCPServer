package server;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

import javax.swing.text.html.HTMLDocument.Iterator;


public class Receiver implements Runnable{
        private Socket socketreceiver;
        private static HashMap<String, Socket> socketList = new HashMap<>();
        private String receive;
        private int num=0;
        
        

        

        Receiver (Socket socketreceiver,HashMap<String, Socket> socketList){
                this.socketreceiver=socketreceiver;
                this.socketList=socketList;

                
        }
        




        public void run() {
                while(true) {
                        
                        try {
                                
                                        PrintWriter out=new PrintWriter(socketreceiver.getOutputStream());                              
                                        for (String key : socketList.keySet()) {
                                                if(socketreceiver!=socketList.get(key)) {
                                                        out = new PrintWriter(socketList.get(key).getOutputStream());
                                                }
                                                
                                        }
                                        BufferedReader in = new BufferedReader(new InputStreamReader(socketreceiver.getInputStream()));
                                        receive=in.readLine();
                                        System.out.println(receive);
                                        out.println(receive);
                                        out.flush();                    
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
                        
                        num++;
                        
                        
                }
        }
        

}



