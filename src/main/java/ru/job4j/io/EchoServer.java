package ru.job4j.io;

import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();

                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(

                             new InputStreamReader(socket.getInputStream()))) {
                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String headLine = input.readLine();
                    System.out.println(headLine);
                    for (String string = input.readLine(); string != null && !string.isEmpty(); string = input.readLine()) {
                        System.out.println(string);
                    }
                    String[] b = headLine.split(" ");
                    String a = b[1].replace("GET /?msg=", "");
                    switch (a) {
                        case "Exit" -> server.close();
                        case "Hello" -> output.write("Hello".getBytes());
                        case "What" -> output.write("What".getBytes());
                        default -> output.write("Wrong address".getBytes());
                    }
                    output.flush();
                }
            }
        }
    }
}
