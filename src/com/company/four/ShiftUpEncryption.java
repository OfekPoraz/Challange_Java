package com.company.four;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ShiftUpEncryption implements IEncryptionAlgorithm {
    private final FileOperations files = new FileOperations(); //Instance of file operation in order to do files operations
    private final String name = "Up";//Setting the name of the encryption

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void encryptFile(String originalFilePath, String outputFilePath, String keyFilePath) throws IOException, NoSuchElementException, NullPointerException{
        // we read the key value from the file
        try {
            int key = files.readIntFromFile(keyFilePath);
            File fileToEncrypt = new File(originalFilePath);
            Scanner myReader = new Scanner(fileToEncrypt);
            while (myReader.hasNextLine()) { //while it has lines, for each line we generate a stringbuffer and adding him the new line
                String data = myReader.nextLine();
                StringBuffer encryptedData = new StringBuffer();
                for (int i = 0; i < data.length(); i++) {
                    if (data.charAt(i) + key == 133){
                        encryptedData.append(data.charAt(i));
                    } else {
                        encryptedData.append((char) (data.charAt(i) + key)); //adding the key to each char and casting the value to char
                    }
                }
                files.addStringToFile(outputFilePath, encryptedData); // after going over the entire line we add the line to the file
            }
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("No key");
        } catch (ClassCastException e){
            throw new ClassCastException("Invalid key format");
        } catch (IOException e) {
            throw new IOException("Invalid Path format, IO Exception");
        } catch (NullPointerException e){
            throw new NullPointerException("Invalid Path format, Null");
        }

    }

    @Override
    public void decryptFile(String originalFilePath, String outputFilePath, String keyFilePath) throws IOException, NullPointerException {
        try{
            // we read the key value from the file
            int key = files.readIntFromFile(keyFilePath);
            File fileToEncrypt = new File(originalFilePath);
            Scanner myReader = new Scanner(fileToEncrypt);
            while (myReader.hasNextLine()) { //while it has lines, for each line we generate a stringbuffer and adding him the new line
                String data = myReader.nextLine();
                StringBuffer encryptedData = new StringBuffer();
                for (int i = 0; i < data.length(); i++) {
                    if (data.charAt(i) + key == 133) {
                        encryptedData.append(data.charAt(i));
                    } else {
                        encryptedData.append((char) (data.charAt(i) - key)); //subtracting the key to each char and casting the value to char
                    }
                }
                files.addStringToFile(outputFilePath, encryptedData); // after going over the entire line we add the line to the file
            }
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("No key");
        } catch (ClassCastException e){
            throw new ClassCastException("Invalid key format");
        } catch (IOException e) {
            throw new IOException("Invalid Path format, IO Exception");
        } catch (NullPointerException e){
            throw new NullPointerException("Invalid Path format, Null");
        }

    }

    @Override
    public int getKeyStrength(String keyFilePath) throws NullPointerException, IOException {
        try {
            int key = files.readIntFromFile(keyFilePath);
            return (int) (Math.log10(key)+1);

        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("No key");
        } catch (ClassCastException e){
            throw new ClassCastException("Invalid key format");
        } catch (NullPointerException e){
            throw new NullPointerException("Invalid Path format, Null");
        } catch (FileNotFoundException e){
            throw new FileNotFoundException("File not found");
        }
    }
}
