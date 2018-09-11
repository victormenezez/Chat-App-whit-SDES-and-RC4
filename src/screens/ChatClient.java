/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens;

import encryption.RC4;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import javax.swing.*;

/**
 *
 * @author victor
 */
public class ChatClient extends JFrame {
    JTextField text_to_send;
    PrintWriter printer;
    Socket socket;
    String name;
    JTextArea received_text;
    Scanner scanner;
    RC4 rc4;
    private class ServerListener implements Runnable {

        @Override
        public void run() {
            try{
                String text;
                while((text = scanner.nextLine()) != null){
                    byte[] decrypted = rc4.decrypt(stringToByteVector(text));
                    System.out.println();
                    received_text.append(new String(decrypted) + "\n");
                }
            }catch (Exception e){
                
            }
            
        }
        
    }
    
    public ChatClient(String name) throws IOException{
        super("Chat: " + name);
        this.name = name;
        
        Font font = new Font("Serif", Font.PLAIN, 26);
        text_to_send = new JTextField();
        text_to_send.setFont(font);
        JButton button = new JButton("Enviar");
        button.addActionListener(new SendListener());
        Container send = new JPanel();
        send.setLayout(new BorderLayout()); 
        send.add(BorderLayout.CENTER, text_to_send);
        send.add(BorderLayout.EAST, button);
        
        received_text = new JTextArea();
        received_text.setFont(font);
        JScrollPane scroll = new JScrollPane(received_text);
        
        getContentPane().add(BorderLayout.CENTER, scroll);
        getContentPane().add(BorderLayout.SOUTH, send);

        networkConfig();
        
        rc4 = new RC4("adamastorpitaco".getBytes());
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(300, 500);
    }
    
    private class SendListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String original_text = name+": "+text_to_send.getText();
            byte[] encrypted_message = original_text.getBytes();
            printer.println( rc4.encrypt(encrypted_message));
            printer.flush();
            text_to_send.setText("");
            text_to_send.requestFocus();
        }
    }
    
    private void networkConfig() throws IOException{
        try{
            socket = new Socket("127.0.0.1", 5354);
            printer = new PrintWriter(socket.getOutputStream());
            scanner = new Scanner(socket.getInputStream());
            new Thread(new ServerListener()).start();
        } catch(Exception e){}
    }
    
    
    private byte[] stringToByteVector ( String mensagem ){
        int index = 0;
        byte[] byteMessage = new byte[ mensagem.length() ];
        for(char c: mensagem.toCharArray()){
           byteMessage[ index++ ] = (byte) c;
        }
        
        return byteMessage;
    }
}
