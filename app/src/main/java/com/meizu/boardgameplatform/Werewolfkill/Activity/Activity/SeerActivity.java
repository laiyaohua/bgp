package com.meizu.boardgameplatform.Werewolfkill.Activity.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.widget.GridView;

import com.meizu.boardgameplatform.R;
import com.meizu.boardgameplatform.Werewolfkill.Activity.Adapter.KillChooseAdapter;
import com.meizu.boardgameplatform.Werewolfkill.Activity.DialogListener;
import com.meizu.boardgameplatform.Werewolfkill.Activity.KillInterface;
import com.meizu.boardgameplatform.Werewolfkill.Activity.View.DialogViewer;
import com.meizu.boardgameplatform.Werewolfkill.Activity.model.HeroInfo;
import com.meizu.boardgameplatform.Werewolfkill.Activity.utils.SoundUtils;
import com.meizu.boardgameplatform.Werewolfkill.Activity.utils.Utils;


public class SeerActivity extends AppCompatActivity implements KillInterface {
    private GridView killGrid;
    private int helpNum,witchKillNum ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seer);
        init();
        initView();
        playMusic(6);
        playMusic(7);
    }
    private void init(){
        helpNum = getIntent().getIntExtra("helpNum",-1);
        witchKillNum = getIntent().getIntExtra("killNum",-1);
    }

    private void initView(){
        killGrid = (GridView)findViewById(R.id.gridview);
        KillChooseAdapter killChooseAdapter = new KillChooseAdapter(this,this,KillChooseAdapter.TYPE_SEER);
        killGrid.setAdapter(killChooseAdapter);

    }
    private void playMusic(int id) {
        SoundUtils.playSound(SoundUtils.mSoundPool,id);
    }

    @Override
    public void kill(int num) {
        //TODO
        // 验证身份

        Log.i("zz","seer Utils.mHeroInfo = "+ Utils.mHeroInfo);
        if(Utils.mHeroInfo != null && Utils.mHeroInfo.size()>0){
            HeroInfo heroInfo = Utils.mHeroInfo.get(num);
            int positive = heroInfo.getPositive();
            String identification = null;
            if(positive == -1){
                identification = "狼人";
            }else {
                identification = "好人";
            }

            String content = Html.fromHtml(String.format(getString(R.string.seer_page_result),num+1,identification)).toString();
            Log.i("zz","content = "+content+"identification = "+identification);
            DialogViewer dialogViewer = new DialogViewer(this, content, R.style.menuTextStyle,null,"确定",new DialogListener() {
                @Override
                public void onDialogClick(Dialog dialog, boolean isLeftButonClick, boolean isRightButtonClick) {
                    playMusic(8);
                    Intent intent = new Intent(SeerActivity.this,ResultActivity.class);
                    intent.putExtra("helpNum",helpNum);
                    intent.putExtra("killNum",witchKillNum);
                    startActivity(intent);
                }
            });
            dialogViewer.show();
        }

    }
}
