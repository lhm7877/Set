package com.hoomin.lhm7877.set.adapter.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hoomin.lhm7877.set.R;
import com.hoomin.lhm7877.set.data.source.Card;
import com.hoomin.lhm7877.set.listener.MyOnItemClickListener;

/**
 * <pre>
 * Created by lhm0805 on 2018-05-29.
 * </pre>
 */
public class CardsViewHolder extends RecyclerView.ViewHolder {

    private Context context;

    private ImageView ivCard;

    private MyOnItemClickListener myOnItemClickListener;

    public CardsViewHolder(Context context, ViewGroup parent, MyOnItemClickListener myOnItemClickListener) {
        super(LayoutInflater.from(context).inflate(R.layout.item_card, parent, false));

        this.context = context;
        this.myOnItemClickListener = myOnItemClickListener;

        ivCard = itemView.findViewById(R.id.ivCard);
    }

    public void onBind(Card card, final int position) {
        Glide.with(itemView)
                .load(card.getImgageRes())
                .into(ivCard);

        itemView.setOnClickListener(v -> {
                    if (myOnItemClickListener != null) {
                        myOnItemClickListener.onItemClick(position, ivCard);
                    }
                }
        );
    }
}
