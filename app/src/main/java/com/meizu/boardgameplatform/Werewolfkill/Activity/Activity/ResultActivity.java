package com.meizu.boardgameplatform.Werewolfkill.Activity.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.meizu.boardgameplatform.R;


public class ResultActivity extends AppCompatActivity {
    private int helpNum,killNum;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        init();
        initView();
    }
    private void init(){
        helpNum = getIntent().getIntExtra("helpNum",-1);
        killNum = getIntent().getIntExtra("killNum",-1);

    }

    private void initView(){
        tvResult = (TextView)findViewById(R.id.tv_result);
    }
    public void ResultOnClick(View v){
        tvResult.setVisibility(View.VISIBLE);
        if(killNum == -1){
            if(helpNum == -1){
                tvResult.setText("昨晚是个平安夜！");
            }else {
                tvResult.setText(String.format(getString(R.string.result),helpNum+1));
            }
        }else {
            if(helpNum != -1){
                tvResult.setText(String.format(getString(R.string.result_two),helpNum+1,killNum+1));
            }else {
                tvResult.setText(String.format(getString(R.string.result),killNum+1));
            }
        }
    }
}
