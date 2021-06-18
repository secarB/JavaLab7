package braces.client.main;


import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import braces.client.utils.ClientAsker;
import braces.client.utils.ClientConsole;
import braces.client.utils.ClientInputChecker;
import braces.client.utils.ClientRun;

public class ClientMain {
	public static void main(String[] args) throws ClassNotFoundException, IOException, InterruptedException {
		ClientRun.run(); 
		ClientInputChecker clientInputChecker = new ClientInputChecker();
		ClientAsker clientAsker = new ClientAsker(clientInputChecker);
		ClientAsker clientAsker2 = new ClientAsker(clientInputChecker);
		ClientConsole clientConsole = new ClientConsole(clientAsker,clientAsker2);
		clientConsole.userMode();
	   
	}

}

