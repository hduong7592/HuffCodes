/************************************************************************************************
 *
 * AT SOME POINT MUST USE ARRAYHEAP IN IMPLEMENTATION
 * 
 ************************************************************************************************/
//TODO: ONLY Code that is modified should be done in the class that implements this...can create additional class 
//	that this one uses, as well as additional methods.

public interface HuffEncodable{
	//TODO: How to generate the huff encoding...
	public void constructEncoding(String forCounts);

	//TODO: Using the generated encoding/tree convert the ASCII text to a binary sequence
	public String encode(String text);

	//TODO: Using the generated encoding/tree convert the binary sequence to ASCII
	public String decode(String binary);

	//TODO: Must override toString, Generate a nice string to show all characters and their encoding (A:101001,B:1101,...) or so
}
