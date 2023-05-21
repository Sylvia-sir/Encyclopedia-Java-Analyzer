import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class WordAnalyzerClient {

	public static void main(String[] args) {
		String data="";
		String Fdata = data;
        try {
        	try {
      	      File myObj = new File("E:\\10.College\\Encyclopedia\\300.txt");
      	      Scanner myReader = new Scanner(myObj);
      	      while (myReader.hasNextLine()) {
      	      data = myReader.nextLine();
      	      Fdata = Fdata+data;
      	      }
      	      myReader.close();
      	    } catch (FileNotFoundException e) {
      	      System.out.println("An error occurred.");
      	      e.printStackTrace();
      	    }
        	Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            WordAnalyzer stub = (WordAnalyzer) registry.lookup("WordAnalyzer");

            long start = System.currentTimeMillis();
            int count = stub.Count(Fdata);
            ArrayList<String> repeated = stub.repeatedwords(Fdata);
            String longest = stub.longest(Fdata);
            String shortest = stub.shortest(Fdata);
            Map<String, Integer> repeat = stub.Repeat(Fdata);
            long end = System.currentTimeMillis();

            System.out.println("Number of letters: " + count);
            System.out.println("Repeated words: " + repeated);
            System.out.println("Longest word: " + longest);
            System.out.println("Shortest word: " + shortest);
            System.out.println("Word repetition count: " + repeat);
            System.out.println("Time taken: " + (end - start) + "ms");
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
