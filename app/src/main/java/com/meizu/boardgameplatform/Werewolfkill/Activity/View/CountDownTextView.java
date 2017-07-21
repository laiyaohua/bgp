package com.meizu.boardgameplatform.Werewolfkill.Activity.View;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.meizu.boardgameplatform.R;
import com.meizu.boardgameplatform.Werewolfkill.Activity.TimeOutInterface;
import com.meizu.boardgameplatform.Werewolfkill.Activity.utils.Utils;


/**
 * Created by Administrator on 2017/5/10.
 */
public class CountDownTextView extends LinearLayout {
    private Context context;
    private TextView countDownText;
    private TimeOutInterface timeOutInterface;
    private String countDownTextString = "秒操作时间";
    public CountDownTextView(Context context) {
        super(context);
        this.context = context;
        initView();
    }
    public void setTimeOutInterface(TimeOutInterface timeOutInterface){
        this.timeOutInterface = timeOutInterface;
    }
    public CountDownTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public CountDownTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    private void initView(){
        countDownText = new TextView(context);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10, 10, 10, 10);
        countDownText.setLayoutParams(layoutParams);
        countDownText.setTextSize(16);
        countDownText.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
//            radioButton.setBackgroundResource(R.drawable.bt_bg_style);
        countDownText.setGravity(Gravity.CENTER);
        addView(countDownText);
    }

    private CountDownTimer timer = new CountDownTimer(Utils.TIME_COUNT, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            countDownText.setText((millisUntilFinished / 1000) + countDownTextString);
        }

        @Override
        public void onFinish() {
            countDownText.setText("时间到!");
            timeOutInterface.timeout();
        }
    };
    public void setCountDownTextString(String string){
        countDownTextString = string;
    }

    public void startCount(){
        countDownText.setVisibility(VISIBLE);
        timer.start();
    }
    public void setTime(int millis){
        Utils.TIME_COUNT = millis;
    }
    public void cancel(){
        countDownText.setVisibility(GONE);
        timer.cancel();
    }
}
