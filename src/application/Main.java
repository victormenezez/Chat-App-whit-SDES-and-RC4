/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.IOException;
import screens.ChatClient;
import screens.ChatServer;

/**
 *
 * @author victor
 */
public class Main {
    
    public static void main(String[] args) throws IOException {
        ChatServer server = new ChatServer();
        ChatClient c1 = new ChatClient("Erick");
        ChatClient c2 = new ChatClient("Victor");
        

//        String ai = "teste";
//        byte[] ai_by = ai.getBytes();
//        System.out.println( ai_by );
//        System.out.println( new String(ai_by) );
        
        
    }
}
