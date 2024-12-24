package com.helen.framework.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class GlobalMethods {
    public static String generatePassword(){
        String upLetters = RandomStringUtils.random(3, 'A', 'Z', true, false);
        String loLetters = RandomStringUtils.random(3, 'a', 'z', true, false);
        String numLetters = RandomStringUtils.random(3, '0', '9', false, true);
        String specialLetters = RandomStringUtils.random(2, 36, 38, false, false);

        String pwd = upLetters
                .concat(loLetters)
                .concat(numLetters)
                .concat(specialLetters);
        return pwd;
    }
    /**
     *
     * @param length the length of the final string
     * @return a string of the specific length
     */
    public static String getRandomString(int length) {
        String strings= "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz";
        String rstring ="";
        for (int i=0; i<length ; i++) {
            Random randomGenerator = new Random();
            int randomInt = randomGenerator.nextInt(strings.length());
            rstring += strings.substring(randomInt,randomInt+1);
        }
        return rstring ;
    }

    public static String getRandomNumberString(int length) {
        String strings= "0123456789";
        String rstring ="";
        for (int i=0; i<length ; i++) {
            Random randomGenerator = new Random();
            int randomInt = randomGenerator.nextInt(strings.length());
            rstring += strings.substring(randomInt,randomInt+1);
        }
        return rstring ;
    }
    public static double getBalanceFromString(String origin) {
        origin = origin.replace(",", "");
        String regEx = "(([1-9][0-9]*)+(.[0-9]{1,2})?$)";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(origin);
        String amount = "Nothing";
        if (matcher.find()) {
            amount = matcher.group(1);
        }

        return Double.valueOf(amount);
    }
    public class FileExample {
        public static void main(String[] args) {
            // Define the file path and name
            String filePath = "example.txt";

            try {
                // Step 1: Create the File object
                File file = new File(filePath);

                // Step 2: Check if the file already exists
                if (file.createNewFile()) {
                    System.out.println("File created: " + file.getName());
                } else {
                    System.out.println("File already exists.");
                }

                // Step 3: Write content to the file
                FileWriter writer = new FileWriter(file);
                writer.write("Hello, this is an example of writing to a file in Java.");
                writer.close();

                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }
}
