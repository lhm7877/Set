package com.hoomin.lhm7877.set.view.main.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.hoomin.lhm7877.set.adapter.contract.CardsAdapterContract;
import com.hoomin.lhm7877.set.data.source.Card;
import com.hoomin.lhm7877.set.data.source.MainRepository;
import com.hoomin.lhm7877.set.listener.MyOnItemClickListener;

import java.util.Queue;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * <pre>
 * Created by lhm0805 on 2018-05-28.
 * </pre>
 */
public class MainPresenter implements MainContract.Presenter, MyOnItemClickListener {

    private final MainRepository mMainRepository;

    private final MainContract.View mMainView;

    private CardsAdapterContract.Model adapterModel;
    private CardsAdapterContract.View adapterView;

    public MainPresenter(@NonNull MainRepository mainRepository, @NonNull MainContract.View mainView) {
        mMainRepository = checkNotNull(mainRepository);
        mMainView = checkNotNull(mainView);

        mMainView.setPresenter(this);
    }

    @Override
    public void start() {
        loadCards();
    }

    private void loadCards() {
        mMainRepository.loadCards((Context) mMainView, (Queue<Card> list) -> {
            if (list != null) {
                adapterModel.addItems(list, mMainRepository.getBoardSize());
                adapterView.notifyAdapter();
            }
        });
    }

    @Override
    public void onItemClick(int position) {
//        Card card = adapterModel.getItem(position);
    }

    @Override
    public void onItem3Click(boolean isValid) {
        adapterView.notifyAdapter();
    }

    @Override
    public void setMainCardsAdapterModel(CardsAdapterContract.Model adapterModel) {
        this.adapterModel = adapterModel;
    }

    @Override
    public void setMainCardsAdapterView(CardsAdapterContract.View adapterView) {
        this.adapterView = adapterView;
        this.adapterView.setOnClickListener(this);
    }

    @Override
    public void addNewCards() {
        int rowSize = this.mMainRepository.getRowSize();
        if( rowSize < 6) {
            this.adapterModel.addCards();
            this.adapterView.notifyAdapter();
            this.mMainRepository.setRowSize(rowSize + 1);
            this.mMainView.changeSize(rowSize + 1, 3);
        }
    }
}