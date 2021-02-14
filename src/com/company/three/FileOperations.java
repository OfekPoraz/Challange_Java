package com.company.three;

import java.io.*;
import java.util.Scanner;

public class FileOperations {

    public int readIntFromFile(String path) throws FileNotFoundException {
        return (new Scanner(new File(path))).nextInt();
    }

    public void addIntToFile(String path, int key) throws IOException {
        //because we want to add an Int we don't need PrintWrite, but only a FileWriter
        Writer wr = new FileWriter(path);
        wr.write(Integer.toString(key)); //casting the int into string
        wr.close();
    }

    public void addStringToFile(String path, StringBuffer line) throws IOException {
        //we set a new PrintWriter in order to enter a String to the file, each string in different line. this is way append
        //is set to true
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path, true)));
        out.println(line);
        out.close();
    }

    public String createEncryptionDecryptionFile(String path, String filename, boolean encryptOrDecrypt , String encryptionName) throws IOException {
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
                //if file already exist we don't create one and we tell the user it exist but still override the file
                System.out.println("File created: " + myFile.getName());
                break;
            } else {
                System.out.println("File already exists. Trying to create with different name");
            }
        }
        //we return to the user the name of the file and it's path and also return it a parameter (the function return String)
        System.out.println("File path:" + myFile.getPath());
        return myFile.getPath();
    }

    public String createKeyFile(String path ,String encryptionName) throws IOException {
        //here we do almost the same as in the previous function only to the key
        //I could have create one function to do all with switch case but decided to do it separately
        File myFile = null;
        for (int i = 0; i < 1000 ; i++) {
            myFile = new File(path + File.separator +  "Key" + encryptionName + i + ".txt");
            if (myFile.createNewFile()) {
                System.out.println("File created: " + myFile.getName());
                break;
            } else {
                System.out.println("File already exists.");
            }
        }
        System.out.println("File path:" + myFile.getPath());
        return myFile.getPath();
    }
}
