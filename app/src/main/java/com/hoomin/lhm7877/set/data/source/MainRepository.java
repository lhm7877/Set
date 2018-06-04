package com.hoomin.lhm7877.set.data.source;

import android.content.Context;

import com.hoomin.lhm7877.set.data.source.local.MainLocalDataSource;

/**
 * <pre>
 * Created by lhm0805 on 2018-05-29.
 * </pre>
 */
public class MainRepository implements MainDataSource {

    private static MainRepository INSTANCE = null;

    private final MainDataSource mMainLocalDataSource;

    private int boardSize;

    private MainRepository(Context context) {
        this.mMainLocalDataSource =  new MainLocalDataSource(context);
        boardSize = 12;
    }

    public static MainRepository getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new MainRepository(context);
        }
        return INSTANCE;
    }

    @Override
    public void loadCards(Context context, final LoadImageCallback loadImageCallback) {
        mMainLocalDataSource.loadCards(context, list -> {
            if (loadImageCallback!=null) {
                loadImageCallback.onImageLoaded(list);
            }
        });
    }

    @Override
    public int getRowSize() {
        return mMainLocalDataSource.getRowSize();
    }

    @Override
    public void setRowSize(int rowSize) {
        mMainLocalDataSource.setRowSize(rowSize);
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }
}
