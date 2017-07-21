package com.meizu.boardgameplatform.Werewolfkill.Activity.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.meizu.boardgameplatform.R;
import com.meizu.boardgameplatform.Werewolfkill.Activity.Adapter.RoleChooseAdapter;
import com.meizu.boardgameplatform.Werewolfkill.Activity.DialogListener;
import com.meizu.boardgameplatform.Werewolfkill.Activity.IdentifyInterface;
import com.meizu.boardgameplatform.Werewolfkill.Activity.View.DialogViewer;
import com.meizu.boardgameplatform.Werewolfkill.Activity.model.HeroInfo;
import com.meizu.boardgameplatform.Werewolfkill.Activity.model.RoleChooseItem;
import com.meizu.boardgameplatform.Werewolfkill.Activity.randomUtil;
import com.meizu.boardgameplatform.Werewolfkill.Activity.utils.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RoleChooseActivity extends AppCompatActivity implements IdentifyInterface {
    
    private ArrayList<Integer> maxNumArray = new ArrayList<Integer>();
    private ListView roleList;
    private RoleChooseAdapter adapter;
    @BindView(R.id.bt_commit) Button btCommit;
    private int[] tempArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("daixw","onCreate" );
        setContentView(R.layout.activity_role_choose);
        init();
        initView();
    }

    //初始化角色数据

    private void init()
    {
        initArrayList();
    }

    private void initArrayList(){
        Log.d("daixw","initArrayList" );
        switch (Utils.mPlayerNum){
            case 8:
                maxNumArray.add(0,3);
                maxNumArray.add(1,2);
                break;
            case 9:
            case 10:
                maxNumArray.add(0,3);
                maxNumArray.add(1,Utils.mPlayerNum - 6);
                break;
            case 11:
            case 12:
                maxNumArray.add(0,4);
                maxNumArray.add(1,Utils.mPlayerNum - 7);
                break;
            default:
                maxNumArray.add(0,3);
                maxNumArray.add(1,Utils.mPlayerNum - 6);
                break;

        }
        maxNumArray.add(2,1);
        maxNumArray.add(3,1);
        maxNumArray.add(4,1);
    }


    //初始化界面组件


    private void initView(){
        ButterKnife.bind(this);
        Log.d("daixw","initView" );
        roleList = (ListView)findViewById(R.id.role_listview);
        adapter = new RoleChooseAdapter(this,this, createData());
//        adapter.setRoleList(heroInfoArrayList);
        roleList.setAdapter(adapter);
        Log.d("daixw","after setAdapter" );
    }

    private ArrayList<RoleChooseItem> createData() {
        ArrayList<RoleChooseItem> arrayList = new ArrayList<>();
        Log.d("daixw"," Utils.mPlayerNum ="+ Utils.mPlayerNum);
        for(int i = 0 ;i < Utils.mPlayerNum; i++) {
            RoleChooseItem roleChooseItem = new RoleChooseItem();
            roleChooseItem.setId( i + 1);
            roleChooseItem.setChecked(false);
            roleChooseItem.setRandomList(createRandomList());
            arrayList.add(roleChooseItem);
        }
        Log.d("daixw","arrayList ="+arrayList.size());
        return arrayList;
    }

    private ArrayList<Integer> createRandomList(){
        ArrayList<Integer> roleTypeList = new ArrayList<>();
        for(int i=0;i<Utils.MAX_ROLE_TYPE;i++){
            roleTypeList.add(i);
        }
        return  new randomUtil().randomList(roleTypeList);
    }

    private void checkResult (){
        for(int i :tempArray){
            Log.i("t"," i ="+i);
        }
        for(int j :maxNumArray){
            Log.i("t"," j ="+j);
        }
        for(int i=0;i< Utils.MAX_ROLE_TYPE;i++){
            if(maxNumArray.get(i) != tempArray[i]){
                Utils.mHeroInfo.clear();
                showDailog();
                break;
            }else if(i == Utils.MAX_ROLE_TYPE -1){
                //TODO
                for(HeroInfo heroInfo:Utils.mHeroInfo){
                    Log.i("zz","身份是："+heroInfo.getPositive());
                }
                DialogViewer dialogViewer = new DialogViewer(this, getString(R.string.role_confirm_success), new DialogListener() {
                    @Override
                    public void onDialogClick(Dialog dialog, boolean isLeftButonClick, boolean isRightButtonClick) {
                        if(isRightButtonClick){
                            Intent intent = new Intent(RoleChooseActivity.this,WerewolfActivity.class);
//                            intent.putExtra("RoleInfo", (Serializable) roleList);
                            startActivity(intent);
                        }
                    }
                });
                dialogViewer.show();
            }
        }
    }


    @Override
    public void identifyRole(int[] a) {
        btCommit.setVisibility(View.VISIBLE);
        roleList.setVisibility(View.GONE);
        tempArray = a;
    }



    private void showDailog(){
        DialogViewer dialogViewer = new DialogViewer(this, getString(R.string.confirm_again), R.style.menuTextStyle,null,"确定",new DialogListener() {
            @Override
            public void onDialogClick(Dialog dialog, boolean isLeftButonClick, boolean isRightButtonClick) {
                if(isRightButtonClick){
                    btCommit.setVisibility(View.GONE);
                    roleList.setVisibility(View.VISIBLE);
                    adapter = new RoleChooseAdapter(RoleChooseActivity.this,RoleChooseActivity.this, createData());
                    roleList.setAdapter(adapter);
                }
            }
        });
        dialogViewer.show();
    }
    @OnClick(R.id.bt_commit)void commint(){
        checkResult();
    }

}
