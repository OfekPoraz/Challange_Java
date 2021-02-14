package com.company.four;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ShiftMultiplyEncryption implements IEncryptionAlgorithm {
    private final FileOperations files = new FileOperations(); //Instance of file operation in order to do files operations
    private final String name = "Multiply"; //Setting the name of the encryption
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void encryptFile(String originalFilePath, String outputFilePath, String keyFilePath) throws IOException, NullPointerException{
        try {
            // we read the key value from the file
            int key = files.readIntFromFile(keyFilePath);
            File fileToEncrypt = new File(originalFilePath);
            Scanner myReader = new Scanner(fileToEncrypt);
            while (myReader.hasNextLine()) { //while it has lines, for each line we generate a stringbuffer and adding him the new line
                String data = myReader.nextLine();
                StringBuffer encryptedData = new StringBuffer();
                for (int i = 0; i < data.length(); i++) {
                    System.out.println(data.charAt(i));
                    encryptedData.append((char) (data.charAt(i) * key)); //multiplying the key to each char and casting the value to char
                    System.out.println(data.charAt(i)*key);

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
                    System.out.println(data.charAt(i));
                    encryptedData.append((char) (data.charAt(i) / key)); //dividing the key to each char and casting the value to char
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
        } catch (IOException e) {
            throw new IOException("Invalid Path format, IO Exception");
        } catch (NullPointerException e){
            throw new NullPointerException("Invalid Path format, Null");
        }
    }
}
