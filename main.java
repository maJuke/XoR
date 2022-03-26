package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class main {

    public static String encode (String msg, String key) {
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < msg.length(); i++) {
            int fI = (int) msg.charAt(i) - (int) 'a'; // First Iteration
            int sI = (int) (key.charAt(i % key.length())) - (int) 'a'; // Second Iteration
            Character tI = (char) (((fI + sI) % 26) + (int) 'a'); // Third Iteration

            if ((int) msg.charAt(i) == (int)' ')
                res.append(' ');
            else
                res.append(tI);
        }
        return res.toString();
    }

    public static String decode (String msg, String key) {
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < msg.length(); i++) {
            int fI = (int) msg.charAt(i) - (int) 'a'; // First Iteration
            int sI = (int) (key.charAt(i % key.length())) - (int) 'a'; // Second Iteration
            Character tI = (char)(((fI - sI + 26) % 26) + (int) 'a'); // Third Iteration

            if ((int)msg.charAt(i) == (int)(' '))
                res.append(' ');
            else
                res.append(tI);
        }
        return res.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Type 'e' - for encryption, 'd' - for decryption, else - for quit");
        String ans = sc.nextLine();
        switch (ans) {
            case "e":
                System.out.println("Type in message you want to encrypt:");
                String enMsgOut = sc.nextLine();
                System.out.println("Type in the key:");
                String enKeyOut = sc.nextLine();
                String resE = encode(enMsgOut.toLowerCase(), enKeyOut.toLowerCase());
                System.out.println("Encrypted message is: " + resE);
                try {
                    FileWriter wr = new FileWriter("encrypted message.txt");
                    wr.write(resE);
                    wr.close();
                    System.out.println("Result has been written!");
                } catch (IOException e) {
                    System.out.println("An error occurred!");
                    e.printStackTrace();
                }
                break;
            case "d":
                String deMsgOut = "";
                String deKeyOut = "";
                System.out.println("Type in the file: ");
                String fileName = sc.nextLine();
                try {
                    File rd = new File(fileName);
                    Scanner reader = new Scanner(rd);
                    while (reader.hasNextLine())
                        deMsgOut = reader.nextLine();
                    reader.close();

                    System.out.println("Message has been read. Type in the key's file: ");

                    String keyFileName = sc.nextLine();
                    rd = new File(keyFileName);
                    reader = new Scanner(rd);
                    while (reader.hasNextLine())
                        deKeyOut = reader.nextLine();
                    reader.close();
                } catch (IOException e) {
                    System.out.println("An error occurred!");
                    e.printStackTrace();
                }

                String resD = decode(deMsgOut, deKeyOut);
                System.out.println("Decrypted message is: " + resD);

                try {
                    FileWriter wr = new FileWriter("decrypted message.txt");
                    wr.write(resD);
                    wr.close();
                    System.out.println("Result has been written!");
                } catch (IOException e) {
                    System.out.println("An error occurred!");
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("Quiting...");
                break;
        }
    }
}