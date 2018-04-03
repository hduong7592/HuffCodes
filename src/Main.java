import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        ArrayHeap<HeapNode> minQ = new ArrayHeap(1,true);
        String originalString = "This is a sample test string";
        //String originalString = "A_DEAD_DAD_CEDED_A_BAD_BABE_A_BEADED_ABACA_BED";
        String arrayValue = originalString;
        //String arrayValue = "This is a sample string";

        for(char c: arrayValue.toCharArray()){
            String charInString = String.valueOf(c);

            if(minQ.findValue(charInString) == -1){
                int charCount = getCharCount(charInString, arrayValue);
                HeapNode<String> node = new HeapNode<>(charInString, charCount);
                minQ.enqueue(node, node.getP());
            }
        }

        System.out.println("MinQ: "+minQ);
        while (minQ.getSize() > 1){
            HeapNode<String> leftN = minQ.dequeue();
            HeapNode<String> rightN = minQ.dequeue();
            HeapNode<String> parentN = new HeapNode<>(leftN, rightN);
            minQ.enqueue(parentN, parentN.getP());
        }

        HeapNode<String> rootTree = minQ.dequeue();

        ArrayList<HeapCode> codeTable = new ArrayList<>();
        getCodeTable(rootTree, "", codeTable);

        for(HeapCode code: codeTable){
            System.out.println(code.getLetter()+": "+code.getCode());
            arrayValue = arrayValue.replace(code.getLetter(), code.getCode());
        }
        System.out.println("Encoded: "+arrayValue);

        String decodeString = "";

        System.out.println("Encoded: "+ originalString);

        HeapNode<String> node = rootTree;
        HeapNode<String> nextNode = null;
        for(int i=0; i<arrayValue.length();i++){
            if(arrayValue.charAt(i)=='0'){
               nextNode = node.getNodeLeft();

            }else if(arrayValue.charAt(i)=='1'){
               nextNode = node.getNodeRight();
            }
            if(nextNode.isLeaf()){
                //System.out.print(nextNode.getD());
                decodeString+=nextNode.getD();
                node = rootTree;
            }else{
                node = nextNode;
            }
        }

        System.out.println("Decoded: "+decodeString);
    }


    private static int getCharCount(String charInString, String arrayValue) {
        int charCount = 0;
        for(char c: arrayValue.toCharArray()){
            if(String.valueOf(c).equals(charInString)){
                charCount++;
            }
        }
        return charCount;
    }

    private static void getCodeTable(HeapNode x, String path, ArrayList<HeapCode> codeTB) {
        if (x.isLeaf()) {
            //System.out.println("Data: "+x.getD() +", path: "+path);
            HeapCode code = new HeapCode((String.valueOf(x.getD())), path);
            codeTB.add(code);
            return;
        }

        getCodeTable(x.getNodeLeft(), path+"0", codeTB);
        getCodeTable(x.getNodeRight(), path+"1", codeTB);
    }
}
