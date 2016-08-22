package ProgAssignment3;

import ProgAssignment3.Board.Pebble;

public class Game {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Board b = new Board(6);
		HumanPlayer h1 = new HumanPlayer(Pebble.O, b);
//		HumanPlayer h2 = new HumanPlayer(Pebble.X, b);
		Sammy s = new Sammy(Pebble.X, b);

		while (!b.gameEnds()) {
			 int[] sam = s.nextMove();
			 s.doMove(sam[0],sam[1]);
//			 int[] human1 = h1.nextMove();
//				h1.doMove(human1[0], human1[1]);
			if (b.gameEnds())
				break;
			 int[] human1 = h1.nextMove();
			 h1.doMove(human1[0], human1[1]);
//			 int[] sam = s.nextMove();
//			 s.doMove(sam[0],sam[1]);
		}
		b.winner();

	}

}
