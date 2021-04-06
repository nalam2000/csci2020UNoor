package sample;

import java.util.concurrent.*;
import javafx.scene.control.TextArea;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Controller {

    public TextArea board;


    public void initialize() throws IOException {
        ThreadPoolExecutor t = (ThreadPoolExecutor)Executors.newFixedThreadPool(5);
        t.execute(new ThreadPool());
    }

    public void exitClient(){
        System.exit(0);
    }

    class ThreadPool implements Runnable{
        ThreadPool(){

        }
        @Override
        public void run() {
            ThreadPoolExecutor t = (ThreadPoolExecutor)Executors.newFixedThreadPool(25);
            try (ServerSocket s = new ServerSocket(8080)) {
                System.out.println("The server is running...");
                while (true) {
                    t.execute(new ConnectionHandler(s.accept()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public void setText(String message){
        String prev = board.getText();
        board.setText(prev + "\n" + message);
    }

    public class ConnectionHandler implements Runnable {
        private Socket socket;

        public ConnectionHandler(Socket socket) {
            //System.out.println("new thread created");
            this.socket = socket;
        }

        @Override
        public void run() {
            //System.out.println("Connected: " + socket);
            try {
                Scanner in = new Scanner(socket.getInputStream());

                String message = in.nextLine();
                setText(message);
            }
            catch (Exception e) {
                System.out.println(e);
            }
            finally{
                try{
                    socket.close();
                }
                catch (IOException e){

                }

            }
        }
    }
}