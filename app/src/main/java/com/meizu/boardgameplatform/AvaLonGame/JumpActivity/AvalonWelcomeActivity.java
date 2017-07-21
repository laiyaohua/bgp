package com.meizu.boardgameplatform.AvaLonGame.JumpActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.meizu.boardgameplatform.AvaLonGame.Utils.CommonTools;
import com.meizu.boardgameplatform.AvaLonGame.Utils.HeroListUtils;
import com.meizu.boardgameplatform.AvaLonGame.Utils.SoundUtils;
import com.meizu.boardgameplatform.AvaLonGame.model.HeroInfo;
import com.meizu.boardgameplatform.R;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Created by Administrator on 2016/2/5.
 */
public class AvalonWelcomeActivity extends AppCompatActivity {
    public static String SUB_TAG = "AvalonWelcomeActivity";
    private ImageView player_count;
    private SeekBar mSbHumNum;
    private Switch mSwMethod;
    private GridView mGridView;
    private HeroListAdapter mHeroListAdapter;
    private ArrayList<HeroInfo> mHeroDataList = new ArrayList<HeroInfo>();
    private TextView mTvHumNum;
    private int mCurrentHumNum;
    private boolean mHasModer;
    private ImageButton mButton;
    private View mLlmethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(SUB_TAG, "AvalonWelcomeActivity onCreate");
        setContentView(R.layout.avalon_home);
        init();
        initView();
    }

    private void initMusic() {
        // 通过 load 方法加载指定音频流，并将返回的音频 ID 放入 musicId 中
        SoundUtils.musicId.put(1, SoundUtils.mSoundPool.load(this, R.raw.win, 1));
        SoundUtils.musicId.put(2, SoundUtils.mSoundPool.load(this, R.raw.fail, 1));
        SoundUtils.musicId.put(3, SoundUtils.mSoundPool.load(this, R.raw.volt, 1));
    }

    private void init() {
        resumeData();
        initMusic();
    }

    private void resumeData() {
        mHasModer = CommonTools.getBooleanSp(getApplication(), CommonTools.HAS_MODER);
        mCurrentHumNum = CommonTools.getIntSp(getApplication(), CommonTools.HUM_NUM);
        if (mCurrentHumNum < 5) {
            mCurrentHumNum = 5;
        }

    }

    private void saveData() {
        CommonTools.setBooleanSp(getApplication(), CommonTools.HAS_MODER, mHasModer);
        CommonTools.setIntSp(getApplication(), CommonTools.HUM_NUM, mCurrentHumNum);
    }

    private void initView() {
        mLlmethod = findViewById(R.id.ll_method);
        player_count = (ImageView)findViewById(R.id.people_num_pic);
        mSbHumNum = (SeekBar) findViewById(R.id.sb_hum_num);

//        mTvHumNum = (TextView) findViewById(R.id.tv_hum_num);
        mSwMethod = (Switch) findViewById(R.id.sw_method);
        mButton = (ImageButton) findViewById(R.id.btn_start);
        mGridView = (GridView) findViewById(R.id.gv_hero_choose);

        mHeroDataList = HeroListUtils.getHeroIdList(mCurrentHumNum, false);

//        mTvHumNum.setText(String.valueOf(mCurrentHumNum));
//        mSbHumNum.setProgress(mCurrentHumNum - 5);
        mSwMethod.setChecked(mHasModer);

        if (mCurrentHumNum < 8) {
            mLlmethod.setVisibility(View.INVISIBLE);
        }

        mHeroListAdapter = new HeroListAdapter(this, mHeroDataList);
        mGridView.setAdapter(mHeroListAdapter);

        mSwMethod.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mHasModer = b;
                updateUi();
            }
        });

        mSbHumNum.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mCurrentHumNum = i + 5;
//                mTvHumNum.setText(String.valueOf(mCurrentHumNum));
                switch (mCurrentHumNum){
                    case 5:
                        player_count.setImageResource(R.mipmap.home_5);
                        break;
                    case 6:
                        player_count.setImageResource(R.mipmap.home_6);
                        break;
                    case 7:
                        player_count.setImageResource(R.mipmap.home_7);
                        break;
                    case 8:
                        player_count.setImageResource(R.mipmap.home_8);
                        break;
                    case 9:
                        player_count.setImageResource(R.mipmap.home_9);
                        break;
                    case 10:
                        player_count.setImageResource(R.mipmap.home_10);
                        break;
                }
                if (mCurrentHumNum <= 8) {
                    mLlmethod.setVisibility(View.INVISIBLE);
                } else {
                    mLlmethod.setVisibility(View.VISIBLE);
                }
                updateUi();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
                Intent intent = new Intent(AvalonWelcomeActivity.this, HeroChooseActivity.class);
                intent.putExtra("heroList", (Serializable) mHeroDataList);
                startActivity(intent);
            }
        });

    }

    private void updateUi() {
        mHeroDataList = HeroListUtils.getHeroIdList(mCurrentHumNum, mHasModer);
        mHeroListAdapter.changeData(mHeroDataList);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        saveData();
    }

}
