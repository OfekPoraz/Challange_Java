package com.company;

import java.io.*;

public class FileOperations {

    public String createEncryptionDecryptionFile(String path, String filename, boolean encryptOrDecrypt) throws IOException {
        //here we create a new file. if we want to encrypt we pass true in the boolean field. otherwise false.
        File myFile;
        // we create a new file in the directory of the original file with the same name but different ending
        if (encryptOrDecrypt) {
            myFile = new File(path + File.separator + filename + "_Encrypted.txt");
        } else {
            myFile = new File(path + File.separator + filename + "_Decrypted.txt");
        }
        if (myFile.createNewFile()) {
            //if file already exist we don't create one and we tell the user it exist but still override the file
            System.out.println("File created: " + myFile.getName());
        } else {
            System.out.println("File already exists.");
        }
        //we return to the user the name of the file and it's path and also return it a parameter (the function return String)
        System.out.println("File path:" + myFile.getPath());
        return myFile.getPath();
    }

    public String createKeyFile(String path) throws IOException {
        //here we do almost the same as in the previous function only to the key
        //I could have create one function to do all with switch case but decided to do it separately
        File myFile = new File(path + File.separator + "Key.txt");
        if (myFile.createNewFile()) {
            System.out.println("File created: " + myFile.getName());
            System.out.println("File path:" + myFile.getPath());
        } else {
            System.out.println("File already exists.");
        }
        return myFile.getPath();
    }

    public void addStringToFile(String path, StringBuffer line) throws IOException {
        //we set a new PrintWriter in order to enter a String to the file, each string in different line. this is way append
        //is set to true
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path, true)));
        out.println(line);
        out.close();
    }

    public void addIntToFile(String path, int key) throws IOException {
        //because we want to add an Int we don't need PrintWrite, but only a FileWriter
        Writer wr = new FileWriter(path);
        wr.write(Integer.toString(key)); //casting the int into string
        wr.close();
    }
}
