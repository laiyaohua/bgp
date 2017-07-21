package com.meizu.boardgameplatform.Werewolfkill.Activity.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.meizu.boardgameplatform.R;
import com.meizu.boardgameplatform.Werewolfkill.Activity.TimeOutInterface;
import com.meizu.boardgameplatform.Werewolfkill.Activity.View.CountDownTextView;

import butterknife.BindView;


public  class BaseActivity extends AppCompatActivity implements TimeOutInterface {
    @BindView(R.id.ll_countdown)
    CountDownTextView countDownTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }
    protected void initCountDown(){
        countDownTextView.setTimeOutInterface(this);
        countDownTextView.startCount();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        countDownTextView.cancel();

    }

    @Override
    public void timeout() {

    }
}
