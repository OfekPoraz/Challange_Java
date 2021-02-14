package com.company.four;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.NoSuchElementException;
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
    protected String keyPath = null;

    public FileEncryptor(IEncryptionAlgorithm algorithm) { //constructor
        this.algorithm = algorithm;
        this.fileOperations = new FileOperations();
    }

    @Override
    public String getName() {
        //Get name function that calls the algorithm's name
        return algorithm.getName();
    }

    public String setNewKey(String filePath, String encryptionName) throws IOException, NullPointerException {
        try {
            // create key file using file operations class and creating a key using Random package
            File myFile = new File(filePath);
            String parentPath = myFile.getAbsoluteFile().getParent();
            Random rand = new Random();
            int key = rand.nextInt(200)+140;
            //after creating a random key we set the path to key to the actual file we created and then adding the key to the file
            String pathToKey = fileOperations.createKeyFile(parentPath, encryptionName);
            fileOperations.addIntToFile(pathToKey, key);
            this.keyPath = pathToKey;
            return pathToKey;
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

    public String createEncryptedDecryptedFile(String filePath, boolean encryptOrDecrypt, String encryptionName) throws IOException, NullPointerException {
        try {
            // create file using the file operations class
            File myFile = new File(filePath);
            String parentPath = myFile.getAbsoluteFile().getParent();
            //getting the name of the file without extensions
            String fileWithoutExtensions = myFile.getName().replaceFirst("[.][^.]+$", "");
            return fileOperations.createEncryptionDecryptionFile(parentPath, fileWithoutExtensions, encryptOrDecrypt, encryptionName);
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
    public void encryptFile(String originalFilePath, String outputFilePath, String keyFilePath) throws IOException, NoSuchElementException, NullPointerException {
        try {
            //encrypting the file using the encryption algorithm of the algorithm we initialize at the beginning
            algorithm.encryptFile(originalFilePath, outputFilePath, keyFilePath);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("No key");
        } catch (ClassCastException e){
            throw new ClassCastException("Invalid key format");
        } catch (IOException e) {
            System.out.println("Invalid Path format");
            throw new IOException("Invalid Path format, IO Exception");
        } catch (NullPointerException e){
            throw new NullPointerException("Invalid Path format, Null");
        }
    }

    @Override
    public void decryptFile(String originalFilePath, String outputFilePath, String keyFilePath) throws IOException, NullPointerException{
        try {
            algorithm.decryptFile(originalFilePath, outputFilePath, keyFilePath);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("No key");
        } catch (ClassCastException e){
            throw new ClassCastException("Invalid key format");
        } catch (IOException e) {
            System.out.println("Invalid Path format");
            throw new IOException("Invalid Path format, IO Exception");
        } catch (NullPointerException e){
            throw new NullPointerException("Invalid Path format, Null");
        }
    }

    @Override
    public int getKeyStrength(String keyFilePath) throws IOException, NullPointerException {
        try {
            return algorithm.getKeyStrength(keyFilePath);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("No key");
        } catch (ClassCastException e){
            throw new ClassCastException("Invalid key format");
        } catch (IOException e) {
            System.out.println("Invalid Path format");
            throw new IOException("Invalid Path format, IO Exception");
        } catch (NullPointerException e){
            throw new NullPointerException("Invalid Path format, Null");
        }
    }
    static class Compare implements Comparator<FileEncryptor> {

        @Override
        public int compare(FileEncryptor o1, FileEncryptor o2) throws NullPointerException {
            try {
                return o1.getKeyStrength(o1.keyPath) - o2.getKeyStrength(o2.keyPath);
            } catch (IOException e) {
                e.printStackTrace();
                return 0;
            }
        }

    }
}
