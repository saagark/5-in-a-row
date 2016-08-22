package ProgAssignment3;

import ProgAssignment3.Board.Pebble;

public abstract class Player {
	private Pebble color;
	private Board board;

	public abstract int[] nextMove();

	public Player(Pebble color, Board b) {
		this.color = color;
		board = b;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Pebble getColor() {
		return color;
	}

	public void doMove(int x, int y) {
		board.setCell(x, y, color);
	}

}
