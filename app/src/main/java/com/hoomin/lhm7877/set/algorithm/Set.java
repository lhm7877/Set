package com.hoomin.lhm7877.set.algorithm;

import android.util.Log;

import com.hoomin.lhm7877.set.data.source.Card;

import java.util.ArrayList;

/**
 * <pre>
 * Created by lhm0805 on 2018-05-31.
 * </pre>
 */
public class Set {
    public static boolean FindSet(ArrayList<Card> cards) {
        ArrayList<Card[]> validSets = new ArrayList<>();

        for (int i = 0; i < cards.size(); i++) {
            for (int j = i + 1; j < cards.size(); j++) {
                for (int k = j + 1; k < cards.size(); k++) {
                    if (IsSetValid(cards.get(i), cards.get(j), cards.get(k))) {
                        //TODO: validset 찾기
                        validSets.add(new Card[]{cards.get(i), cards.get(j), cards.get(k)});
                    }
                }
            }
        }
        if(validSets.size()>0)
        if (validSets.get(0).length>0) {
        for (int i = 0; i < validSets.get(0).length; i++) {
            Log.i("valid", String.valueOf(validSets.get(0)[i].getTestResource()));
        }

        }
        return true;
    }

    //Valid sets must either have all different numbers, shapes & colors, or all the same
    public static boolean IsSetValid(Card card1, Card card2, Card card3) {
        if (!areAllEqual(card1.getNumber(), card2.getNumber(), card3.getNumber()) && !areAllDifferent(card1.getNumber(), card2.getNumber(), card3.getNumber())) {
            return false;
        }
        if (!areAllEqual(card1.getShape(), card2.getShape(), card3.getShape()) && !areAllDifferent(card1.getShape(), card2.getShape(), card3.getShape())) {
            return false;
        }
        if (!areAllEqual(card1.getShading(), card2.getShading(), card3.getShading()) && !areAllDifferent(card1.getShading(), card2.getShading(), card3.getShading())) {
            return false;
        }
        if (!areAllEqual(card1.getColor(), card2.getColor(), card3.getColor()) && !areAllDifferent(card1.getColor(), card2.getColor(), card3.getColor())) {
            return false;
        }
        return true;
    }

    private static boolean areAllEqual(int num1, int num2, int num3) {
        return num1 == num2 && num2 == num3;
    }

    private static boolean areAllDifferent(int num1, int num2, int num3) {
        return num1 != num2 && num2 != num3 && num1 != num3;
    }

}
