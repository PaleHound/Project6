package p6;

import java.util.concurrent.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

// (Evt: Bibliotek over alle mulige arrays: http://ddwap.mah.se/af8827/)
import p6.Array7x7;
import p6.TestUI1; // Ja?

public class M2Controller {

	private JPanel ui;
	private Array7x7 array;
	private Array7x7[] charArray = new Array7x7[5];
	private Array7 leftColumn;
	private Array7 rightColumn;
	static String word;
	static Array7x7 arr7x7;
	
	public M2Controller(Array7x7 array) {
		this.array = array;
	}
		
	public M2Controller(JPanel ui, Array7x7 array) {
		this.ui = ui;
		this.array = array;
	}
	
	public M2Controller(Array7x7[] arr) {
		this.charArray = arr;
	}

	public M2Controller(Array7x7 array, Array7 leftColumn, Array7 rightColumn) {
		// TODO Auto-generated constructor stub
		this.leftColumn = leftColumn;
		this.rightColumn = rightColumn;
		this.array = array;
	}
	
	public void setWord(String word) {
		for (int z = 0; z < word.length(); z++) {
			charArray[z].setArray(Chars.getChar(word.charAt(z)));
			for (int y = 0; y < 7; y++) {
				for (int x = 0; x < 7; x++) {
					if (charArray[z].getElement(y, x) == 1) {
						charArray[z].setElement(y, x, Color.WHITE);
					} else {
						charArray[z].setElement(y, x, Color.TRANSPARENT);
					}
				}
			}
		}
	}	
	
	public static void convertToColor() {
		for (int y = 0; y < 7; y++) {
			for (int x = 0; x < 7; x++) {
				if (arr7x7.getElement(y, x) == 1) {
					arr7x7.setElement(y, x, Color.WHITE);
				} else {
					arr7x7.setElement(y, x, Color.TRANSPARENT);
				}
			}
		}
	}



	// public void readRow(int rowNbr) {
	// array.getRow(rowNbr); // denna behöves vist egentligen inte, vi anropar jo
	// eksakt samma på raden under
	// ui.setBotRow(array.getRow(rowNbr));
	// }
	//
	// public void writeRow(int rowNbr, Array7 arr) {
	// array.setRow(rowNbr, arr);
	// // Nu har vi så skrivit över en rad i array7x7 med en ny rad.

	// }

	public Array7 getLeftColumn() {
		return leftColumn;
	}

	public void setLeftColumn(Array7 leftColumn) {
		this.leftColumn = leftColumn;
	}

	public Array7 getRightColumn() {
		return rightColumn;
	}

	public void setRightColumn(Array7 rightColumn) {
		this.rightColumn = rightColumn;
	}

	public void writeCol(int colNbr, Array7 arr) {
		array.setCol(colNbr, arr);

	}

	public void setUI(JPanel ui) {
		this.ui = ui;
	}

	public JPanel getUI() {
		return ui;
	}

	public void setArray7x7(Array7x7 array) {
		this.array = array;
	}

	public Array7x7 getArray7x7() {
		return array;
	}
	
	public Array7x7 getCharArray(int i) {
		return charArray[i];
	}

	// public void updateLabels() {
	//
	// for (int i = 0; i < 7; i++) {
	// for (int j = 0; j < 7; j++) {
	// ui.setArrLabels(i, j, array.getElement(i, j));
	// }
	// }

	public Array7x7 getArray() {
		return array;
	}

	public static void main(String[] args) {
		int[] leftC = new int[] { 3, 2, 1, 4, 6, 4, 8 };
		int[] rightC = new int[] { 3, 1, 5, 3, 5, 1, 5 };
		Array7x7[] charArr = new Array7x7[5];
		
		for (int i = 0; i < 5; i++) {
			charArr[i] = new Array7x7();
		}
		
		arr7x7 = new Array7x7();
		int answer = Integer.parseInt(JOptionPane.showInputDialog("1. Test1UI\n" + "2. Test2UI\n" + "3. Test3UI\n" + "4. Test4UI\n" + "0. avsluta."));
		JFrame frame = new JFrame("Test2UI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		switch (answer) {
		case 1:
			M2Controller con1 = new M2Controller(arr7x7, new Array7(leftC), new Array7(rightC));
			TestUI1 ui1 = new TestUI1(con1);
			con1.setUI(ui1);
			frame.add(ui1);
			break;
			
		 case 2:
			 M2Controller con2 = new M2Controller(arr7x7, new Array7(leftC), new
			 Array7(rightC));
			 TestUI2 ui2 = new TestUI2(con2);
			 con2.setUI(ui2);
			 frame.add(ui2);
			 break;
			 
		 case 3:
			 arr7x7.setArray(Chars.getChar('A'));
			 convertToColor();
			 M2Controller con3 = new M2Controller(arr7x7);
			 final TestUI3 ui3 = new TestUI3(con3);
			 con3.setUI(ui3);
			 frame.add(ui3);
			 
			 final ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
			 ses.scheduleAtFixedRate(new Runnable(){
					public void run() {
						ui3.changeleft();
					}
			 }, 0, 500, TimeUnit.MILLISECONDS);
		     break;
		     
		case 4:
			word = JOptionPane.showInputDialog("Skriv ett ord på 5 bokstäver");
			
//			while (word.length() != 5) {
//				word = JOptionPane.showInputDialog("Skriv ett ord på 5 bokstäver");
//			}
			
			M2Controller con4 = new M2Controller(charArr);
			con4.setWord(word.toUpperCase());
			final TestUI4 ui4 = new TestUI4(con4);
			con4.setUI(ui4);
			frame.add(ui4);
			
			final ScheduledExecutorService se = Executors.newSingleThreadScheduledExecutor();
			se.scheduleAtFixedRate(new Runnable(){
				final String word2 = word;
				public void run() {
					ui4.moveLeft(word2);
				}
		
		 }, 0, 500, TimeUnit.MILLISECONDS);
		 break;
		 
		 case 0:
			 System.exit(0);
		}

		frame.pack();
		frame.setVisible(true);
	}

}

