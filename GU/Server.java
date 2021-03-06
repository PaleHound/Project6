package GU;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.Icon;

public class Server {
	public Server(int port) {
		new Connection(port).start();
	}

	private class Connection extends Thread {
		private int port;

		public Connection(int port) {
			this.port = port;
		}

		public void run() {
			Socket socket = null;
			System.out.println("Server startad");
			try (ServerSocket serverSocket = new ServerSocket(port)) {
				while (true) {
					try {
						socket = serverSocket.accept();
						new ClientHandler(socket);
					} catch (IOException e) {
						System.err.println(e);
						if (socket != null)
							socket.close();
					}
				}
			} catch (IOException e) {
				System.err.println(e);
			}
			System.out.println("Server stoppad");
		}
	}

	private class ClientHandler extends Thread {
		private Socket socket;
		private ObjectInputStream ois;
		private ObjectOutputStream oos;

		public ClientHandler(Socket socket) throws IOException {
			this.socket = socket;
			ois = new ObjectInputStream(socket.getInputStream());
			oos = new ObjectOutputStream(socket.getOutputStream());
			start();
		}

		public void run() {
			Message msg;
			try {
				while (true) {
					try {
						msg = (Message) ois.readObject();
						oos.writeObject(msg);
						oos.flush();
						msgList.add(msg);
						WriteMessage("files/save.dat", msg);
					} catch (ClassNotFoundException e) {
					}
				}
			} catch (IOException e) {
				try {
					socket.close();
				} catch (Exception e2) {
				}
			}
			System.out.println("Klient nerkopplad");
		}
	}

	private ArrayList<Message> msgList = new ArrayList<Message>();

	public void WriteMessage(String filename, Message msg) throws IOException {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
			oos.writeInt(msgList.size());
			for (Message m : msgList) {
				oos.writeObject(m);
			}
			oos.flush();
		}
	}

	/**
	 * 
	 * @param user
	 * @throws IOException
	 */
	private void FindUnrecievedMessages(User user) throws IOException {
		File file = new File("files/unsent.dat");

		/**
		 * Format for saving messages is: Sender: "Sender1" Reciever: "Reciever1"
		 * "Reciever2" "Reciever3" Message: Bla bla bla bla bla bla
		 */
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String res = reader.readLine();
		while (res != null) {
			res = reader.readLine();
			if (res.contains(user.getUsername())) {
				while (res != "Message:") {
					res = reader.readLine();
				}
				// Send message to user;

			}
		}
	}

	public void ReadMessage(String filename) throws IOException {
		try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)))) {
			Message msg;
			int n = ois.readInt();
			try {
				for (int i = 0; i < n; i++) {
					msg = (Message) ois.readObject();
					System.out.println(msg);
				}
			} catch (ClassNotFoundException e) {
			}
		}
	}

	public static void main(String[] args) {
		new Server(1213);
	}
}
