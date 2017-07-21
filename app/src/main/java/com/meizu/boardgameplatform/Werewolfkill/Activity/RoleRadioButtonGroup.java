package com.meizu.boardgameplatform.Werewolfkill.Activity;

import android.app.Dialog;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;


import com.meizu.boardgameplatform.R;
import com.meizu.boardgameplatform.Werewolfkill.Activity.View.DialogViewer;
import com.meizu.boardgameplatform.Werewolfkill.Activity.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/5/7 0007.
 */
public class RoleRadioButtonGroup extends LinearLayout {
    private List<Integer> list = new ArrayList<>();
    private Context mContext;
    private OnClickListener mButtomListener;
    private  RoleItemInterface roleItemInterface;
    private int mPosition;  //保存每个人选择的角色信息
    @BindArray(R.array.role_name) String[] roleNameArray;

    public RoleRadioButtonGroup(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public RoleRadioButtonGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {
        ButterKnife.bind(this);
        for(int i = 0; i < Utils.MAX_ROLE_TYPE; i++) {
            Log.d("daixw","addView i ="+i);
            Button radioButton = new Button(mContext);
            LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT-10 , LayoutParams.WRAP_CONTENT-10);
            layoutParams.setMargins(10, 10, 10, 10);
            layoutParams.weight = 1;
            radioButton.setLayoutParams(layoutParams);
            radioButton.setTextSize(16);
            radioButton.setTextColor(mContext.getResources().getColor(R.color.colorPrimaryDark));
//            radioButton.setBackgroundResource(R.drawable.bt_bg_style);
            radioButton.setGravity(Gravity.CENTER);
            addView(radioButton);
        }
    }

    public void setData(List<Integer> list){
        this.list = list;
        initButtonInfo();
    }

    public void setRoleItemInterface(RoleItemInterface roleItemInterface ){
        this.roleItemInterface = roleItemInterface;
    }
    public void setPosition( int position){
        mPosition = position;
    }

    public void initButtonInfo() {
        Log.d("daixw","initButtonInfo list.size() ="+list.size());
        for(int i = 0 ; i <list.size(); i++){
            Button radioButton = (Button)getChildAt(i);
            if(radioButton != null){
                radioButton.setText(roleNameArray[list.get(i)]);
                radioButton.setTag(list.get(i));
                Log.i("t","i ="+i+"tag = list.get(i) = "+list.get(i));
                radioButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int tag =(int)v.getTag();
                        Log.i("t","tag = "+tag);
                        String content = String.format(mContext.getString(R.string.role_comfirm),mPosition+1 , roleNameArray[tag]);
                        DialogViewer dialogViewer = new DialogViewer(mContext, content, new DialogListener() {
                            @Override
                            public void onDialogClick(Dialog dialog, boolean isLeftButonClick, boolean isRightButtonClick) {
                                if(isRightButtonClick){
                                    if(tag>=0 && list.size() >= tag){
                                        roleItemInterface.maxNumPlus(tag);
                                    }
                                    setVisibility(View.GONE);
                                    roleItemInterface.countPlus();
                                    roleItemInterface.refreshAdapter(mPosition);
                                }
                            }
                        });
                        dialogViewer.show();
                    }
                });
            }

        }

    }
}
