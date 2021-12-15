package com.sheyko;

import com.sheyko.core.GameLogic;
import com.sheyko.core.TableMoves;
import com.sheyko.core.TableMovesCombinations;
import org.apache.commons.codec.digest.HmacAlgorithms;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import static com.sheyko.crypto.GameCryptoTrails.hmac;
import static com.sheyko.crypto.GameCryptoTrails.securityKey;


public class App {
    private static final String AMOUNT_EXCEPTION = "Arguments less than 3. Enter the number of arguments greater than or equal to 3";
    private static final String NUMBER_ODD_EXCEPTION = "The number of elements is odd";
    private static final String FREQUENCY_EXCEPTION = "there is a repetition in the parameters";
    private static final String ENTER = "Enter your move : ";

    public static void main(String[] args) throws NoSuchAlgorithmException {
        validationNumbersElements( args );
        validationFrequency(args);

        var input = new Scanner( System.in );
        var movePlayer = "";
        var moveAI = 0;
        var key = securityKey();
        moveAI = new Random().nextInt( args.length );


        message( "HMAC : " + hmac( HmacAlgorithms.HMAC_SHA_256.getName(), args[moveAI],key ) + System.lineSeparator() );
        TableMoves.show( args );
        message( ENTER );
        movePlayer = input.next();

        if ( movePlayer.equals( "?" ) ) {
            TableMovesCombinations.help(args);
            message( ENTER );
            movePlayer = input.next();
        } else if ( movePlayer.equals( "0" ) ) message( "bye!" );

        var result = GameLogic.resultBattle( Integer.parseInt( movePlayer ), moveAI, args.length );
        message( "computer move : " + args[moveAI] + '\n' + "player move : " + args[Integer.parseInt( movePlayer )] );
        message( result.getStatus() );
        message( "HMACkey : " + key );

    }

    private static void message(String mess) {
        System.out.println( mess );
    }

    private static void validationNumbersElements(String[] args) {
        if ( args.length < 3 ) message( AMOUNT_EXCEPTION );
        else if ( args.length % 2 != 1 ) message( NUMBER_ODD_EXCEPTION );
    }

    private static void validationFrequency(String[] moves){
        var frequency = Arrays.stream(moves)
                .anyMatch(move -> Collections.frequency( Arrays.asList( moves ), move) > 1);
        if (frequency) message(FREQUENCY_EXCEPTION);
    }
}