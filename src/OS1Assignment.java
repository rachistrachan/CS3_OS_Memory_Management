import java.io.*;
import java.lang.reflect.Array;
import java.nio.*;
import java.util.*;

public class OS1Assignment {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter file path: ");
        String filePath = scanner.nextLine();
        scanner.close();
        List<Long> virtualAddress = readVA(filePath);
        System.out.println(virtualAddress);

    }
    private static List<Long> readVA(String filePath) {
        List<Long> addresses = new ArrayList<>();
            try (DataInputStream data = new DataInputStream(new FileInputStream(filePath))) {
                byte[] bytes = new byte[8]; // Array to hold 8 bytes
                while ((data.read(bytes)) != -1) {
                    long address = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getLong();
                    addresses.add(address);
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + filePath);
            } catch (IOException e) {
                System.out.println("Error reading the file: " + filePath);
            }
        return addresses;
    }

    // another arrayList containing physical addresses
    // method that takes in long virtual address and changes it to physical address
    private static void convertToPhysical(ArrayList<Long> virualAddress) {
        int[] pageTable = new int[]{2, 4, 1, 7, 3, 5, 6};




    }
}