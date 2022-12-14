//edited from Canvas class
import java.io.*;
import java.net.*;
import java.util.*;

public class Server 
{
	public static void main(String[] args) {
		new Server();
	}
	
	public Server() {
		try {
		  ServerSocket serverSocket = new ServerSocket(8000);
		  System.out.println("Server started at " + new Date());
		
		  Socket socket = serverSocket.accept();
		
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
		}
		catch(IOException ex) {
		  System.err.println(ex);
		}
	}
} 