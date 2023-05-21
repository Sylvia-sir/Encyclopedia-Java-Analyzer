import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class WordAnalyzerGUI extends JFrame { //implements WordAnalyzer {

    private JPanel contentPane;
    JTextArea textArea;
    JTextArea outputArea = new JTextArea();

    /**
     * Create the frame.
     */
    public WordAnalyzerGUI(WordAnalyzer wordAnalyzer) {
        setTitle("Word Analyzer threaded");
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(400, 400, 600, 340);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JButton countButton = new JButton("Count");
        countButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String paragraph = textArea.getText();
                int count = 0;
                Thred T1 = new Thred();
                T1.start();
				try {
					count = T1.Count(paragraph);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                outputArea.setText("Number of letters: " + count);
            }
        });
        countButton.setBounds(10, 260, 89, 23);
        contentPane.add(countButton);

        JButton repeatedWordsButton = new JButton("Repeated Words");
        repeatedWordsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Thred T2 = new Thred();
                T2.start();
                String paragraph = textArea.getText();
                ArrayList<String> repeatedWords = null;
				try {
					repeatedWords = T2.repeatedwords(paragraph);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                outputArea.setText("Repeated Words: " + repeatedWords);
            }
        });
        repeatedWordsButton.setBounds(110, 260, 140, 23);
        contentPane.add(repeatedWordsButton);
        
        JButton longestButton = new JButton("Longest");
        longestButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Thred T3 = new Thred();
                T3.start();
                String paragraph = textArea.getText();
                String longest = null;
				try {
					longest = T3.longest(paragraph);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
                outputArea.setText("Longest word: " + longest);
                
            }
        });
        longestButton.setBounds(260, 260, 89, 23);
        contentPane.add(longestButton);

        JButton shortestButton = new JButton("Shortest");
        shortestButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Thred T4 = new Thred();
                T4.start();
                String paragraph = textArea.getText();
                String shortest = null;
				try {
					shortest = T4.shortest(paragraph);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                outputArea.setText("Shortest word: " + shortest);
            }
        });
        shortestButton.setBounds(360, 260, 89, 23);
        contentPane.add(shortestButton);
                
        JButton repeated = new JButton("Repeated");
        repeated.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                Thred T5 = new Thred();
                T5.start();
        		String paragraph = textArea.getText();
        		Map<String, Integer> repeated = null;
        		try {
        			repeated = T5.Repeat(paragraph);
        		}catch(RemoteException er) {
        			er.printStackTrace();
        		}
        		outputArea.setText("Word repetition count: "+repeated);
        	}
        });
        repeated.setBounds(460, 260, 89, 23);
        contentPane.add(repeated);
      
        JLabel input = new JLabel("Input:", JLabel.LEFT);
        input.setBounds(10, 1, 90, 25);
        contentPane.add(input);

        textArea = new JTextArea();
        textArea.setBounds(10, 25, 560, 97);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        contentPane.add(textArea);

        JLabel output = new JLabel("Output:", JLabel.LEFT);
        output.setBounds(10, 120, 90, 25);
        contentPane.add(output);
        
        outputArea = new JTextArea();
        outputArea.setBounds(10, 152, 560, 97);
		outputArea.setLineWrap(true);
		outputArea.setWrapStyleWord(true);
        contentPane.add(outputArea);
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry();
            WordAnalyzer wordAnalyzer = (WordAnalyzer) registry.lookup("WordAnalyzer");
            WordAnalyzerGUI frame = new WordAnalyzerGUI(wordAnalyzer);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}