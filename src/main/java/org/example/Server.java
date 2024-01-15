package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket=new ServerSocket(3001);
            Socket socket= serverSocket.accept();
            System.out.println("Server is listening...");

            //System.out.println("accepted");
            String str1="";//massege
            String str2="";//reply

            DataInputStream dataInputStream =new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream=new DataOutputStream(socket.getOutputStream());
            //Scanner scanner=new Scanner(System.in);
            BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(System.in));
            //scanner wenuwata files read kirima sadaha buffer reader yoda gani.

                while(!str1.equals("stop")) {
                    str1=dataInputStream.readUTF();
                    System.out.println("Client says: "+str1);
                    str2= bufferedReader.readLine();
                    dataOutputStream.writeUTF(str2);
                    dataOutputStream.flush();// anith paththata yawanu labai.
                }
            System.out.println("closed connect");
            dataInputStream.close();
            dataOutputStream.close();


            socket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}