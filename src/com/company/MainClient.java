package com.company;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MainClient {
    private static Socket socket;
    private static BufferedReader reader;
    private static BufferedReader in; // чтение из сокета
    private static BufferedWriter out; //запись в сокет

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);//для того чтобы пользователь мог вводить какие-либо данные
        try{
            try {
                socket = new Socket("localhost", 4004);//запрашиваем у сервера строку на соединение
                reader = new BufferedReader(new InputStreamReader(System.in));//доступ на соединение
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));//чтение сообщения
                out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));//пишем сообщение

                System.out.println("Enter the expression");
                String word = scanner.nextLine(); //ждем ответа клиента
                out.write(word + "\n"); //отправление сообщения на сервер
                out.flush();
                String serverWord = in.readLine(); // ждём ответа сервера
                System.out.println(serverWord); // получаем ответ
            } finally { //закрываем потоки
                socket.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
