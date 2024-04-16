import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class OS1Assignment {
    public static void main(String[] args) {
        String filePath = "/Users/rachelstrachan/Documents/UNI_2024/CS3/SEM1/OS/Assignment_1/Memory_Management/src/OS1testsequence";
        File file = new File(filePath);

        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] bytes = new byte[8]; // Array to hold 8 bytes
            int bytesRead;

            while ((bytesRead = fis.read(bytes)) != -1) {
                if (bytesRead == 8) { // Ensure 8 bytes are read
                   // reverseArray(bytes); // Reverse the array
                    // Process the reversed array or print it
                    printBytes(bytes);
                } else {
                    System.out.println("Last read did not contain 8 bytes.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Utility method to reverse a byte array
    private static void reverseArray(byte[] data) {
        for (int i = 0; i < data.length / 2; i++) {
            byte temp = data[i];
            data[i] = data[data.length - 1 - i];
            data[data.length - 1 - i] = temp;
        }
    }

    // Utility method to print byte array in hexadecimal
    private static void printBytes(byte[] bytes) {
        for (byte b : bytes) {
            System.out.printf("%02X ", b);
        }
        System.out.println();
    }
}
