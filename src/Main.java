import java.util.ArrayList;
import java.util.Collections;

/**
 * Main class
 *
 * @author Hieu Duong
 * @date 4/2/18
 */

public class Main {
    public static void main(String[] args){
        String value = "A_DEAD_DAD_CEDED_A_BAD_BABE_A_BEADED_ABACA_BED";

        char[] msgChars = value.toCharArray();
        ArrayList<Character> chars = new ArrayList<>();
        for(char c:value.toCharArray()){
            if(!(chars.contains(c))){
                chars.add(c);
            }
        }

        int[] countChars = new int[chars.size()];
        for(int i=0; i<chars.size(); i++){
            //System.out.println("Char: "+chars.get(i));
            int count=0;
            for(char c: value.toCharArray()){
                if(chars.get(i).equals(c)){
                    count++;
                }
            }
            countChars[i] = count;
        }


        for(int i=0; i<countChars.length-1; i++){
            for(int j=0; j<countChars.length-1; j++){
                if(countChars[j]<countChars[j+1]){
                    int temp = countChars[j];
                    countChars[j] = countChars[j+1];
                    countChars[j+1] = temp;

                    char tempChar = chars.get(j);
                    chars.set(j, chars.get(j+1));
                    chars.set(j+1, tempChar);
                }
            }
        }


        for(char c: chars){
            System.out.print(c+" ");
        }
        System.out.println();
        for(int i: countChars){
            System.out.print(i+" ");
        }
        System.out.println("");

        char c1 = chars.remove(chars.size()-1);
        char c2 = chars.remove(chars.size()-1);

        int val1 = countChars[countChars.length-1];
        int val2 = countChars[countChars.length-2];

        System.out.println("Left: "+c1+", "+val1);
        System.out.println("Right: "+c2+", "+val2);


        ArrayHeap<String> ar = new ArrayHeap(1,false);

        ar.enqueue("C", 2);
        ar.enqueue("B", 6);
        ar.enqueue("CB", 8);

        ArrayHeap<String> ar2 = new ArrayHeap(1,false);
        ar2.enqueue("E", 7);
        ar2.enqueue("ECB", 15);


        System.out.println("AR: "+ar);
        System.out.println("AR2: "+ar2);
        ar2.addToExistTree(ar);
        System.out.println("New AR2: "+ar2);
        System.out.println("AR2 size: "+ar2.getSize());

        ArrayHeap<String> ar3 = new ArrayHeap(1,false);

        ar3.enqueue("D", 10);
        ar3.enqueue("_", 10);
        ar3.enqueue("_D", 20);

        System.out.println("AR3: "+ar3);

        ArrayHeap<String> ar4 = new ArrayHeap(1,false);
        ar4.enqueue("A", 11);
        ar4.enqueue("AECB", 26);

        System.out.println("AR4: "+ar4);
        ar4.addToExistTree(ar2);

        System.out.println("New Ar4: "+ar4);

        ArrayHeap<String> ar5 = new ArrayHeap(1,false);
        ar5.enqueue("_DAECB", 46);
        ar5.addToExistTree(ar3);

        //ar5.addToExistTree(ar4);
        System.out.println("AR5: "+ar5);

        ArrayHeap<String> ar6 = new ArrayHeap(1,false);
        ar6.enqueue("_DAECB", 46);
        ar6.addToExistTree(ar4);
        System.out.println("AR6: "+ar6);

    }

    private static int countValue(String charToCount, String valueString) {
        int count =0;
        for(char c: valueString.toCharArray()){
            if(charToCount.equals(String.valueOf(c))){
                count++;
            }
        }
        return count;
    }
}
