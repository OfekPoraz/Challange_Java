package com.company.three;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class RepeatEncryption implements IEncryptionAlgorithm {
    /*
    In this encryption we want to preform specific encryption multiple times on a file.
    We get the numbers of time we want to repeat and the encryption we want to use and then preform it to encrypt and in reverse order to decrypt
     */
    private final int numberOfTimesToRepeat;//integer that stores numbers of times we want to repeat
    private final FileOperations files = new FileOperations(); //Instance of file operation in order to do files operations
    private final IEncryptionAlgorithm algorithm1; //Instance of the algorithm we will use in this repeat encryption
    private final String name = "Repeat"; //Setting the name of the encryption
    private ArrayList<String> innerPaths = new ArrayList<String>(); //array list of the paths of files we will create
    @Override
    public String getName() {
        return name;
    }

    public RepeatEncryption(int numberOfTimesToRepeat, IEncryptionAlgorithm algorithm1) { //constructor
        this.numberOfTimesToRepeat = numberOfTimesToRepeat;
        this.algorithm1 = algorithm1;
    }

    @Override
    public void encryptFile(String originalFilePath, String outputFilePath, String keyFilePath) throws IOException {
        String parentPath = new File(originalFilePath).getAbsoluteFile().getParent();
        File myFile = new File(outputFilePath);
        //getting the name of the file without extensions
        String fileWithoutExtensions = myFile.getName().replaceFirst("[.][^.]+$", "");
        //Creating the first "middle" file. we create file to each middle encryption we will do
        innerPaths.add(files.createEncryptionDecryptionFile(parentPath, fileWithoutExtensions, true, getName()));
        //Preforming the first encryption from the original file path to the inner path file.
        algorithm1.encryptFile(originalFilePath, innerPaths.get(0), keyFilePath);

        //we repeat this process from the encrypted file to a new encrypted file n-2 times. after this we did the encryption n-1 times.
        for (int i = 1; i < numberOfTimesToRepeat - 1 ; i++){
            innerPaths.add(files.createEncryptionDecryptionFile(parentPath, fileWithoutExtensions, true, getName()));
            algorithm1.encryptFile(innerPaths.get(i-1), innerPaths.get(i), keyFilePath);

        }
        //Preforming the last encryption and it's destination is the original output path
        algorithm1.encryptFile(innerPaths.get(numberOfTimesToRepeat-2), outputFilePath, keyFilePath);
    }

    @Override
    public void decryptFile(String originalFilePath, String outputFilePath, String keyFilePath) throws IOException {
        //here we do the same as encryption but in reverse order.
        algorithm1.decryptFile(originalFilePath, innerPaths.get(numberOfTimesToRepeat-2), keyFilePath);
        for (int i = numberOfTimesToRepeat-2 ; i > 1; i--){
            algorithm1.decryptFile(innerPaths.get(i), innerPaths.get(i-1), keyFilePath);
        }
        algorithm1.decryptFile(innerPaths.get(0), outputFilePath, keyFilePath);

    }
}
