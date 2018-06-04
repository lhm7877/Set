package com.hoomin.lhm7877.set.data.source;

import android.content.Context;

import java.util.List;
import java.util.Queue;

/**
 * <pre>
 * Created by lhm0805 on 2018-05-29.
 * </pre>
 */
public interface MainDataSource {
    interface LoadImageCallback{
        void onImageLoaded(Queue<Card> list);
    }

    void loadCards(Context context, LoadImageCallback loadImageCallback);

    int getRowSize();
    void setRowSize(int rowSize);
}
