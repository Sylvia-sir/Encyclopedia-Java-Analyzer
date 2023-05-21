import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Map;

public interface WordAnalyzer extends Remote {
    int Count(String Fdata) throws RemoteException;
    ArrayList<String> repeatedwords(String Fdata) throws RemoteException;
    String longest(String Fdata) throws RemoteException;
    String shortest(String Fdata) throws RemoteException;
    Map<String, Integer> Repeat(String Fdata) throws RemoteException;
}