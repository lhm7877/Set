package com.hoomin.lhm7877.set.view.main.presenter;

import com.hoomin.lhm7877.set.BasePresenter;
import com.hoomin.lhm7877.set.BaseView;
import com.hoomin.lhm7877.set.adapter.contract.CardsAdapterContract;

/**
 * <pre>
 * Created by lhm0805 on 2018-05-28.
 * </pre>
 */
public interface MainContract {
    interface View extends BaseView<Presenter>{
        void changeEdgeColor(int i);
        void changeSize(int r, int c);
    }

    interface Presenter extends BasePresenter{
        void setMainCardsAdapterModel(CardsAdapterContract.Model adapterModel);
        void setMainCardsAdapterView(CardsAdapterContract.View adapterView);
        void addNewCards();
    }
}
