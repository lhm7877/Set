package com.hoomin.lhm7877.set.listener;

import android.widget.ImageView;

import com.hoomin.lhm7877.set.data.source.Card;

import java.util.List;

/**
 * <pre>
 * Created by lhm0805 on 2018-05-29.
 * </pre>
 */
public interface MyOnItemClickListener {
    void onItemClick(int position, ImageView ivCard);
    void onItem3Click(List<Card> selectedCardList);
}
