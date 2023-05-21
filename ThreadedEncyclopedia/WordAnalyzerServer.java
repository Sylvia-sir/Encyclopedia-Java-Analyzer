import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject; 

@SuppressWarnings("serial")
public class WordAnalyzerServer extends UnicastRemoteObject implements WordAnalyzer {

    public WordAnalyzerServer() throws RemoteException {
        super();
        }

    public static void main(String[] args) {
    	try {
            WordAnalyzerServer obj = new WordAnalyzerServer();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("WordAnalyzer", obj);
            System.out.println("Server is running.");
        } catch (RemoteException e) {
        	System.err.println("Server exception: " + e.toString()); 
            e.printStackTrace();
        }        
    }
}



