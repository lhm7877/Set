package com.hoomin.lhm7877.set.adapter;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.hoomin.lhm7877.set.R;
import com.hoomin.lhm7877.set.adapter.contract.CardsAdapterContract;
import com.hoomin.lhm7877.set.algorithm.Set;
import com.hoomin.lhm7877.set.data.source.Card;
import com.hoomin.lhm7877.set.listener.MyOnItemClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import co.ceryle.fitgridview.FitGridAdapter;

/**
 * <pre>
 * Created by lhm0805 on 2018-05-29.
 * </pre>
 */
public class MainCardsAdapter extends FitGridAdapter implements CardsAdapterContract.Model, CardsAdapterContract.View {

    private final Context context;
    private MyOnItemClickListener myOnItemClickListener;

    private Queue<Card> deckQueue;
    private ArrayList<Card> deckList;
    private ArrayList<ImageView> selectedViewList = new ArrayList<>();
    private ArrayList<Integer> selectedIndexList = new ArrayList<>();
    private List<Card> selectedCardList = new ArrayList<>();

    public MainCardsAdapter(Context context) {
        super(context, R.layout.item_card);
        this.context = context;
    }

    @Override
    public void setOnClickListener(MyOnItemClickListener onClickListener) {
        this.myOnItemClickListener = onClickListener;
    }

    @Override
    public void notifyAdapter() {
        notifyDataSetChanged();
    }

    @Override
    public void showWrongAlert() {

    }

    @Override
    public void resetSelectedViews(List<Card> selectedCardList) {

    }

    @Override
    public void showSelectedCard(ImageView ivCard, Card selectedCard) {

    }

    @Override
    public void onBindView(int position, View itemView) {
        ImageView ivCard = itemView.findViewById(R.id.ivCard);
        Glide.with(itemView)
                .load(deckList.get(position).getImgageRes())
                .into(ivCard);

        if (!Set.FindSet(deckList)) {
            Toast.makeText(context, "없음", Toast.LENGTH_LONG).show();
        }

        itemView.setOnClickListener(v -> {
            Card selectedCard = deckList.get(position);
            Set.FindSet(deckList);
            if (myOnItemClickListener != null) {
                clickItem(position, ivCard, selectedCard);

                if (selectedCardList.size() == 3) {
                    click3Items();
                }
                myOnItemClickListener.onItemClick(position, ivCard);
            }
        });
    }

    @Override
    public void addItems(Queue<Card> deck, int boardSize) {
        this.deckQueue = deck;
        this.deckList = new ArrayList<>();
        for (int i = 0; i < boardSize; i++) {
            deckList.add(deckQueue.poll());
        }
    }

    @Override
    public void clearItem() {
        if (deckQueue != null) {
            deckQueue.clear();
        }
    }

    @Override
    public Card getItem(int position) {
        return deckList.get(position);
    }

    @Override
    public void addCards() {
        deckList.add(deckQueue.poll());
        deckList.add(deckQueue.poll());
        deckList.add(deckQueue.poll());
    }

    @Override
    public void addSelectedCardInfo(int position, ImageView ivCard, Card selectedCard) {
    }

    @Override
    public List<Card> getSelectedCardList() {
        return selectedCardList;
    }

    @Override
    public Card getNewCard() {
        return null;
    }

    @Override
    public void removeCardFromDeckList(@NonNull Card cardToBeRemoved) {

    }

    @Override
    public void setCardToDeckList(int index, @NonNull Card newCard) {

    }

    @Override
    public void clearSelectedCardListInfo() {

    }

    private void clickItem(int position, ImageView ivCard, Card card) {
    }

    private void click3Items() {
        boolean isValid = Set.IsSetValid(selectedCardList.get(0), selectedCardList.get(1), selectedCardList.get(2));
        if (isValid) {
            for (int i = 0; i < selectedCardList.size(); i++) {
                Card card = deckQueue.poll();
                if (card == null) {
                    deckList.remove(selectedCardList.get(i));
//                    Glide.with(context)
//                            .load(android.R.color.transparent)
//                            .into(selectedViewList.get(i));
                } else {
                    deckList.set(selectedIndexList.get(i), card);
                    Glide.with(context)
                            .load(card.getImgageRes())
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .into(selectedViewList.get(i));
                }
            }
            Set.FindSet(deckList);
        } else {
            for (ImageView view : selectedViewList) {
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
        for (ImageView imageView : selectedViewList) {
            imageView.setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
        }
//        notifyAdapter();
        myOnItemClickListener.onItem3Click(selectedCardList);
        selectedViewList.clear();
        selectedCardList.clear();
    }
}
