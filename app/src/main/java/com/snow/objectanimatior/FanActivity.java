package com.snow.objectanimatior;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FanActivity extends Activity {

    private ListView mListView;
    private List<String> mData;
    private ArrayAdapter adapter;
    private ArcMenu arcMenu;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fan);

        initView();
        initData();
        mListView.setAdapter(
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mData));
        initEvent();

    }

    private void initEvent() {
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }
            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                if (arcMenu.isOpen()) {
                    arcMenu.toogleItem(500);

                }
            }
        });
        arcMenu.setmItemClick(new ArcMenu.MenuItemClick() {
            @Override
            public void OnClick(View view, int pos) {
                Toast.makeText(FanActivity.this, view.getTag() + "", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initData() {
        for (int i = 'A'; i <'Z' ; i++) {
            mData.add((char) i + "");
        }


    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.lv_listView);
        mData = new ArrayList<>();
        arcMenu = (ArcMenu) findViewById(R.id.menu_tab);

    }
}
