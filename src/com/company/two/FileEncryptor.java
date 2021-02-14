package com.company.two;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class FileEncryptor implements IEncryptionAlgorithm {
    /*
    Class that will be responsible for the encryption process
    the methods will be:
        1.create file using the file operations class
        2.create key file using file operations class and creating a key using Random package
        3.encrypting the file using the encryption algorithm of the algorithm we initialize at the beginning
        4.the same as 3 but with decryption
        5.Get name function that calls the algorithm's name
     */
    private final IEncryptionAlgorithm algorithm;
    private final FileOperations fileOperations;

    public FileEncryptor(IEncryptionAlgorithm algorithm) { //constructor
        this.algorithm = algorithm;
        this.fileOperations = new FileOperations();
    }

    @Override
    public String getName() {
        //Get name function that calls the algorithm's name
        return algorithm.getName();
    }

    public String setNewKey(String filePath, String encryptionName) throws IOException {
        // create key file using file operations class and creating a key using Random package
        File myFile = new File(filePath);
        String parentPath = myFile.getAbsoluteFile().getParent();
        Random rand = new Random();
        int key = rand.nextInt(500);
        //after creating a random key we set the path to key to the actual file we created and then adding the key to the file
        String pathToKey = fileOperations.createKeyFile(parentPath, encryptionName);
        fileOperations.addIntToFile(pathToKey, key);
        return pathToKey;
    }

    public String createEncryptedDecryptedFile(String filePath, boolean encryptOrDecrypt, String encryptionName) throws IOException {
        // create file using the file operations class
        File myFile = new File(filePath);
        String parentPath = myFile.getAbsoluteFile().getParent();
        //getting the name of the file without extensions
        String fileWithoutExtensions = myFile.getName().replaceFirst("[.][^.]+$", "");
        return fileOperations.createEncryptionDecryptionFile(parentPath, fileWithoutExtensions, encryptOrDecrypt, encryptionName);
    }

    @Override
    public void encryptFile(String originalFilePath, String outputFilePath, String keyFilePath) throws IOException {
        //encrypting the file using the encryption algorithm of the algorithm we initialize at the beginning
        algorithm.encryptFile(originalFilePath, outputFilePath, keyFilePath);
    }

    @Override
    public void decryptFile(String originalFilePath, String outputFilePath, String keyFilePath) throws IOException {
        algorithm.decryptFile(originalFilePath, outputFilePath, keyFilePath);
    }
}
