package com.hoomin.lhm7877.set.view.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;

import com.hoomin.lhm7877.set.R;
import com.hoomin.lhm7877.set.adapter.CardsAdapter;
import com.hoomin.lhm7877.set.data.source.MainRepository;
import com.hoomin.lhm7877.set.view.main.presenter.MainContract;
import com.hoomin.lhm7877.set.view.main.presenter.MainPresenter;

import static com.google.common.base.Preconditions.checkNotNull;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private MainContract.Presenter mPresenter;
//    private MainCardsAdapter mainCardsAdapter;
    private CardsAdapter cardsAdapter;

    private RecyclerView rvBoards;
    private ImageView ivDeck;

    private Button btnHint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivDeck = findViewById(R.id.ivDeck);
        btnHint = findViewById(R.id.btnHint);
        rvBoards = findViewById(R.id.rvBoards);

//        mainCardsAdapter = new MainCardsAdapter(this);
        rvBoards.setLayoutManager(new GridLayoutManager(this,3));
        cardsAdapter = new CardsAdapter(this);
        rvBoards.setAdapter(cardsAdapter);

        mPresenter = new MainPresenter(MainRepository.getInstance(this), this);
        mPresenter.setMainCardsAdapterModel(cardsAdapter);
        mPresenter.setMainCardsAdapterView(cardsAdapter);

        ivDeck.setOnClickListener(v -> mPresenter.addNewCards());

        btnHint.setOnClickListener(v -> {
//            mainCardsAdapter.notifyAdapter();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(@NonNull MainContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void changeSize(int r, int c) {
//        gvBoards.setNumRows(r);
//        gvBoards.setNumColumns(c);
//        gvBoards.update();
    }
}
