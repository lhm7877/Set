package com.hoomin.lhm7877.set.adapter.contract;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.hoomin.lhm7877.set.data.source.Card;
import com.hoomin.lhm7877.set.listener.MyOnItemClickListener;

import java.util.List;
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

        void showWrongAlert();

        void resetSelectedViews(List<Card> selectedCardList);

        void showSelectedCard(ImageView ivCard, Card selectedCard);
    }

    interface Model{
        void addItems(Queue<Card> deck, int boardSize);

        void clearItem();

        Card getItem(int position);

        void addCards();

        void addSelectedCardInfo(int position, ImageView ivCard, Card selectedCard);

        List<Card> getSelectedCardList();

        Card getNewCard();

        void removeCardFromDeckList(@NonNull Card cardToBeRemoved);

        void setCardToDeckList(int index, @NonNull Card newCard);

        void clearSelectedCardListInfo();
    }
}
