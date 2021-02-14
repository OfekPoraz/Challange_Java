package com.company.two;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ShiftMultiplyEncryption implements IEncryptionAlgorithm{
    private final FileOperations files = new FileOperations(); //Instance of file operation in order to do files operations
    private final String name = "Multiply"; //Setting the name of the encryption
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void encryptFile(String originalFilePath, String outputFilePath, String keyFilePath) throws IOException {
        // we read the key value from the file
        int key = files.readIntFromFile(keyFilePath);
        File fileToEncrypt = new File(originalFilePath);
        Scanner myReader = new Scanner(fileToEncrypt);
        while (myReader.hasNextLine()) { //while it has lines, for each line we generate a stringbuffer and adding him the new line
            String data = myReader.nextLine();
            StringBuffer encryptedData = new StringBuffer();
            for (int i = 0; i < data.length(); i++) {
                encryptedData.append((char) (data.charAt(i) * key)); //multiplying the key to each char and casting the value to char
            }
            files.addStringToFile(outputFilePath, encryptedData); // after going over the entire line we add the line to the file
        }
    }

    @Override
    public void decryptFile(String originalFilePath, String outputFilePath, String keyFilePath) throws IOException {
        // we read the key value from the file
        int key = files.readIntFromFile(keyFilePath);
        File fileToEncrypt = new File(originalFilePath);
        Scanner myReader = new Scanner(fileToEncrypt);
        while (myReader.hasNextLine()) { //while it has lines, for each line we generate a stringbuffer and adding him the new line
            String data = myReader.nextLine();
            StringBuffer encryptedData = new StringBuffer();
            for (int i = 0; i < data.length(); i++) {
                encryptedData.append((char) (data.charAt(i) / key)); //dividing the key to each char and casting the value to char
            }
            files.addStringToFile(outputFilePath, encryptedData); // after going over the entire line we add the line to the file
        }
    }
}
