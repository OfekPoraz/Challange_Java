package com.company.three;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class DoubleEncryption implements IEncryptionAlgorithm {
    // In this algorithm we will use two algorithms to preform "double" encryption
    // We will encrypt the file first with one algorithm and than again with a second algorithm
    // The same for the decryption process but with reverse order
    private final FileOperations files = new FileOperations(); //Instance of file operation in order to do files operations
    private final IEncryptionAlgorithm algorithm1; //Instance of the first algorithm we will use in this double encryption
    private final IEncryptionAlgorithm algorithm2; //Instance of the second algorithm we will use in this double encryption
    private String pathToKey = null; // global String to store new key file path
    private final String name = "Double"; //Setting the name of the encryption
    @Override
    public String getName() { // Getter for the name
        return name;
    }

    public DoubleEncryption(IEncryptionAlgorithm algorithm1, IEncryptionAlgorithm algorithm2) { //Constructor
        this.algorithm1 = algorithm1;
        this.algorithm2 = algorithm2;
    }

    @Override
    public void encryptFile(String originalFilePath, String outputFilePath, String keyFilePath) throws IOException {
        //Creating a new key file with new key value and adding the value to the file
        String parentPath = new File(originalFilePath).getAbsoluteFile().getParent();
        pathToKey = files.createKeyFile(parentPath, "Double");
        Random rand = new Random();
        int key = rand.nextInt(20);
        files.addIntToFile(pathToKey, key);

        //creating new "middle" file for the output after one encryption
        File myFile = new File(outputFilePath);
        //getting the name of the file without extensions
        String fileWithoutExtensions = myFile.getName().replaceFirst("[.][^.]+$", "");
        String innerPath = files.createEncryptionDecryptionFile(parentPath, fileWithoutExtensions, true, algorithm1.getName());

        //preforming the first encryption
        algorithm1.encryptFile(originalFilePath, innerPath, keyFilePath);
        //preforming the second encryption
        algorithm2.encryptFile(innerPath, outputFilePath, pathToKey);
    }

    @Override
    public void decryptFile(String originalFilePath, String outputFilePath, String keyFilePath) throws IOException {
        String parentPath = new File(originalFilePath).getAbsoluteFile().getParent();
        File myFile = new File(originalFilePath);
        //getting the name of the file without extensions
        String fileWithoutExtensions = myFile.getName().replaceFirst("[.][^.]+$", "");
        //creating new "middle" file for the output after one decryption
        String innerPathDecrypt = files.createEncryptionDecryptionFile(parentPath, fileWithoutExtensions, false, algorithm1.getName());
        //preforming the second decryption
        algorithm2.decryptFile(originalFilePath, innerPathDecrypt, pathToKey);
        //preforming the first decryption
        algorithm1.decryptFile(innerPathDecrypt, outputFilePath, keyFilePath);
    }
}
