/**
* STRRAC002
* Rachel Strachan
* OS1Assignment
* April 2024
*/
import java.io.*;
import java.nio.*;
import java.util.*;

public class OS1Assignment {

    /**
     * Reads virtual addresses from a binary file specified by filePath.
     * Each address is assumed to be 8 bytes, stored in little-endian format.
     *
     * @param filePath the path to the binary file containing the virtual addresses.
     * @return a list of virtual addresses as Long values.
     */
    private static List<Long> readVA(String filePath) {
        ArrayList<Long> addresses = new ArrayList<>();
        FileInputStream fileInput = null;
        DataInputStream dataInput = null;
            //
            try  {
                 fileInput = new FileInputStream(filePath);
                 dataInput = new DataInputStream(fileInput);
                // Array to hold 8 bytes = 64 bits
                byte[] bytes = new byte[8];
                // store number of bytes read
                int bytesRead;
                // read first block of bytes
                bytesRead = dataInput.read(bytes);
                // continue reading in bytes until end is reached
                while (bytesRead!= -1) {
                    // make sure all 8 bytes are read
                    if (bytesRead == bytes.length){
                        // converts 8 bytes into a long value
                        // sets byte order to Little Endian = least significant bytes first
                        long address = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getLong();
                        // add to address list
                        addresses.add(address);
                    } else{
                        System.out.println("Incomplete data read: Expected 8 bytes, got " + bytesRead);
                    }
                    // Read the next block of bytes
                    bytesRead = dataInput.read(bytes);
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + filePath);
            } catch (IOException e) {
                System.out.println("Error reading the file: " + filePath);
            } finally {
            // Close both streams ensuring non-null and ignoring potential exceptions on close
            if (dataInput != null) {
                try {
                    dataInput.close();
                } catch (IOException e) {
                    System.out.println("Failed to close the data input stream.");
                }
            }
            if (fileInput != null) {
                try {
                    fileInput.close();
                } catch (IOException e) {
                    System.out.println("Failed to close the file input stream.");
                }
            }
            }
        return addresses;
    }

    /**
     * Converts a list of virtual addresses to physical addresses using a given page table.
     *
     * @param virtualAddress the list of virtual addresses to be converted.
     * @return a list of physical addresses.
     */
    private static List<Long> convertToPhysical(List<Long> virtualAddress) {
        List<Long> physicalAddresses = new ArrayList<>();
        // page table mapping as given
        int[] pageTable = new int[]{2, 4, 1, 7, 3, 5, 6};
        // loop through list of virtual addresses
        for (int i = 0; i < virtualAddress.size(); i++ ) {
            long vA = virtualAddress.get(i);
            // extract last 7 bits of vA
            // 0x7F used as mask (lower 7 bits set to 1, and the others to 0)
            int offset = (int) (vA & 0x7F);
            // shift vA 7 bits (2^7) to the right and extract last 5 bits (2^5)
            int pageNumber = (int) (vA / 128) % 32;
            // get frameNumber from pageTable using pageNumber as index
            long frameNumber = pageTable[pageNumber];
            // shift frame number by 7 bits (2^7 =128) so it is in higher part of address
            long shiftFrame = frameNumber * 128;
            // combine shifted frame number with offset = to create physical address
            long pA = shiftFrame | offset;
            // add physical address to list
            physicalAddresses.add(pA);
        }
        return physicalAddresses;

        }

    /**
     * Converts a long integer address to a hexadecimal string.
     *
     * @param address the address to convert.
     * @return the hexadecimal string representation of the address.
     */
    private static String toHexString(long address) {
        return String.format("0x%03X", address);
    }

    /**
     * Writes a list of physical addresses to a file named "output-OS1".
     *
     * @param physicalAddress the list of physical addresses to write.
     */
    private static void writeToFile(List<Long> physicalAddress) {
        try (BufferedWriter w = new BufferedWriter(new FileWriter("output-OS1"))) {
            for (int i = 0; i < physicalAddress.size(); i++){
                long address = physicalAddress.get(i);
                String hexString = toHexString(address);
                w.write(hexString);
                w.newLine();
            }
            System.out.println("Writing to 'output-OS1' complete.");
        } catch (IOException e) {
            System.out.println("Error writing to the file: output-OS1");
        }
    }

    /**
     * Main method for the address conversion process. Prompts the user for a file path,
     * reads virtual addresses, converts them to physical addresses, and writes them to a file.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter file path: ");
        String filePath = scanner.nextLine();
        scanner.close();
        List<Long> virtualAddress = readVA(filePath);
        List<Long> physicalAddress = convertToPhysical(virtualAddress);
        writeToFile(physicalAddress);

    }

}
