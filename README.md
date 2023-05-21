# Encyclopedia-Java-Analyzer
We worked on this project with 2 concepts, Java RMI and Multithreading, with a basic GUI.

The main idea behind this project is importing/inserting an encyclopedia and then analyze it, once using the threaded version and another time using the linear version.

Encyclopedia file (unthreaded version) 
Includes the interface to be implemented, server class that implements the methods, client to receive input and analyze it, and another client as a GUI version. 

ThreadedEncyclopedia file (threaded version)
Includes all the components of the unthreaded version + a thread class so we can create as many classes as we need

RMI (Remote Method Invocation) concept is that the client can invoke the server's methods, so that's how it works:

First, we declared a Java interface (WordAnalyzer) that includes all the methods we may need to implement.

Second, we implemented its server class (WordAnalyzerServer) that actually implements the methods, and connects to the host (localhost in our case), then prints an acknowledgment message "Server is running".

Third, we implemented its first client class (WordAnalyzerClient) that imports the input, connects to the host, and calls the methods. It also calculated the time taken for the execution. Then prints the output of all the methods + the calculated time.

Fourth, we implemented its second client class with the GUI (WordAnalyzerGUI) that takes the input from the user, connects to the host, and calls each method when its button is clicked. Then prints the output of that method.

In the case of the threaded version:

The thread class (Thred) is created so we can create multiple methods. Therefore in the client class of this version, we call each method from a different thread so they are executed parallelly. And the time taken using threads was about 1/5 of the time of the linear version.

Note: you need to make sure the host is available before you run a server on it. So before you run the server make sure to terminate any running processes on that host.






