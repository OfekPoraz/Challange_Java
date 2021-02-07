package com.company.two;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IEncryptionAlgorithm {
    public void encryptFile(String originalFilePath, String outputFilePath, String keyFilePath) throws IOException;
    public void decryptFile(String originalFilePath, String outputFilePath, String keyFilePath) throws IOException;
    public String getName();
}