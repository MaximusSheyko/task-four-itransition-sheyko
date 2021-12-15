package com.sheyko.core;

import com.sheyko.core.GameLogic;
import com.sheyko.core.StatusWinners;

public class TableMovesCombinations {
    private static final String FORMAT_FOR_MOVE_WINNER ="%s >> ";
    private static final String FORMAT_FOR_MOVE_LOSER = "%s-";
    private static final String SLASH = "|";

    public static void help(String[] moves){
        var build = new StringBuilder();

        System.out.println("Moves combination");
        for (int moveWinner = 0; moveWinner < moves.length; moveWinner++) {
            build.append(String.format(SLASH + FORMAT_FOR_MOVE_WINNER, moves[moveWinner]));

            for (int indexLoser = 0; indexLoser < moves.length; indexLoser++) {
                if (GameLogic.resultBattle(indexLoser,moveWinner, moves.length ).equals( StatusWinners.PLAYER_WIN )) {
                    build.append(String.format( FORMAT_FOR_MOVE_LOSER,moves[indexLoser]));
                }
                if (indexLoser == moves.length - 1){
                    build.deleteCharAt(build.length() - 1);
                    build.append(SLASH).append('\n');
                }
            }
        }

        System.out.println(build);
    }
}
