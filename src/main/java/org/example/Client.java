package org.example;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Socket socket= null;
        try {
            socket = new Socket("localhost",3001);
            System.out.println("");

            DataInputStream dataInputStream=new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream= new DataOutputStream(socket.getOutputStream());
            //Scanner scanner=new Scanner(System.in);
            BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(System.in));
            String str1="";
            String str2="";

            while(!str1.equals("stop")) {
                str1= bufferedReader.readLine();
                dataOutputStream.writeUTF(str1);
                dataOutputStream.flush();
                str2=dataInputStream.readUTF();
                System.out.println("server says: "+str2);
            }
            System.out.println("closed conection");
            dataOutputStream.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
