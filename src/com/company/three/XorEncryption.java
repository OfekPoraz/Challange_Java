package com.company.three;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class XorEncryption implements IEncryptionAlgorithm {
    //The same as ShiftUP encryption only we don't add the key value, we preform XOR operation on the two values
    private final FileOperations files = new FileOperations();
    private final String name = "Xor";

    @Override
    public void encryptFile(String originalFilePath, String outputFilePath, String keyFilePath) throws IOException {
        int key = files.readIntFromFile(keyFilePath);
        File fileToEncrypt = new File(originalFilePath);
        Scanner myReader = new Scanner(fileToEncrypt);
        while (myReader.hasNextLine()) { //while it has lines, for each line we generate a stringbuffer and adding him the new line
            String data = myReader.nextLine();
            StringBuffer encryptedData = new StringBuffer();
            for (int i = 0; i < data.length(); i++) {
                encryptedData.append((char) (data.charAt(i) ^ key)); //Xor between key and value of char and casting the value to char
            }
            files.addStringToFile(outputFilePath, encryptedData); // after going over the entire line we add the line to the file
        }
    }

    @Override
    public void decryptFile(String originalFilePath, String outputFilePath, String keyFilePath) throws IOException {
        encryptFile(originalFilePath, outputFilePath, keyFilePath); //we just preform the encryption again to decrypt because of the way XOR operate.
        //If A = B ^ C then in order to find B again (the value of the original char) we know that B = A ^ C
    }

    @Override
    public String getName() {
        return this.name;
    }
}
