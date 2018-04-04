import java.util.ArrayList;

/**
 * HuffCodes class
 *
 * @author Hieu Duong
 * @date 4/1/18
 */

public class HuffCodes implements HuffEncodable {
    public ArrayList<EncodeData> codeTable;
    public HeapNode<String> rootTree;

    public HuffCodes() {
        codeTable = new ArrayList<>();
        rootTree = null;
    }
    public void constructEncoding(String forCounts){
        //Create tree
        ArrayHeap<HeapNode> minQ = new ArrayHeap(1,true);

        if(!forCounts.isEmpty()) {
            for (char c : forCounts.toCharArray()) {
                String charInString = String.valueOf(c);

                if (minQ.findValue(charInString) == -1) {
                    int charCount = getCharCount(charInString, forCounts);
                    HeapNode<String> node = new HeapNode<>(charInString, charCount);
                    minQ.enqueue(node, node.getP());
                }
            }

            //System.out.println("MinQ: " + minQ);
            while (minQ.getSize() > 1) {
                //Remove left node from Min Queue
                HeapNode<String> leftN = minQ.dequeue();
                //Remove right node from Min Queue
                HeapNode<String> rightN = minQ.dequeue();
                //Add both of them together and create a parent Node
                //With left node and right node as its child
                HeapNode<String> parentN = new HeapNode<>(leftN, rightN);
                //Add parent node back to Min Queue
                minQ.enqueue(parentN, parentN.getP());
            }
            //Finally the remaining in the Min Queue is the root
            //Tree is completed
            rootTree = minQ.dequeue();

            //Get the binary code table base on the tree
            //Trace the tree until find the leaf and get the path to that leaf
            getCodeTable(rootTree, "", codeTable);

            //displayCodeTable(codeTable);
        }
    }

    /**
     * Method to display the coded Table
     * @param codeTable
     */
    private void displayCodeTable(ArrayList<EncodeData> codeTable) {
        for (EncodeData data : codeTable) {
            System.out.println(data.getLetter()+": "+data.getCode());
        }
    }

    //TODO: Using the generated encoding/tree convert the ASCII text to a binary sequence

    /**
     * Method to encode the String
     * @param text
     * @return
     */
    public String encode(String text){
        for (EncodeData data : codeTable) {
            text = text.replace(data.getLetter(), data.getCode());
        }
        return text;
    }

    //TODO: Using the generated encoding/tree convert the binary sequence to ASCII

    /**
     * Method to decode the binary string
     * @param binary
     * @return
     */
    public String decode(String binary){
        String decodedString = "";
        HeapNode<String> node = rootTree;

        HeapNode<String> nextNode = null;
        for (int i = 0; i < binary.length(); i++) {
            if (binary.charAt(i) == '0') {
                nextNode = node.getNodeLeft();

            } else if (binary.charAt(i) == '1') {
                nextNode = node.getNodeRight();
            }
            if (nextNode.isLeaf()) {
                decodedString += nextNode.getD();
                node = rootTree;
            } else {
                node = nextNode;
            }
        }

        return decodedString;
    }

    /**
     * Method to count the repeat char in String
     * @param charInString
     * @param arrayValue
     * @return
     */
    private int getCharCount(String charInString, String arrayValue) {
        int charCount = 0;
        for(char c: arrayValue.toCharArray()){
            if(String.valueOf(c).equals(charInString)){
                charCount++;
            }
        }
        return charCount;
    }

    /**
     * Recursion method to get the path (binary code) to the leaf nodes
     * @param node
     * @param codedValue
     * @param codeTB
     */
    private void getCodeTable(HeapNode node, String codedValue, ArrayList<EncodeData> codeTB) {
        if (node.isLeaf()) {
            //System.out.println("Data: "+x.getD() +", path: "+path);
            EncodeData data = new EncodeData((String.valueOf(node.getD())), codedValue);
            codeTB.add(data);
            return;
        }

        getCodeTable(node.getNodeLeft(), codedValue+"0", codeTB);
        getCodeTable(node.getNodeRight(), codedValue+"1", codeTB);
    }
}
