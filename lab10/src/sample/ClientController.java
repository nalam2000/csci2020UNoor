package sample;

import javafx.scene.control.TextField;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class ClientController {

    public TextField usernameField;

    public TextField messageField;


    public void sendMessage(){
        try {
            Socket socket = new Socket("localhost", 8080);
            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            out.println(usernameField.getText() + ": " + messageField.getText());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public void exitClient(){
        System.exit(0);
    }
}