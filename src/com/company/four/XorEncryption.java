package com.company.four;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class XorEncryption implements IEncryptionAlgorithm {
    //The same as ShiftUP encryption only we don't add the key value, we preform XOR operation on the two values
    private final FileOperations files = new FileOperations();
    private final String name = "Xor";

    @Override
    public void encryptFile(String originalFilePath, String outputFilePath, String keyFilePath) throws IOException, NullPointerException {
        try{
            int key = files.readIntFromFile(keyFilePath);
            File fileToEncrypt = new File(originalFilePath);
            Scanner myReader = new Scanner(fileToEncrypt);
            while (myReader.hasNextLine()) { //while it has lines, for each line we generate a stringbuffer and adding him the new line
                String data = myReader.nextLine();
                StringBuffer encryptedData = new StringBuffer();
                for (int i = 0; i < data.length(); i++) {
                    if ((data.charAt(i) ^ key) == 133){
                        encryptedData.append(data.charAt(i));
                    } else {
                        encryptedData.append((char) (data.charAt(i) ^ key)); //Xor between key and value of char and casting the value to char
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
            encryptFile(originalFilePath, outputFilePath, keyFilePath); //we just preform the encryption again to decrypt because of the way XOR operate.
            //If A = B ^ C then in order to find B again (the value of the original char) we know that B = A ^ C
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
    public String getName() {
        return this.name;
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
