package com.company.four;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FileOperations {

    public int readIntFromFile(String path) throws NoSuchElementException, NullPointerException, FileNotFoundException {
        try {
            return (new Scanner(new File(path))).nextInt();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("No key");
        } catch (ClassCastException e){
            throw new ClassCastException("Invalid key format");
        } catch (NullPointerException e){
            throw new NullPointerException("Invalid Path format, Null");
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not Found");
        }
    }

    public void addIntToFile(String path, int key) throws IOException, NoSuchElementException, ClassCastException, NullPointerException {
        //because we want to add an Int we don't need PrintWrite, but only a FileWriter
        try{
            Writer wr = new FileWriter(path);
            wr.write(Integer.toString(key)); //casting the int into string
            wr.close();
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

    public void addStringToFile(String path, StringBuffer line) throws IOException, NullPointerException {
        //we set a new PrintWriter in order to enter a String to the file, each string in different line. this is way append
        //is set to true
        try{
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path, true)));
            out.println(line);
            out.close();
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

    public String createEncryptionDecryptionFile(String path, String filename, boolean encryptOrDecrypt , String encryptionName) throws IOException, NullPointerException {
        try {
        //here we create a new file. if we want to encrypt we pass true in the boolean field. otherwise false.
        File myFile = null;
        // we create a new file in the directory of the original file with the same name but different ending
        for (int i = 0; i < 1000 ; i++){
            if (encryptOrDecrypt) {
                myFile = new File(path + File.separator + filename + "_Encrypted" + encryptionName + i + ".txt");
            } else {
                myFile = new File(path + File.separator + filename + "_Decrypted" + encryptionName + i + ".txt");
            }

            if (myFile.createNewFile()) {
                //creating file
                //System.out.println("File created: " + myFile.getName());
                break;
            } else {
                //file already exist, creating one with different name
//                System.out.println("File already exists. Trying to create with different name");
            }
        }
        //we return to the user the name of the file and it's path and also return it a parameter (the function return String)
//        System.out.println("File path:" + myFile.getPath());
        return myFile.getPath();
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

    public String createKeyFile(String path ,String encryptionName) throws IOException, NullPointerException {
        try {
        //here we do almost the same as in the previous function only to the key
        //I could have create one function to do all with switch case but decided to do it separately
        File myFile = null;
        for (int i = 0; i < 1000 ; i++) {
            myFile = new File(path + File.separator +  "Key" + encryptionName + i + ".txt");
            if (myFile.createNewFile()) {
//                System.out.println("File created: " + myFile.getName());
                break;
            } else {
//                System.out.println("File already exists.");
            }
        }
//        System.out.println("File path:" + myFile.getPath());
        return myFile.getPath();
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
