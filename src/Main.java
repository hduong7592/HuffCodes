import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        ArrayHeap<HeapNode> minQ = new ArrayHeap(1,true);
        String arrayValue = "A_DEAD_DAD_CEDED_A_BAD_BABE_A_BEADED_ABACA_BED";
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

    private static int computeFactorial(int i) {
        if(i==0) return 1;
        else{
            return i*computeFactorial(i-1);
        }
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
