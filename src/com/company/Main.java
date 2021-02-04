package com.company;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        EncryptionAlgorithm encryptionAlgorithm = new EncryptionAlgorithm(); //creating instance of the algorithm
        //setting the program to repeat until user stops it
        boolean repeat = true;
        while (repeat) {
            //setting variables
            String pathToFile;
            String pathToKey;
            printMenu(); //printing menu
            Scanner scanner = new Scanner(System.in);
            int menuChoose = scanner.nextInt(); //using scanner to get user input about what to do
            switch (menuChoose){
                case 1: //user wants to encrypt file, asking him to input path to file
                    System.out.println("Please Enter path to the file you want to encrypt");
                    scanner.nextLine();//clearing enter from previous scanner
                    pathToFile = scanner.nextLine(); //getting the path
                    encryptionAlgorithm.encryptAlgorithm(pathToFile, true); //calling encryption with encrypt set to true
                    //that means that we want to encrypt and not to decrypt
                    break;
                case 2:
                    System.out.println("Please Enter path to the file you want to decrypt");
                    scanner.nextLine();
                    pathToFile = scanner.nextLine();
                    System.out.println("Please enter path to key");
                    pathToKey = scanner.nextLine();
                    encryptionAlgorithm.encryptAlgorithm(pathToFile, pathToKey ,false);
                    break;
                case 0:
                    System.out.println("Exiting Program");
                    repeat = false;
                    break;
                default:
                    System.out.println("Please enter only valid integers");
                    break;
            }

        }
    }

    public static void printMenu(){ //The menu of the program
        System.out.println("Welcome to Encryption/Decryption program:\n" + "Please choose the action you want to preform:");
        System.out.println("1. Encrypt file\n" + "2. Decrypt file\n" + "0. Exit program");
    }
}
