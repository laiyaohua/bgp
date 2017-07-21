package com.meizu.boardgameplatform.Werewolfkill.Activity.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.meizu.boardgameplatform.R;
import com.meizu.boardgameplatform.Werewolfkill.Activity.IdentifyInterface;
import com.meizu.boardgameplatform.Werewolfkill.Activity.RoleItemInterface;
import com.meizu.boardgameplatform.Werewolfkill.Activity.RoleRadioButtonGroup;
import com.meizu.boardgameplatform.Werewolfkill.Activity.model.HeroInfo;
import com.meizu.boardgameplatform.Werewolfkill.Activity.model.RoleChooseItem;
import com.meizu.boardgameplatform.Werewolfkill.Activity.utils.Utils;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/25 0025.
 */
public class RoleChooseAdapter extends BaseAdapter implements RoleItemInterface {
    private Context context;
    private LayoutInflater mInflater;
    private ArrayList<RoleChooseItem> mRoleChooseItemList;  //随机打算的序列列表
//    private ArrayList<Integer> maxNumArray = new ArrayList<Integer>(){{add(0); add(0); add(0); add(0); add(0);}};
    public  int[] maxNum = new int[]{0,0,0,0,0};  //临时保存每个角色选择的数量
    private IdentifyInterface identifyInterface;

    private  boolean isChecked = false;

    public  int count = 0;

    public RoleChooseAdapter(Context context, IdentifyInterface identifyInterface, ArrayList<RoleChooseItem> roleChooseItemList) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        this.identifyInterface = identifyInterface;
        this.mRoleChooseItemList = roleChooseItemList;
    }

    @Override
    public int getCount() {
        return mRoleChooseItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return mRoleChooseItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        Log.d("daixw","getView position="+position);
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.role_item, null);
            viewHolder.mIcon = (ImageView)convertView.findViewById(R.id.img_has_choosed);
            viewHolder.numTv = (TextView) convertView.findViewById(R.id.tv_num);
            viewHolder.roleRadioButtonGroup = (RoleRadioButtonGroup)convertView.findViewById(R.id.layout_1);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.roleRadioButtonGroup.setPosition(position);

        viewHolder.numTv.setText(String.valueOf(mRoleChooseItemList.get(position).getId()));
        viewHolder.roleRadioButtonGroup.setData((mRoleChooseItemList.get(position).getRandomList()));
        viewHolder.roleRadioButtonGroup.setRoleItemInterface(this);
            //是否已选择
            if(mRoleChooseItemList.get(position).isChecked()){
                viewHolder.mIcon.setVisibility(View.VISIBLE);
                viewHolder.roleRadioButtonGroup.setVisibility(View.GONE);
            }else{
                viewHolder.mIcon.setVisibility(View.GONE);
                viewHolder.roleRadioButtonGroup.setVisibility(View.VISIBLE);
            }
        return convertView;
    }

    @Override
    public void refreshAdapter(int position) {
        mRoleChooseItemList.get(position).setChecked(true);
        notifyDataSetChanged();
    }

    @Override
    public void maxNumPlus(int tag) {
        maxNum[tag]++;
        HeroInfo heroInfo= new HeroInfo();
        heroInfo.setId(tag+1);
        heroInfo.setPositive(Utils.rolePositive[tag]);
        Utils.mHeroInfo.add(heroInfo);
    }

    @Override
    public void countPlus() {
        count++;
        if(count >= Utils.mPlayerNum){
            //核对身份
            identifyInterface.identifyRole(maxNum);
        }
    }

    class ViewHolder {
            public ImageView mIcon;
            public RoleRadioButtonGroup roleRadioButtonGroup;
            public TextView numTv;
        }
}
