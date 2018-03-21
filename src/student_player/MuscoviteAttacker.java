package student_player;

import java.util.List;

import boardgame.Move;
import coordinates.Coordinates;
import tablut.TablutBoardState;
import tablut.TablutMove;

public class MuscoviteAttacker {
	public static Move getOpeningMove(TablutBoardState bs) {
		List<TablutMove> openerMoves = bs.getLegalMovesForPosition(Coordinates.get(1, 4));
		for (TablutMove move : openerMoves) {
			if (move.getEndPosition().x == 2 && move.getEndPosition().y == 4) {
				return move;
			}
		}
		
		return null;
	}

	public static Move getMove(TablutBoardState bs) {
		return bs.getRandomMove();
	}
}