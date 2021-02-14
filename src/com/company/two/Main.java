package com.company.two;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        //Creating new instances for each encryption we want to do and initialize it.
        FileEncryptor fileEncryptorShiftUp = new FileEncryptor(new ShiftUpEncryption());//ShiftUp
        FileEncryptor fileEncryptorShiftMultiply = new FileEncryptor(new ShiftUpEncryption());//ShiftMultiply
        FileEncryptor fileEncryptorDouble = new FileEncryptor(new DoubleEncryption(new ShiftUpEncryption(), new ShiftMultiplyEncryption()));//ShiftDouble
        FileEncryptor fileEncryptorRepeat = new FileEncryptor(new RepeatEncryption(4, new ShiftUpEncryption()));//Repeat
        FileEncryptor fileEncryptorXor = new FileEncryptor(new XorEncryption());//Xor

        String originalPath = "C:\\Users\\ofeko\\IdeaProjects\\Challange_Java\\src\\com\\company\\two\\Hi.txt";// Path to file to be encrypted
        //creating encrypted empty file
        String encryptedPath = fileEncryptorDouble.createEncryptedDecryptedFile(originalPath, true, fileEncryptorDouble.getName());
        //creating new key file
        String keyPath = fileEncryptorDouble.setNewKey(originalPath, fileEncryptorDouble.getName());
        //preforming encryption. to be saved in encryption file we created earlier.
        fileEncryptorDouble.encryptFile(originalPath,encryptedPath, keyPath);

        //creating decrypted empty file
        String decryptedPath = fileEncryptorDouble.createEncryptedDecryptedFile(originalPath, false, fileEncryptorDouble.getName());
        //preforming decryption. to be saved in decryption file we created earlier.
        fileEncryptorDouble.decryptFile(encryptedPath, decryptedPath, keyPath);

        //creating encrypted empty file
        String encryptedPathNew = fileEncryptorShiftMultiply.createEncryptedDecryptedFile(originalPath, true, fileEncryptorShiftMultiply.getName());
        //creating new key file
        String keyPathNew = fileEncryptorShiftMultiply.setNewKey(originalPath, fileEncryptorShiftMultiply.getName());
        //preforming encryption. to be saved in encryption file we created earlier.
        fileEncryptorShiftMultiply.encryptFile(originalPath,encryptedPathNew, keyPathNew);

        //creating decrypted empty file
        String decryptedPathNew = fileEncryptorShiftMultiply.createEncryptedDecryptedFile(originalPath, false, fileEncryptorShiftMultiply.getName());
        //preforming decryption. to be saved in decryption file we created earlier.
        fileEncryptorShiftMultiply.decryptFile(encryptedPathNew, decryptedPathNew, keyPathNew);

        //creating encrypted empty file
        String encryptedPathUp = fileEncryptorShiftUp.createEncryptedDecryptedFile(originalPath, true, fileEncryptorShiftUp.getName());
        //creating new key file
        String keyPathUp = fileEncryptorShiftUp.setNewKey(originalPath, fileEncryptorShiftUp.getName());
        //preforming encryption. to be saved in encryption file we created earlier.
        fileEncryptorShiftUp.encryptFile(originalPath,encryptedPathUp, keyPathUp);

        //creating decrypted empty file
        String decryptedPathUp = fileEncryptorShiftUp.createEncryptedDecryptedFile(originalPath, false, fileEncryptorShiftUp.getName());
        //preforming decryption. to be saved in decryption file we created earlier.
        fileEncryptorShiftUp.decryptFile(encryptedPathUp, decryptedPathUp, keyPathUp);

        //creating encrypted empty file
        String encryptedPathRepeat = fileEncryptorRepeat.createEncryptedDecryptedFile(originalPath, true, fileEncryptorRepeat.getName());
        //creating new key file
        String keyPathRepeat = fileEncryptorRepeat.setNewKey(originalPath, fileEncryptorRepeat.getName());
        //preforming encryption. to be saved in encryption file we created earlier.
        fileEncryptorRepeat.encryptFile(originalPath,encryptedPathRepeat, keyPathRepeat);

        //creating decrypted empty file
        String decryptedPathRepeat = fileEncryptorRepeat.createEncryptedDecryptedFile(originalPath, false, fileEncryptorRepeat.getName());
        //preforming decryption. to be saved in decryption file we created earlier.
        fileEncryptorRepeat.decryptFile(encryptedPathRepeat, decryptedPathRepeat, keyPathRepeat);

        //creating encrypted empty file
        String encryptedPathXor = fileEncryptorXor.createEncryptedDecryptedFile(originalPath, true, fileEncryptorXor.getName());
        //creating new key file
        String keyPathXor = fileEncryptorXor.setNewKey(originalPath, fileEncryptorXor.getName());
        //preforming encryption. to be saved in encryption file we created earlier.
        fileEncryptorXor.encryptFile(originalPath,encryptedPathXor, keyPathXor);

        //creating decrypted empty file
        String decryptedPathXor = fileEncryptorXor.createEncryptedDecryptedFile(originalPath, false, fileEncryptorXor.getName());
        //preforming decryption. to be saved in decryption file we created earlier.
        fileEncryptorXor.decryptFile(encryptedPathXor, decryptedPathXor, keyPathXor);

    }
}
