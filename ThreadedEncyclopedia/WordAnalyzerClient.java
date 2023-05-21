import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;


public class WordAnalyzerClient {

	public static void main(String[] args) {
		String data = "";
    	String Fdata = data;
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
        try {
        	Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            registry.lookup("WordAnalyzer");

            long start = System.currentTimeMillis();

            Thred T1 = new Thred();
            T1.start();
            int count = T1.Count(Fdata);
            System.out.println("Number of letters: "+count );
            
            Thred T2 = new Thred();
            T2.start();
            ArrayList<String> repeated = T2.repeatedwords(Fdata);
            System.out.println("Repeated words: "+repeated );
            
            Thred T3 = new Thred();
            T3.start();
            String longest = T3.longest(Fdata);
            System.out.println("Longest word: "+longest );

            Thred T4 = new Thred();
            T4.start();
            String shortest = T4.shortest(Fdata);
            System.out.println("Shortest word: "+shortest );
            
            Thred T5 = new Thred();
            T5.start();
            Map<String, Integer> repeats = T5.Repeat(Fdata);
            System.out.println("Repeatition number: "+repeats );
            
            long end = System.currentTimeMillis();
            
            System.out.println("Time taken: " + (end - start) + "ms");
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}

