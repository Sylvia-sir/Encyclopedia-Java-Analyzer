import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Thred extends Thread implements WordAnalyzer {
	
	@Override
	public void run() {}
	
	public int Count(String Fdata) throws RemoteException {
        int count = Fdata.length();    
        return count;
        }
	
    public Map<String, Integer> Repeat(String Fdata) throws RemoteException {
        Map<String, Integer> map = new HashMap<>();
        String[] All= Fdata.split("\\s+");
        for (String word : All) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
		return map; 
    }
	
    public ArrayList<String> repeatedwords(String words) throws RemoteException {
        //String[] All= words.split("\\s+");
        Map<String, Integer> wordMap = Repeat(words);
        ArrayList<String> list = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
            if (entry.getValue() > 1) {
                list.add(entry.getKey());
            }
        }
        return list;
    }
    
    public String longest(String Fdata) throws RemoteException {
        String longest = "";
        String[] All= Fdata.split("\\s+");
        for (String word : All) {
            if (word.length() > longest.length()) {
                longest = word;
            }
        }
        return longest;
}
    
    public String shortest(String Fdata) throws RemoteException {
        String[] All= Fdata.split("\\s+");
        String shortest = All[0];
        for (String word : All) {
            if (word.length() < shortest.length()) {
                shortest = word;
            }
        }
        return shortest;   
        }
    

}

