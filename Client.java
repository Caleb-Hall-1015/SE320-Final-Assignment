//edited from Canvas class
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client
{
	private DataOutputStream outputToServer;
	private DataInputStream inputFromServer;
	
	public static void main(String[] args) {
		new Client();
	}
	
	public Client() 
	{
		try {
			Socket socket = new Socket("localhost", 8000);
		
			inputFromServer = new DataInputStream(socket.getInputStream());
			
			outputToServer = new DataOutputStream(socket.getOutputStream());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		try 
		{
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter weight in kilograms: ");
			double weightInKilograms = scan.nextDouble();
			System.out.println("Enter height in meters: ");
			double heightInMeters = scan.nextDouble();
			
			outputToServer.writeDouble(weightInKilograms);
			outputToServer.writeDouble(heightInMeters);
			outputToServer.flush();
			
			double bmi = inputFromServer.readDouble();
			
			System.out.println("Weight is " + weightInKilograms);
			System.out.println("Height is " + heightInMeters);
			System.out.println("BMI received from the server is "+ bmi);
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}