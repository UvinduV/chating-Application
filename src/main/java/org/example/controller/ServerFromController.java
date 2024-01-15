package org.example.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerFromController {

    @FXML
    private JFXButton btnSendServer;

    @FXML
    private TextArea txtAreaServer;

    @FXML
    private TextField txtServer;

    ServerSocket serverSocket;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    String massege="";

    public void initialize(){
        new Thread(()->{
            try {
            serverSocket=new ServerSocket(3002);
            txtAreaServer.appendText("server stared");
            socket= serverSocket.accept();
            txtAreaServer.appendText("\nserver accept");

            DataInputStream dataInputStream =new DataInputStream(socket.getInputStream());
            //DataOutputStream dataOutputStream=new DataOutputStream(socket.getOutputStream());


            while(!massege.equals("stop")) {
                massege=dataInputStream.readUTF();
                txtAreaServer.appendText("\n Client says: "+massege+"\n");

            }
            System.out.println("\nclosed connect");
            dataInputStream.close();
            //dataOutputStream.close();

            socket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        }).start();

    }

    public void btnSendServerOnAction(ActionEvent event) throws IOException {
        /*dataOutputStream.writeUTF(txtServer.getText());
        dataOutputStream.flush();*/
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            String msg = txtServer.getText();
            dataOutputStream.writeUTF(massege);
            dataOutputStream.flush();
            txtAreaServer.appendText("Server : " + massege + "\n");
            txtServer.clear();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
