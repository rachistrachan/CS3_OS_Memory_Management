import java.io.*;
import java.nio.*;
import java.util.*;

public class OS1Assignment {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter file path: ");
        String filePath = scanner.nextLine();
        scanner.close();
        List<Long> virtualAddress = readVA(filePath);
        List<Long> physicalAddress = convertToPhysical(virtualAddress);
        System.out.println(physicalAddress);

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
    private static List<Long> convertToPhysical(List<Long> virtualAddress) {
        List<Long> physicalAddresses = new ArrayList<>();
        // page table mapping as given
        int[] pageTable = new int[]{2, 4, 1, 7, 3, 5, 6};
        // loop through list of virtual addresses
        for (int i = 0; i < virtualAddress.size(); i++ ) {
            long vA = virtualAddress.get(i);
            // extract last 7 bits of vA
            int offset = (int) (vA & 0x7F);
            // shift vA 7 bits to the right
            // extract 5 bits = page number
            int pageNumber = (int) ((vA >>> 7) & 0x1F);
            // get frameNumber from pageTable using pageNumber as index
            long frameNumber = pageTable[pageNumber];
            // shift frame number by 7 bits so it is in higher part of address
            long shiftFrame = frameNumber << 7;
            // combine shifted frame number with offset = to create physical address
            long pA = shiftFrame | offset;
            physicalAddresses.add(pA);
        }
        return physicalAddresses;

        }




}
