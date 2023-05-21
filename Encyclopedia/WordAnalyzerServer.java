import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

@SuppressWarnings("serial")
public class WordAnalyzerServer extends UnicastRemoteObject implements WordAnalyzer {

    public WordAnalyzerServer(String words) throws RemoteException {
        super();
    }

    public int Count(String words) throws RemoteException {
    	return words.length();
    }
    
    public Map<String, Integer> Repeat(String words) throws RemoteException {
        Map<String, Integer> map = new HashMap<>();
        String[] All= words.split("\\s+");
        for (String word : All) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        return map;
    }

    @SuppressWarnings("null")
    public ArrayList<String> repeatedwords(String words) throws RemoteException {
        Map<String, Integer> wordMap = Repeat(words);
        ArrayList<String> list = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
            if (entry.getValue() > 1) {
                list.add(entry.getKey());
            }
        }
        return list;
    }

    public String longest(String words) throws RemoteException {
        String longest = "";
        String[] All= words.split("\\s+");
        for (String word : All) {
            if (word.length() > longest.length()) {
                longest = word;
            }
        }
        return longest;
    }

    public String shortest(String words) throws RemoteException {
        String[] All= words.split("\\s+");
        String shortest = All[0];
        for (String word : All) {
            if (word.length() < shortest.length()) {
                shortest = word;
            }
        }
        return shortest;
    }



    public static void main(String[] args) {
    	String data = "";
    	String Fdata = data;
        try {
        	
            WordAnalyzerServer obj = new WordAnalyzerServer(Fdata);
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("WordAnalyzer", obj);
            System.out.println("Server is running.");
        } catch (RemoteException e) {
        	System.err.println("Server exception: " + e.toString()); 
            e.printStackTrace();
        }
    }
}


