package com.meizu.boardgameplatform.Werewolfkill.Activity.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.meizu.boardgameplatform.R;
import com.meizu.boardgameplatform.Werewolfkill.Activity.Adapter.KillChooseAdapter;
import com.meizu.boardgameplatform.Werewolfkill.Activity.KillInterface;
import com.meizu.boardgameplatform.Werewolfkill.Activity.utils.SoundUtils;
import com.meizu.boardgameplatform.Werewolfkill.Activity.utils.Utils;

import butterknife.BindView;

public class WitchActivity extends BaseActivity implements KillInterface {
    private LinearLayout helpLayout,killLayout,kill_who_layout;
    @BindView(R.id.bt_help_yes) Button helpYes;
    @BindView(R.id.bt_help_no)Button helpNo;
    @BindView(R.id.bt_kill_yes)Button killYes;
    @BindView(R.id.bt_kill_no)Button killNo;
    @BindView(R.id.tv_help_title) TextView helpTitle;
    @BindView(R.id.tv_kill_title)TextView killTitle;
    private int helpNum,witchKillNum = -1 ;
    private Handler mHandler;
    @BindView(R.id.gridview) GridView kill_grid;
    private KillChooseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_witch);
        init();
        initView();
        playMusic(3);
    }

    private void playMusic(int id) {
        SoundUtils.playSound(SoundUtils.mSoundPool,id);
    }

    private void init(){
        helpNum = getIntent().getIntExtra("killNum",-1);
        mHandler = new Handler();
    }
    private void initView(){
        initCountDown();
        helpLayout= (LinearLayout)findViewById(R.id.help_layout);
        killLayout= (LinearLayout)findViewById(R.id.kill_layout);
        kill_who_layout= (LinearLayout)findViewById(R.id.witch_kill_layout);
//        killLayout.setVisibility(View.GONE);
        kill_grid =(GridView)findViewById(R.id.gridview);
        adapter = new KillChooseAdapter(this,this,KillChooseAdapter.TYPE_WITCH);
        kill_grid.setAdapter(adapter);

        helpTitle = (TextView)findViewById(R.id.tv_help_title);
        killTitle = (TextView)findViewById(R.id.tv_kill_title);
        if(helpNum != -1){
//            Html.fromHtml(String.format(getString(R.string.help_tile),helpNum));
            helpTitle.setText(Html.fromHtml(String.format(getString(R.string.help_tile),helpNum +1)));
            killLayout.setVisibility(View.GONE);
        }else {
            //平安夜
            Log.i("zz","狼人没杀人");
            helpTitle.setText(getString(R.string.nobody_kill));
            helpLayout.setVisibility(View.GONE);
            killLayout.setVisibility(View.VISIBLE);
        }
    }
    public void OnClick(View v){
        switch (v.getId()){
            case R.id.bt_help_yes:
                killLayout.setVisibility(View.GONE);
                helpNo.setClickable(false);
                helpNum = -1;
                next();
                break;
            case R.id.bt_help_no:
                killLayout.setVisibility(View.VISIBLE);
                helpYes.setClickable(false);
                break;
            case R.id.bt_kill_yes:
                helpLayout.setVisibility(View.GONE);
                killLayout.setVisibility(View.GONE);
                killTitle.setText(getString(R.string.kill_who));
                showKillTab();
                break;
            case R.id.bt_kill_no:
                helpLayout.setVisibility(View.GONE);
                killLayout.setVisibility(View.GONE);
                next();
                break;
        }
    }
    private void next(){
        playMusic(5);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WitchActivity.this,SeerActivity.class);
                intent.putExtra("helpNum",helpNum);
                intent.putExtra("killNum",witchKillNum);
                startActivity(intent);
            }
        }, Utils.TIME_DELAY);
    }
    private void showKillTab(){
        kill_who_layout.setVisibility(View.VISIBLE);
    }

    @Override
    public void kill(int killNum) {
        witchKillNum = killNum;
        next();
    }

    @Override
    public void timeout() {
        super.timeout();
        next();
    }
}
