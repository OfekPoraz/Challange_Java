package com.company.four;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class FileEncryptorTest {

    private static final String originalPath = "C:\\Users\\ofeko\\IdeaProjects\\Challange_Java\\src\\com\\company\\four\\Hi.txt";// Path to file to be encrypted
    private String enPath;
    private String dePath;
    private String keyPath;
    private static Map<Integer, String> index = new HashMap<Integer, String>();
    private static Map<String, FileEncryptor> fileEncryptor = new HashMap<String, FileEncryptor>();
    private static Map<String, String> enPathMap = new HashMap<String, String>();
    private static Map<String, String> dePathMap = new HashMap<String, String>();
    private static Map<String, String> keyPathMap = new HashMap<String, String>();


    @BeforeAll
    static void beforeAll() throws IOException {
        index.put(1, "ShiftUp");
        index.put(2, "ShiftMultiply");
        index.put(3, "Double");
        index.put(4, "Repeat");
        index.put(5, "Xor");
        fileEncryptor.put("ShiftUp", new FileEncryptor(new ShiftUpEncryption()));
        fileEncryptor.put("ShiftMultiply", new FileEncryptor(new ShiftMultiplyEncryption()));
        fileEncryptor.put("Double", new FileEncryptor(new DoubleEncryption(new ShiftUpEncryption(), new ShiftMultiplyEncryption())));
        fileEncryptor.put("Repeat", new FileEncryptor(new RepeatEncryption(3, new ShiftUpEncryption())));
        fileEncryptor.put("Xor", new FileEncryptor(new XorEncryption()));
        for (int i = 1; i <=5 ; i++){
            enPathMap.put(index.get(i), fileEncryptor.get(index.get(i)).createEncryptedDecryptedFile(
                    originalPath, true, fileEncryptor.get(index.get(i)).getName()));
            dePathMap.put(index.get(i), fileEncryptor.get(index.get(i)).createEncryptedDecryptedFile(
                    originalPath, false, fileEncryptor.get(index.get(i)).getName()));
            keyPathMap.put(index.get(i), fileEncryptor.get(index.get(i)).setNewKey(
                    originalPath, fileEncryptor.get(index.get(i)).getName()));
        }


    }

    @Test
    public void Test() throws IOException {
        for (int i = 1 ; i <= 5 ; i++){
            fileEncryptor.get(index.get(i)).encryptFile(originalPath, enPathMap.get(index.get(i)), keyPathMap.get(index.get(i)));
            fileEncryptor.get(index.get(i)).decryptFile(enPathMap.get(index.get(i)), dePathMap.get(index.get(i)), keyPathMap.get(index.get(i)));

            byte[] file1Bytes = Files.readAllBytes(Paths.get(dePathMap.get(index.get(i))));
            byte[] file2Bytes = Files.readAllBytes(Paths.get(originalPath));

            String file1 = new String(file1Bytes, StandardCharsets.UTF_8);
            String file2 = new String(file2Bytes, StandardCharsets.UTF_8);
            assertEquals(file1, file2);
        }
    }
    @Test
    void compareTest(){
        Collection<FileEncryptor> fileEncryptorCollection = fileEncryptor.values();
        ArrayList<FileEncryptor> fileEncryptorArrayList = new ArrayList<>(fileEncryptorCollection);
        fileEncryptorArrayList.sort(new FileEncryptor.Compare());

        System.out.println("Sorted By key strength:");
        for (FileEncryptor encryptor : fileEncryptorArrayList) {
            System.out.println(encryptor.getName());
        }
        assertTrue(true);
    }

    @Test
    void testNullException(){
        for (int i = 1 ; i <= 5 ; i ++){
            int finalI = i;
            NullPointerException thrown = assertThrows(NullPointerException.class,
                    () -> {
                        fileEncryptor.get(index.get(finalI)).encryptFile(null, null, null);
                    });
            System.out.println("Checking Null exception");
        }

    }

    @Test
    void testIOException(){
        for (int i = 1 ; i <= 5 ; i ++) {
            int finalI = i;
            IOException thrown = assertThrows(IOException.class,
                    () -> {
                        fileEncryptor.get(index.get(finalI)).encryptFile("C:\\Users\\ofeko\\IdeaProjects\\Challange_Java\\src\\com\\company\\five\\Hi.txt", dePathMap.get(index.get(finalI)), keyPathMap.get(index.get(finalI)));
                    });
        }
        System.out.println("Checking IOException");
    }

    @Test
    void testNoSuchElementException(){
        for (int i = 1 ; i <= 5 ; i ++) {
            int finalI = i;
            NoSuchElementException thrown = assertThrows(NoSuchElementException.class,
                    () -> {
                        fileEncryptor.get(index.get(finalI)).encryptFile(enPathMap.get(index.get(finalI)), dePathMap.get(index.get(finalI)), "C:\\Users\\ofeko\\IdeaProjects\\Challange_Java\\src\\com\\company\\four\\WrongKey.txt");
                    });
            System.out.println("Checking No Such Element Exception");

        }
    }

}