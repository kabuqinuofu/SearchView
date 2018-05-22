package com.yc.searchview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.yc.smoothsearchview.SmoothSearchView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> mTags;
    private SmoothSearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mSearchView = findViewById(R.id.search);
        mSearchView.setOnIconRemoveListener(new SmoothSearchView.OnIconRemoveListener() {
            @Override
            public void onIconRemoved(View view, String tag) {
                Toast.makeText(MainActivity.this, "移除：" + tag, Toast.LENGTH_SHORT).show();
            }
        });

        mSearchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Toast.makeText(MainActivity.this, "输入内容：" + editable.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        mTags = new ArrayList<>();

        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setImageResource(R.drawable.ic_launcher_background);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(60, 60);
                params.leftMargin = 10;
                imageView.setLayoutParams(params);
                String tag = System.currentTimeMillis() + "";
                mSearchView.addIconView(imageView, tag);
                mTags.add(tag);
            }
        });

        findViewById(R.id.remove).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTags.size() > 0) {
                    mSearchView.removeIconView(mTags.get(0));
                    mTags.remove(0);
                } else {
                    Toast.makeText(MainActivity.this, "请添加", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}
