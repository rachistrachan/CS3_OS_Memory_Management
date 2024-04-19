import java.io.*;
import java.lang.reflect.Array;
import java.nio.*;
import java.util.*;

public class OS1Assignment {
    private static final int[] pageTable = new int[]{2, 4, 1, 7, 3, 5, 6};

    public static void main(String[] args) {


        // String fileInput =
        String filePath = "/Users/rachelstrachan/Documents/UNI_2024/CS3/SEM1/OS/Assignment_1/Memory_Management/src/OS1testsequence";
        File file = new File(filePath);
        ArrayList<Long> virtualAddress = new ArrayList<Long>();// Array to hold virtual addresses in long format

        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] bytes = new byte[8]; // Array to hold 8 bytes

            int bytesRead;

            while ((bytesRead = fis.read(bytes)) != -1) {
                if (bytesRead == 8) { // Ensure 8 bytes are read
                    // reverseArray(bytes); // Reverse the array
                    long address = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getLong();
                    virtualAddress.add(address);

                    // Process the reversed array or print it

                } else {
                    System.out.println("Last read did not contain 8 bytes.");
                } System.out.println(virtualAddress);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // another arrayList containing physical addresses
    // method that takes in long virtual address and changes it to physical address
    /*private static ArrayList<Long> convertToPhysical (ArrayList<Long> virualAddress){
        ArrayList<Long> physicalAddress = new ArrayList<Long>();
        for (int i = 0; i < virualAddress.size(); i++){
            long virtual = virualAddress.get(i);
            long physical = mapToPhysical(virtual);


        }

    }

    private static long mapToPhysical(long virtual){
        return null;

    }*/


    // Utility method to print byte array in hexadecimal
    private static void printBytes(byte[] bytes) {
        for (byte b : bytes) {
            System.out.printf("%02X ", b);
        }
        System.out.println();
    }
}