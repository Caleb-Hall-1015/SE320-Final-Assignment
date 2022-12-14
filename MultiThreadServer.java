//edited from Canvas class
import java.io.*;
import java.net.*;
import java.util.*;

public class MultiThreadServer
{	
	public static void main(String[] args) 
	{
		new MultiThreadServer();
	}
		
	public MultiThreadServer() 
	{	
		try {
		  ServerSocket serverSocket = new ServerSocket(8000);
		  System.out.println("MultiThreadServer started at " + new Date() + '\n');
		
		  int clientNo = 1;
		
		  while (true) {
		    Socket socket = serverSocket.accept();
		    System.out.println("Starting thread for client " + clientNo + " at " + new Date());
	
		    InetAddress inetAddress = socket.getInetAddress();
		    System.out.println("Client " + clientNo + "'s host name is " + inetAddress.getHostName());
		    System.out.println("Client " + clientNo + "'s IP Address is " + inetAddress.getHostAddress());
		    HandleAClient thread = new HandleAClient(socket);
	
		    thread.start();
	
		    clientNo++;
		    }
		}
		catch(IOException ex) {
		    	System.err.println(ex);
		}
	}
	
	// Define the thread class for handling new connection
	class HandleAClient extends Thread {
		private Socket socket;
		
	    public HandleAClient(Socket socket) {
	    	this.socket = socket;
	    }
		
	    public void run() {
		    try {
			    DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
			    DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());
		
			    while (true) 
			    {
			    	double weightInKilograms = inputFromClient.readDouble();
					double heightInMeters = inputFromClient.readDouble();
					
					double bmi = weightInKilograms / (heightInMeters * heightInMeters);
					
					outputToClient.writeDouble(bmi);
					
					System.out.println("Weight received from client: " + weightInKilograms);
					System.out.println("Height received from client: " + heightInMeters);
					System.out.println("BMI found: " + bmi);
			    }
		    } catch(IOException e) {
		    	System.err.println(e);
			}
		}
	}
}