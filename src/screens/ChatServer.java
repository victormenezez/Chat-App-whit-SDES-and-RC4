/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatServer {
    
    public ChatServer() throws IOException{
        ServerSocket server;
        try{
            server = new ServerSocket(5000);
            while(true){
                Socket socket = server.accept();
                new Thread(new ClientListener(socket)).start();
            }
        } catch(IOException e){}
    }
    
    private class ClientListener implements Runnable {
        Scanner scanner;
        public ClientListener(Socket socket) throws IOException{
            scanner = new Scanner(socket.getInputStream());
        }
        
        @Override
        public void run() {
            try{
                String text;
                while((text = scanner.nextLine()) != null){
                    System.out.println(text);
                }
            } catch(Exception e){}
        }
    }
    
    public static void main(String[] args) throws IOException{
        new ChatServer();
    }
}
