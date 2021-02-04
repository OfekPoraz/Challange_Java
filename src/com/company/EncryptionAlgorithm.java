package com.company;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Random;
import java.util.Scanner;

public class EncryptionAlgorithm {
    FileOperations files = new FileOperations();

    //I wanted to make the encryption and decryption in the same function to avoid duplicate code
    //if we want to encrypt we call this function but with key path the same as file path because we won't
    //use it so it doesn't affect our code

    public void encryptAlgorithm(String filePath, String keyPath, boolean encrypt) throws IOException {
        //Getting the Directory of the file
        File myFile = new File(filePath);
        String parentPath = myFile.getAbsoluteFile().getParent();
        //getting the name of the file without extensions
        String fileWithoutExtensions = myFile.getName().replaceFirst("[.][^.]+$", "");


        //Creating encrypted or decrypted file, it will be handled in Fileoperations class
        String encryptedFilePath = files.createEncryptionDecryptionFile(parentPath, fileWithoutExtensions, encrypt);

        //here we set the path to the key or the key itself. if we decrypt we only set the path to what we passed
        //with the function, if we want to encrypt, encrypt is set to true and it will be explained in the if block.
        String pathToKey = keyPath;
        if (encrypt) { // Only if we want to encrypt
            //Creating Key to the encryption
            Random rand = new Random();
            int key = rand.nextInt(500);
            //after creating a random key we set the path to key to the actual file we created and then adding the key to the file
            pathToKey = files.createKeyFile(parentPath);
            files.addIntToFile(pathToKey, key);
        }



        //here we are getting the key from the file, either it from the file we just created or from the path we got.
        int key = (new Scanner(new File(pathToKey))).nextInt();

        //accessing the file we want to encrypt/decrypt and reading it line by line
        File fileToEncrypt = new File(filePath);
        Scanner myReader = new Scanner(fileToEncrypt);
        while (myReader.hasNextLine()) { //while it has lines, for each line we generate a stringbuffer and adding him the new line
            String data = myReader.nextLine();
            StringBuffer encryptedData = new StringBuffer();
            for (int i = 0; i < data.length(); i++) {
                if (encrypt) { //the new line dependes if we want to encrypt/decrypt. encrypt we add the key, decrypt we subtract the key
                    encryptedData.append((char) (data.charAt(i) + key)); //casting the value to char
                } else {
                    encryptedData.append((char) (data.charAt(i) - key)); //casting the value to char
                }
            }
            files.addStringToFile(encryptedFilePath, encryptedData); // after going over the entire line we add the line to the file
        }


    }

    public void encryptAlgorithm(String filePath, boolean encrypt) throws IOException {
        encryptAlgorithm(filePath, filePath, encrypt); //this is only for comfort, if we want to encrypt we don't want to pass a
        //key path from Main because we don't have one and we don't want the user to change something
    }
}