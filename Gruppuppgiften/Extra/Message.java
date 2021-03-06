package Extra;

import java.awt.Component;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Message implements Serializable{
	private User user;
	private ArrayList<User> receivers = new ArrayList<User>();
	private String text;
	private Icon image;
	private String serverReceivedDate;
	private String messageDeliveredDate;
	
	public Message(User user, ArrayList<User> receivers, Icon image) {
		this.user = user;
		this.receivers = receivers;
		this.image = image;
		this.text = null;
	}
	
	public Message(User user, ArrayList<User> receivers, String text) {
		this.user = user;
		this.receivers = receivers;
		this.text = text;
		this.image = null;
	}
	
	public Message(User user, ArrayList<User> receivers, String text, Icon image) {
		this.user = user;
		this.receivers = receivers;
		this.text = text;
		this.image = image;
	}
	
	public String toString() {
		return "Receivers: " + receivers;
//		return "(User: " + user + "), \n(UserList: " + receivers + "), \n(Text: " + text + "), \n(Image: " + image + ")";
	}
	
	public void setServerReceivedDate() {
		Date date = new Date();
		DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		serverReceivedDate = sdf.format(date);
	}
	
	public String getServerReceivedDate() {
		return serverReceivedDate;
	}
	
	public void setMessageDeliveredDate() {
		Date date = new Date();
		DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		messageDeliveredDate = sdf.format(date);
	}
	
	public String getMessageDeliveredDate() {
		return messageDeliveredDate;
	}
	
	public void setImage(Icon image) {
		this.image = image;
	}
	
	public Icon getImage() {
		return this.image;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setReceivers(ArrayList<User> receivers){
		this.receivers = receivers;
	}
	
	public ArrayList<User> getReceivers() {
		return receivers;
	}
}
