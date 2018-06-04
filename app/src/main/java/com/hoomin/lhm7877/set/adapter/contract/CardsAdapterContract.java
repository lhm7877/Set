package com.hoomin.lhm7877.set.adapter.contract;

import com.hoomin.lhm7877.set.data.source.Card;
import com.hoomin.lhm7877.set.listener.MyOnItemClickListener;

import java.util.Queue;

/**
 * <pre>
 * Created by lhm0805 on 2018-05-29.
 * </pre>
 */
public interface CardsAdapterContract {
    interface View{
        void setOnClickListener(MyOnItemClickListener onClickListener);

        void notifyAdapter();
    }

    interface Model{
        void addItems(Queue<Card> cardItems, int boardSize);

        void clearItem();

        Card getItem(int position);

        void addCards();
    }
}
