package com.meizu.boardgameplatform.Werewolfkill.Activity.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.meizu.boardgameplatform.R;
import com.meizu.boardgameplatform.Werewolfkill.Activity.Adapter.KillChooseAdapter;
import com.meizu.boardgameplatform.Werewolfkill.Activity.DialogListener;
import com.meizu.boardgameplatform.Werewolfkill.Activity.KillInterface;
import com.meizu.boardgameplatform.Werewolfkill.Activity.View.DialogViewer;
import com.meizu.boardgameplatform.Werewolfkill.Activity.utils.SoundUtils;
import com.meizu.boardgameplatform.Werewolfkill.Activity.utils.Utils;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class WerewolfActivity extends BaseActivity implements KillInterface {
    @BindView(R.id.gridview) GridView killGrid;
    @BindView(R.id.bt_kill_no) Button btKillNobody;
    @BindView(R.id.kill_ok) RadioButton btKillOk;
    @BindView(R.id.kill_layout) LinearLayout chooseLayout;
    private Handler mHandler;
    private int killNum = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_werewolf);
        mHandler = new Handler();
        initView();
        playMusic(1);
    }

    private void playMusic(int id) {
        SoundUtils.playSound(SoundUtils.mSoundPool,id);
    }

    private void initView(){
        ButterKnife.bind(this);
        initCountDown();
        btKillOk.setVisibility(View.GONE);
        chooseLayout.setVisibility(View.VISIBLE);
        KillChooseAdapter killChooseAdapter = new KillChooseAdapter(this,this,KillChooseAdapter.TYPE_WEREWOLF);
        killGrid.setAdapter(killChooseAdapter);
    }

    @Override
    public void kill(final int killNum) {
        toNextPage();
    }
    @OnClick(R.id.bt_kill_no) void killOnbodyClick(){
        killNum = -1;
        String content = "确认不杀人？";
        DialogViewer dialogViewer = new DialogViewer(WerewolfActivity.this,content,new DialogListener() {
            @Override
            public void onDialogClick(Dialog dialog, boolean isLeftButonClick, boolean isRightButtonClick) {
                if(isRightButtonClick){
                   toNextPage();
                }
            }
        });
        dialogViewer.show();
    }

    @Override
    public void timeout() {
        super.timeout();
        toNextPage();
    }
    // 延迟跳转到下一个页面
    private void toNextPage(){
        //TODO
        countDownTextView.cancel();
        btKillOk.setVisibility(View.VISIBLE);
        chooseLayout.setVisibility(View.GONE);
        playMusic(2);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WerewolfActivity.this,WitchActivity.class);
                intent.putExtra("killNum", killNum);
                startActivity(intent);
            }
        }, Utils.TIME_DELAY);
    }
}
