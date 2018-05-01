package GU;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class ClientController {
	private User user;
	private Client client;
	private ClientUI ui;
	private void showClientUI() {
		JFrame frame = new JFrame("Client");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(ui);
		frame.pack();
		frame.setVisible(true);
	}
	public ClientController(Client client) {
		this.client = client;
		this.user = client.getUser();
		ui = new ClientUI(this, user);
		client.setController(this);
		showClientUI();
	}
	public void newSend(Message msg) {
		try {
			client.newSend(msg);
		} catch (IOException e) {
			System.err.println("error i newSend Controller");
		}
	}
	public void newMessageReceived(Message msg) {
		ui.setMessageReceived(msg);
	}

	public void setOnlineList(ArrayList<String> list) {
		ui.setOnlineList(list);
	}

	public User getUser() {
		return this.user;
	}

	//	public static void main(String[] args) {
	//		try {
	//			Client client = new Client("127.0.0.1",1210);
	//			new ClientController(client);
	//		} catch (IOException e) {}
	//	}
}