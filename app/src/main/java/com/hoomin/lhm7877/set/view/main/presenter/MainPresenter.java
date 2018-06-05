package com.hoomin.lhm7877.set.view.main.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.hoomin.lhm7877.set.adapter.contract.CardsAdapterContract;
import com.hoomin.lhm7877.set.algorithm.Set;
import com.hoomin.lhm7877.set.data.source.Card;
import com.hoomin.lhm7877.set.data.source.MainRepository;
import com.hoomin.lhm7877.set.listener.MyOnItemClickListener;

import java.util.List;
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
    public void onItemClick(int position, ImageView ivCard) {
        Card selectedCard = adapterModel.getItem(position);
        adapterModel.addSelectedCardInfo(position, ivCard, selectedCard);
        adapterView.showSelectedCard(ivCard, selectedCard);

        List<Card> selectedCardList = adapterModel.getSelectedCardList();

        if (selectedCardList.size() == 3){
            onItem3Click(selectedCardList);
        }
    }

    @Override
    public void onItem3Click(List<Card> selectedCardList) {
        boolean isValid = Set.IsSetValid(selectedCardList.get(0), selectedCardList.get(1), selectedCardList.get(2));

        // 정답일 경우
        if (isValid) {
            for (int i = 0; i < selectedCardList.size(); i++){
                Card newCard = adapterModel.getNewCard();
                // 덱에 카드가 남아있지 않을 경우
                if (newCard == null) {
                    adapterModel.removeCardFromDeckList(selectedCardList.get(i));
                    //TODO: 삭제, 변경했을때 방법 1. list에 데이터 변경 -> notifyDataChange -> 카드가 자동 정렬되서 빈곳이 없어짐 -> ui적으로 안좋다.
                    //                    방법 2. Glide로 이미지 변경
                } else { // 덱에 카드가 남아있을 경우
                    adapterModel.setCardToDeckList(selectedCardList.get(i).getIndex(), newCard);
                }
                adapterView.notifyAdapter();
            }
        } else { // 오답일 경우
            adapterView.showWrongAlert();
        }
        adapterView.resetSelectedViews(selectedCardList);
        adapterModel.clearSelectedCardListInfo();
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