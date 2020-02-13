import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FrequencyDriver {
    public static void main(String[] args) throws FileNotFoundException
    {
        FrequencyHashTable EAPHashTable = new FrequencyHashTable();//hashtable for author EAP
        FrequencyHashTable MWSHashTable = new FrequencyHashTable();//hashtable for author MWS
        FrequencyHashTable HPLHashTable = new FrequencyHashTable();//hashtable for author HPL

        File f = new File("train.csv");//setup scanner for train.csv
        Scanner scan = new Scanner(f);

        //iterate through the file
        while (scan.hasNext()) {
            String line = scan.nextLine();
            Record r = new Record(line);
            if (r.getAuthor().equals("EAP"))
            {
                //it's written by EAP, increment each word
                for (String word : r.getText().split(" ")) {
                    EAPHashTable.increment(word);
                }
            } else if (r.getAuthor().equals("HPL"))
            {
                //it's written by HPL, increment each word
                for (String word : r.getText().split(" ")) {
                    HPLHashTable.increment(word);
                }
            } else for (String word : r.getText().split(" ")) { //it's written by MWS, increment each word
                    MWSHashTable.increment(word);
                }
        }

        File file = new File("test.csv");//setup scanner for test.csv
        Scanner scanner = new Scanner(file);
        int counter = 0;
        int totalCounter = 0;

        //iterate through the file
        while (scanner.hasNext())
        {
            String liner = scanner.nextLine();
            Record record = new Record(liner);
            String getAuthor = record.getAuthor();
            String correctGuess;
            double eap=1, mws=1, hpl=1;
            String [] sentArray = record.getText().split(" ");
            totalCounter++;
            for (int i =0; i < sentArray.length ; i++)
            {
                eap = eap * EAPHashTable.get(sentArray[i]);
                hpl = hpl * HPLHashTable.get(sentArray[i]);
                mws = mws * MWSHashTable.get(sentArray[i]);
            }

            if (eap >= mws && eap >= hpl)
                correctGuess = "EAP";
            else if (hpl >= eap && hpl >= mws)
                correctGuess = "HPL";
            else correctGuess = "MWS";

            System.out.println(correctGuess);

            if (correctGuess.equals(getAuthor))
            {
                counter++;
            }
        }
        System.out.println("Correct Matches Percentage: " + (100 * counter/totalCounter)  + "%");

    }

}


