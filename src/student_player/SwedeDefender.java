package student_player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import boardgame.Move;
import coordinates.Coord;
import coordinates.Coordinates;
import tablut.TablutBoardState;
import tablut.TablutMove;

public class SwedeDefender {
	public static Move getOpeningMove(TablutBoardState bs) {
        return Minimax.getBestMove(bs, TablutBoardState.SWEDE, 2);
    }

    public static double evaluatePosition(TablutBoardState bs, TablutMove move) {
        if (bs.gameOver()) {
            if (bs.getWinner() == TablutBoardState.SWEDE) {
                return 100000 - bs.getTurnNumber();
            } else {
                return -100000;
            }
        }

        double value = 1000;
        //Swedes prefer trades as it frees up the board
        value += bs.getNumberPlayerPieces(TablutBoardState.SWEDE) * 20;
        value -= bs.getNumberPlayerPieces(TablutBoardState.MUSCOVITE) * 30;

        //Favour moves that move the king to a wall
        Coord kingPosition = bs.getKingPosition();
        if (kingPosition.x == 0 || kingPosition.x == 8 || kingPosition.y == 0 || kingPosition.y == 8)
            value += 60;

        //Slightly favour moves that move the king
        if (move.getEndPosition().x == kingPosition.x && move.getEndPosition().y == kingPosition.y) {
            value += 3;
        }

        List<Coord> freeCorners = MyTools.getFreeCorners(bs);
        //Reward the Swede's for moving towards
        for(Coord freeCorner: freeCorners) {
            //Move away from cutoff corners
            if (bs.getKingPosition().maxDifference(freeCorner) <= 3) {
                value += 15;
            }
        }

        //Slightly reward king for moving towards corners
        value -= Coordinates.distanceToClosestCorner(bs.getKingPosition());

        return value;
    }
}
