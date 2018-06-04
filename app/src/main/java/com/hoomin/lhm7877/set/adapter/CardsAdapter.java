package com.hoomin.lhm7877.set.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.hoomin.lhm7877.set.adapter.contract.CardsAdapterContract;
import com.hoomin.lhm7877.set.adapter.holder.CardsViewHolder;
import com.hoomin.lhm7877.set.data.source.Card;
import com.hoomin.lhm7877.set.listener.MyOnItemClickListener;

import java.util.Queue;

/**
 * <pre>
 * Created by lhm0805 on 2018-06-04.
 * </pre>
 */
public class CardsAdapter extends RecyclerView.Adapter<CardsViewHolder> implements CardsAdapterContract.Model, CardsAdapterContract.View{
    @NonNull
    @Override
    public CardsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CardsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public void setOnClickListener(MyOnItemClickListener onClickListener) {

    }

    @Override
    public void notifyAdapter() {

    }

    @Override
    public void addItems(Queue<Card> cardItems, int boardSize) {

    }

    @Override
    public void clearItem() {

    }

    @Override
    public Card getItem(int position) {
        return null;
    }

    @Override
    public void addCards() {

    }
}
