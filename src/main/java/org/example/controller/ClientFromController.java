package org.example.controller;


import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class ClientFromController {

    @FXML
    private JFXButton btnSendClient;

    @FXML
    private TextArea txtAreaClient;

    @FXML
    private TextField txtClient;

    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    String massege="";

    public void initialize(){
        new Thread(()->{

            try {
                socket = new Socket("localhost",3002);
                txtAreaClient.appendText("client strated");

                dataInputStream=new DataInputStream(socket.getInputStream());
                dataOutputStream= new DataOutputStream(socket.getOutputStream());

                while(!massege.equals("stop")) {
                    massege=dataInputStream.readUTF();
                    txtAreaClient.appendText("\nserver says: "+massege);
                }
                System.out.println("closed conection");
                dataOutputStream.close();
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public void btnSendClientOnAction(ActionEvent event) throws IOException {
        dataOutputStream.writeUTF(txtClient.getText());
        dataOutputStream.flush();
    }
}
