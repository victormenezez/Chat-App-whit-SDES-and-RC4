package application;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import screens.ChatClient;

public class ChatServer {
    
    ArrayList<PrintWriter> printers = new ArrayList<>();
    
    public ChatServer() throws IOException{
        ServerSocket server;
        try{
            server = new ServerSocket(5354);
            while(true){
                Socket socket = server.accept();
                new Thread(new ClientListener(socket)).start();
                PrintWriter p = new PrintWriter(socket.getOutputStream());
                printers.add(p);
            }
        } catch(IOException e){}
    }
    
    private void sendToAll(String text){
        for(PrintWriter w : printers){
            try{
                w.println(text);
                w.flush();
            } catch(Exception e){}
        }
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
//                    System.out.println(text);
                    
                    sendToAll(text);
                }
            } catch(Exception e){}
        }
    }
    
    public static void main(String[] args) throws IOException {
        new ChatServer();
        ChatClient c1 = new ChatClient("Victor");
        ChatClient c2 = new ChatClient("Erick");
        
        c1.setVisible(true);
        c2.setVisible(true);
    }
}
