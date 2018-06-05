package com.hoomin.lhm7877.set.adapter;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.hoomin.lhm7877.set.R;
import com.hoomin.lhm7877.set.adapter.contract.CardsAdapterContract;
import com.hoomin.lhm7877.set.adapter.holder.CardsViewHolder;
import com.hoomin.lhm7877.set.algorithm.Set;
import com.hoomin.lhm7877.set.data.source.Card;
import com.hoomin.lhm7877.set.listener.MyOnItemClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * <pre>
 * Created by lhm0805 on 2018-06-04.
 * </pre>
 */
public class CardsAdapter extends RecyclerView.Adapter<CardsViewHolder> implements CardsAdapterContract.Model, CardsAdapterContract.View{

    private final Context context;
    private MyOnItemClickListener onItemClickListener;

    private Queue<Card> deckQueue;
    private ArrayList<Card> deckList;
    private ArrayList<ImageView> selectedViewList = new ArrayList<>();
    private List<Card> selectedCardList = new ArrayList<>();

    public CardsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CardsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CardsViewHolder(context, parent, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CardsViewHolder holder, int position) {
        {
            // FIXME: 테스트용 토스트 메시지
            if (!Set.FindSet(deckList)) {
                Toast.makeText(context, "없음", Toast.LENGTH_LONG).show();
            }
        }
        holder.onBind(getItem(position), position);
    }

    @Override
    public int getItemCount() {
        return deckList != null ? deckList.size() : 0;
    }

    @Override
    public void setOnClickListener(MyOnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void notifyAdapter() {
        notifyDataSetChanged();
    }

    @Override
    public void showWrongAlert() {
        for (ImageView view : this.selectedViewList) {
            int colorFrom = context.getResources().getColor(R.color.colorWrong);
            int colorTo = context.getResources().getColor(android.R.color.transparent);
            ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
            colorAnimation.setDuration(300);
            colorAnimation.setRepeatMode(ValueAnimator.REVERSE);
            colorAnimation.setRepeatCount(2);
            colorAnimation.addUpdateListener(animation -> view.setBackgroundColor((Integer) animation.getAnimatedValue()));
            colorAnimation.start();
        }
    }

    @Override
    public void resetSelectedViews(List<Card> selectedCardList) {
        for (ImageView imageView : selectedViewList) {
            imageView.setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
        }
    }

    @Override
    public void addItems(Queue<Card> deck, int boardSize) {
        this.deckQueue = deck;
        this.deckList = new ArrayList<>();
        for (int i = 0; i < boardSize; i++) {
            deckList.add(getNewCard());
        }
    }

    @Override
    public void clearItem() {
        if (deckList != null) {
            deckList.clear();
        }
    }

    @Override
    public Card getItem(int position) {
        return deckList.get(position);
    }

    @Override
    public void addCards() {
        deckList.add(getNewCard());
        deckList.add(getNewCard());
        deckList.add(getNewCard());
    }

    @Override
    public void addSelectedCardInfo(int position, ImageView ivCard, Card selectedCard) {
        if (selectedCardList.contains(selectedCard)) {
            selectedCardList.remove(selectedCard);
            selectedViewList.remove(ivCard);
        }else {
            selectedCard.setIndex(position);
            selectedCardList.add(selectedCard);
            selectedViewList.add(ivCard);
        }
    }


    @Override
    public void showSelectedCard(ImageView ivCard, Card selectedCard) {
        if (selectedCardList.contains(selectedCard)) {
            ivCard.setBackground(context.getDrawable(R.drawable.card_border));
        } else {
            ivCard.setBackground(context.getDrawable(R.color.colorTransparent));
        }
    }


    @Override
    public List<Card> getSelectedCardList() {
        return selectedCardList;
    }

    @Override
    public Card getNewCard() {
        return deckQueue.poll();
    }

    @Override
    public void removeCardFromDeckList(@NonNull Card cardToBeRemoved) {
        deckList.remove(cardToBeRemoved);
    }

    @Override
    public void setCardToDeckList(int index, @NonNull Card newCard) {
        deckList.set(index, newCard);
    }

    @Override
    public void clearSelectedCardListInfo() {
        selectedViewList.clear();
        selectedCardList.clear();
    }

}
