import java.util.*;
import java.io.*;

/**
	This is the class that generates a Huffman encoding table and encodes a String using that encoding strategy
	
	@author: Cody Narber
*/
public class TestHuffman{
	public static void main(String[] args){
		String forGeneratingCounts = "";
		Scanner readFromConsole = new Scanner(System.in);
		try{
			System.out.println("What file to load for frequency counts: ");
			String fName = readFromConsole.nextLine();
			FileInputStream fis = new FileInputStream(fName);
			Scanner fscn = new Scanner(fis);
			while(fscn.hasNext()) forGeneratingCounts+=fscn.nextLine().toUpperCase().trim(); //Absorbing newline characters...not used in frequency counts
			fscn.close();
		} catch(Exception e){
			System.out.println("You must type an appropriate file name :(");
			System.out.println("\t"+e.getMessage());
			System.exit(1);
		}
		System.out.println("String: "+forGeneratingCounts);
				
		//This will construct the tree/lookup table
		HuffEncodable myHuffEncoding = new HuffCodes(); //TODO: HuffCodes is the name of your file
		myHuffEncoding.constructEncoding(forGeneratingCounts);
		
		//Testing the encoding, assume letters typed are letters seen in document 
		boolean keepEncoding = true;
		while(keepEncoding){
			System.out.println("(ASCII) Enter a string to encode to binary (just press enter to stop looping): ");
			String toEncode = readFromConsole.nextLine();
			keepEncoding = !toEncode.isEmpty();
			if(keepEncoding) System.out.println("\nEncoding:\n\t"+myHuffEncoding.encode(toEncode));
		}

		//Testing the decoding, assume only 1's and 0's typed 
		boolean keepDecoding = true;
		while(keepDecoding){
			System.out.println("(BINARY) Enter a binary sequence to convert to ASCII (just press enter to stop looping): ");
			String toDecode = readFromConsole.nextLine();
			keepDecoding = !toDecode.isEmpty();
			if(keepDecoding) System.out.println("\nDecoding:\n\t"+myHuffEncoding.decode(toDecode));
		}

		//Printing out the visualization
		System.out.println(myHuffEncoding);
	}
}
