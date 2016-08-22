package ProgAssignment3;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ProgAssignment3.Board.Pebble;

import javax.swing.JOptionPane;

public class Board extends JFrame {

	private Pebble[][] board;
	private int frameSize = 600;
	private int boardSize;

	private Marker[][] markers; // has JLabels for pebbles

	enum Pebble {
		X("BLACK"), O("WHITE"), EMPTY("EMPTY");
		private final String COLOR;
		private Pebble(String c){
			this.COLOR = c;
		}
		public String getColor(){
			return this.COLOR;
		}
		
	}

	public Board(int n) {
		super("Five in a Row");
		this.setSize(frameSize, frameSize);
		this.setLayout(null);

		board = new Pebble[n][n];
		markers = new Marker[n][n];
		boardSize = n;

		clearBoard();
		initBoard(boardSize);

		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private void initBoard(int size) {
		JPanel panel = new JPanel() {
			public void paint(Graphics g) {
				for (int i = 0; i <= 500; i += (500 / size)) {
					g.drawLine(0, i, 500, i);
					g.drawLine(i, 0, i, 500);
				}
			}
		};
		this.add(panel);
		panel.setLocation(50, 50);
		panel.setSize(500, 500);
	}

	public void drawPebble(int xPos, int yPos, Pebble color) {
		Marker m = new Marker(color);
		markers[xPos][yPos] = m;
		this.add(m);
		int boxSize = getBoxSize();
		m.setLocation(50 + getBoxSize() * xPos, frameSize - (50 +(getBoxSize()*(yPos+1))));
		m.setSize(boxSize, boxSize);
		m.setEnabled(true);
	}

	public int getBoxSize() {
		return 500 / board.length;
	}

	public int getBoardSize() {
		return this.boardSize;
	}

	public void clearBoard() {
		// set all values to EMPTY
		for (int i = 1; i <= boardSize; i++) {
			for (int j = 1; j <= boardSize; j++) {
				setCell(i, j, Pebble.EMPTY);
			}
		}
	}

	public boolean isValidInput(int xPos, int yPos) {
		return (xPos > 0 && xPos <= boardSize && yPos > 0 && yPos <= boardSize);
	}

	// Will set the cell to aPebble, even if it is filled with something else
	public void setCell(int xPos, int yPos, Pebble aPebble) {
		if (!isValidInput(xPos, yPos))
			return;

		board[xPos - 1][yPos - 1] = aPebble;
		if (markers[xPos - 1][yPos - 1] == null)
			drawPebble(xPos - 1, yPos - 1, aPebble);
		else
			markers[xPos - 1][yPos - 1].changeColor(aPebble);

	}

	public Pebble getCell(int xPos, int yPos) {
		if (!isValidInput(xPos, yPos))
			return null;
		return board[xPos - 1][yPos - 1];
	}

	public boolean isEmpty(int xPos, int yPos) {
		return board[xPos - 1][yPos - 1] == Pebble.EMPTY;
	}

	public boolean isBoardEmpty() {
		boolean empty = false;
		for (int i = 1; i <= boardSize; i++) {
			for (int j = 1; j <= boardSize; j++) {
				empty |= isEmpty(i, j);
			}
		}
		return empty;
	}

	public boolean isBoardFull() {
		for (int i = 1; i <= boardSize; i++) {
			for (int j = 1; j <= boardSize; j++) {
				if (isEmpty(i, j)) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean gameEnds() {
		return (isBoardFull() || fiveX() || fiveO());
	}

	public boolean fiveX() {
		return inARow(Pebble.X, 5);
	}

	public boolean fiveO() {
		return inARow(Pebble.O, 5);
	}

	private boolean inARow(Pebble aPebble, int range) {
		if (boardSize < range)
			return false;
		else {
			int countH = 0;
			int countV = 0;
			for (int i = 1; i <= boardSize; i++) {
				for (int j = 1; j <= boardSize; j++) {
					if (getCell(i, j) == aPebble) {
						countH++;
					} else {
						countH = 0;
					}
					if (getCell(j, i) == aPebble) {
						countV++;
					} else {
						countV = 0;
					}
					if (countH == range || countV == range) {
						return true;
					}
				}
			}

			return false;
		}
	}

	public int fourX() {
		return four(Pebble.X);
	}

	public int fourO() {
		return four(Pebble.O);
	}

	private int four(Pebble aPebble) {
		int numFours = 0;
		if (!isBoardFull() || boardSize < 4)
			return -1;
		else {

			numFours += fourForwards(aPebble);
			numFours += fourBackwards(aPebble);

		}
		return numFours;
	}

	/*
	 * re-write and make it better
	 * 
	 * 
	 */
	private int fourForwards(Pebble aPebble) {
		int numFours = 0;

		for (int i = 1; i + 3 <= boardSize; i++) {
			for (int j = 1; j + 3 <= boardSize; j++) {
				int count = 0;
				int increment = 0;
				for (int x = i, y = j; increment < 4; increment++, x++, y++) {
					if (getCell(x, y) == aPebble) {
						count++;
					} else {
						count = 0;
					}
					if (count == 4) {
						numFours++;
						count = 0;
					}
				}
			}

		}

		return numFours;
	}

	private int fourBackwards(Pebble aPebble) {
		int numFours = 0;

		for (int i = boardSize; i - 3 >= 0; i--) {
			for (int j = 1; j + 3 <= boardSize; j++) {
				int count = 0;
				int increment = 0;
				for (int x = i, y = j; increment < 4; increment++, x--, y++) {
					if (getCell(x, y) == aPebble) {
						count++;
					} else {
						count = 0;
					}
					if (count == 4) {
						numFours++;
						count = 0;
					}

				}
			}

		}

		return numFours;
	}

	public Pebble[][] getBoard() {
		return board;
	}


	public void winner() {
		if (fiveX() || (gameEnds() && fourX() > fourO())) {
			JOptionPane.showMessageDialog(null, "Black Wins!", "Winner!", JOptionPane.PLAIN_MESSAGE);
		}
		if (fiveO() || (gameEnds() && fourX() > fourO())) {
			JOptionPane.showMessageDialog(null, "White Wins!", "Winner!", JOptionPane.PLAIN_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Tie game!", "It's a tie!", JOptionPane.PLAIN_MESSAGE);
		}
		dispose();

	}

	/*
	 * 
	 * Methods for Sammy
	 * 
	 * 
	 * 
	 */
	private int[] randomNear(int dist) {
		if (isBoardFull())
			return null;

		int[] index = randomEmpty();

		int max = (boardSize * boardSize) - 1;
		int count = 0;
		while (count < max) {
			int x = index[0];
			int y = index[1];
			for (int i = x - dist; i <= x + dist; i++) {
				for (int j = y - dist; j <= y + dist; j++) {
					if (i == x && j == y) {
						continue;
					}
					if (getCell(i, j) != Pebble.EMPTY) {
						int[] res = { x, y };
						return res;
					}
				}

			}
			count++;
			index = randomEmpty();
		}

		return null; // just in case
	}

	public int[] randomAdjacent() {
		return randomNear(1);
	}

	public int[] randomClose() {
		return randomNear(2);
	}

	public int[] randomEmpty() {

		Random r = new Random();
		for (int i = r.nextInt(boardSize) + 1; !isBoardFull(); i = r.nextInt(boardSize) + 1) {
			int j = r.nextInt(boardSize) + 1;
			if (isEmpty(i, j)) {
				int[] result = { i, j };
				return result;
			}
			j = r.nextInt(boardSize) + 1;
		}
		int[] noRes = { -1, -1 };
		return noRes;
	}

	public int[] clever() {

		return null;
	}

}

class Marker extends JLabel {
	Pebble color;

	public Marker(Pebble color) {
		setColor(color);
	}

	// draw initial pebble
	protected void paintComponent(Graphics g) {
		if (color == Pebble.X)
			g.setColor(Color.BLACK);
		else if (color == Pebble.O)
			g.setColor(Color.WHITE);
		else
			return;
		g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);
	}

	public void setColor(Pebble color) {
		this.color = color;
	}

	public void changeColor(Pebble color) {
		setColor(color);
		repaint();
	}

}
