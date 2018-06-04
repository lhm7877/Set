package com.hoomin.lhm7877.set.data.source.local;

import android.content.Context;

import com.hoomin.lhm7877.set.data.source.Card;
import com.hoomin.lhm7877.set.data.source.MainDataSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * <pre>
 * Created by lhm0805 on 2018-05-30.
 * </pre>
 */
public class MainLocalDataSource implements MainDataSource{

    private Queue<Card> deckQueue;

    public MainLocalDataSource(Context context) {
        deckQueue = makeCards(context);
    }

    private int rowSize = 4;

    @Override
    public void loadCards(Context context, LoadImageCallback loadImageCallback) {
        if (loadImageCallback != null) {
            loadImageCallback.onImageLoaded(deckQueue);
        }
    }

    @Override
    public int getRowSize() {
        return rowSize;
    }

    @Override
    public void setRowSize(int rowSize) {
        this.rowSize = rowSize;
    }

    private Queue<Card> makeCards(Context context){
        deckQueue = new LinkedList<>();
        ArrayList<Card> deckList = new ArrayList<>();

        Card[] cards = new Card[81];
        int index = 0;
        for(int color = 0; color<3; color++){
            for (int shading = 0; shading<3; shading++){
                for ( int shape = 0; shape<3; shape++){
                    for (int number = 0; number<3; number++){
                        final int resource = context.getResources().getIdentifier("set_deck" + (index +1), "drawable", context.getPackageName());
                        deckList.add(new Card(resource, number, shape, shading, color, "set_deck"+(index+1)));
                        index++;
                    }
                }
            }
        }
        Collections.shuffle(deckList);
        for (Card card : deckList) {
            deckQueue.offer(card);
        }
        return deckQueue;
    }

}