package com.meizu.boardgameplatform.Werewolfkill.Activity.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

import com.meizu.boardgameplatform.R;
import com.meizu.boardgameplatform.Werewolfkill.Activity.utils.SoundUtils;
import com.meizu.boardgameplatform.Werewolfkill.Activity.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class HomeActivity extends Activity {
    @BindView(R.id.sb_hum_num) SeekBar mSbHumNum;
    @BindView(R.id.tv_players) TextView tvPlayerNum;
    @BindView(R.id.tv_role_num) TextView tvRoleNumIntroduction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("daixw","onCreate");
        setContentView(R.layout.werewolfkill_home);
        init();
        initView();
    }


    private void init() {
        ButterKnife.bind(this);
        initMusic();
    }
    private void initMusic() {
        // 通过 load 方法加载指定音频流，并将返回的音频 ID 放入 musicId 中

        SoundUtils.musicId.put(1, SoundUtils.mSoundPool.load(this, R.raw.werewolf_1, 1));
        SoundUtils.musicId.put(2, SoundUtils.mSoundPool.load(this, R.raw.werewolf_2, 1));
        SoundUtils.musicId.put(3, SoundUtils.mSoundPool.load(this, R.raw.witch_1, 1));
        SoundUtils.musicId.put(4, SoundUtils.mSoundPool.load(this, R.raw.witch_2, 1));
        SoundUtils.musicId.put(5, SoundUtils.mSoundPool.load(this, R.raw.witch_3, 1));
        SoundUtils.musicId.put(6, SoundUtils.mSoundPool.load(this, R.raw.seer_1, 1));
        SoundUtils.musicId.put(7, SoundUtils.mSoundPool.load(this, R.raw.seer_2, 1));
        SoundUtils.musicId.put(8, SoundUtils.mSoundPool.load(this, R.raw.seer_3, 1));
    }

    public void initView(){
        ButterKnife.bind(this);
        tvRoleNumIntroduction.setText(String.format(getString(R.string.role_num_introduction),getWolfNum(), Utils.mPlayerNum-getWolfNum()));
        mSbHumNum.setProgress(Utils.mPlayerNum - Utils.MIN_PLAYER_NUM);

        mSbHumNum.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Utils.mPlayerNum = i + Utils.MIN_PLAYER_NUM;
                tvPlayerNum.setText(String.valueOf(Utils.mPlayerNum));
                tvRoleNumIntroduction.setText(String.format(getString(R.string.role_num_introduction),getWolfNum(),Utils.mPlayerNum-getWolfNum()-3));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

    }
    @OnClick(R.id.bt_conform) void commit(){
        Log.d("daixw"," onClick Utils.mPlayerNum ="+ Utils.mPlayerNum);
        Intent intent = new Intent(HomeActivity.this, RoleChooseActivity.class);
        startActivity(intent);
    }
    private int getWolfNum(){
        int wolfNum = 3;
        int num =  Utils.mPlayerNum;
        switch (num){
            case 8:
            case 9:
                wolfNum = 3;
                break;
            case 10:
            case 11:
                wolfNum = 4;
                break;
            case 12:
                wolfNum = 5;
                break;

        }
        return wolfNum;
    }
}
