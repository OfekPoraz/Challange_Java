package com.company.three;

import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileEncryptorTest {

    private static final String originalPath = "C:\\Users\\ofeko\\IdeaProjects\\Challange_Java\\src\\com\\company\\three\\Hi.txt";// Path to file to be encrypted
    private static ArrayList<String> encryptedPath = new ArrayList<>();
    private static ArrayList<String> decryptedPath = new ArrayList<>();
    private static ArrayList<String> keyPath = new ArrayList<>();
    private static List<FileEncryptor> FET = new ArrayList<FileEncryptor>(); //new FileEncryptor(new ShiftUpEncryption());

    @BeforeAll
    public static void beforeAll() throws IOException {
        FET.add(new FileEncryptor(new ShiftUpEncryption()));
        FET.add(new FileEncryptor(new ShiftMultiplyEncryption()));
        FET.add(new FileEncryptor(new DoubleEncryption(new ShiftMultiplyEncryption(), new ShiftUpEncryption())));
        FET.add(new FileEncryptor(new RepeatEncryption(4, new ShiftUpEncryption())));
        FET.add(new FileEncryptor(new XorEncryption()));
        int i = 0;
        for (FileEncryptor FETLoop : FET){
            encryptedPath.add(FETLoop.createEncryptedDecryptedFile(originalPath, true, FETLoop.getName()));
            keyPath.add(FETLoop.setNewKey(originalPath, FETLoop.getName()));
            FETLoop.encryptFile(originalPath,encryptedPath.get(i), keyPath.get(i));
            decryptedPath.add(FETLoop.createEncryptedDecryptedFile(originalPath, false, FETLoop.getName()));
            i++;

        }
    }

    @org.junit.jupiter.api.Test
    void decryptFile() throws IOException {

        int i = 0;
        for (FileEncryptor FETLoop : FET){
            FETLoop.decryptFile(encryptedPath.get(i), decryptedPath.get(i), keyPath.get(i));

            byte[] file1Bytes = Files.readAllBytes(Paths.get(decryptedPath.get(i)));
            byte[] file2Bytes = Files.readAllBytes(Paths.get(originalPath));

            String file1 = new String(file1Bytes, StandardCharsets.UTF_8);
            String file2 = new String(file2Bytes, StandardCharsets.UTF_8);

            assertEquals(file1, file2);
            i++;
        }

    }
}