/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
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
        getContentPane().add(BorderLayout.SOUTH, send);
        
        networkConfig();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(500, 90);
    }
    
    private class SendListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            printer.println(name+": "+text_to_send.getText());
            printer.flush();
            text_to_send.setText("");
            text_to_send.requestFocus();
        }
    }
    
    private void networkConfig() throws IOException{
        try{
            socket = new Socket("127.0.0.1", 5000);
            printer = new PrintWriter(socket.getOutputStream());
        } catch(Exception e){}
    }
    
    public static void main(String[] args) throws IOException{
        new ChatClient("Victor");
        new ChatClient("Erick");        
    }
}
