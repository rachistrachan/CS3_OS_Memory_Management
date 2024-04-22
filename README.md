OS1Assignment Memory Management

This project processes virtual memory addresses by converting them to physical memory addresses using a 
given page table. The program reads in virtual memory addresses from a binary file (specified by the user), 
performs the conversion, and writes the physical addresses in hexadecimal string format to an output file.


Features
- Read virtual addresses from a binary file containing 8 byte virtual addresses
- Convert to physical addresses using hardcoded page table
- Write physical addresses in hexadecimal format to a file


Installation
Ensure you have Java installed.


Usage
1. Compile the Java program 
2. Run the compiled program
3. Enter the file path to the binary file containing the virtual addresses when prompted.


Input File Format
The input file should be a binary file with virtual addresses stored as 8-byte values in little-endian format.


Output
The converted physical addresses are written to a file named output-OS1 in the same directory where the program is run. 
Each address is on a new line, formatted as a hexadecimal string.
