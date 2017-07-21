package com.meizu.boardgameplatform.Werewolfkill.Activity.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.meizu.boardgameplatform.R;
import com.meizu.boardgameplatform.Werewolfkill.Activity.DialogListener;
import com.meizu.boardgameplatform.Werewolfkill.Activity.KillInterface;
import com.meizu.boardgameplatform.Werewolfkill.Activity.View.DialogViewer;
import com.meizu.boardgameplatform.Werewolfkill.Activity.utils.Utils;


/**
 * Created by Administrator on 2017/4/25 0025.
 */
public class KillChooseAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater mInflater;
    private KillInterface killInterface;
    private View.OnClickListener onClickListener;
    private int Type;
    public static final int TYPE_WEREWOLF = 1;
    public static final int TYPE_WITCH = 2;
    public static final int TYPE_SEER = 3;

    public KillChooseAdapter(Context context, KillInterface killInterface,int Type) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        this.killInterface = killInterface;
        this.Type = Type;
    }



    @Override
    public int getCount() {
        return Utils.mPlayerNum;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
//      final  ViewHoder viewHoder;

            convertView = mInflater.inflate(R.layout.kill_choose_item, null);

            Button killButton = (Button)convertView.findViewById(R.id.kill_bt);

            killButton.setText(String.valueOf(position+1)+"号");

            killButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = null;
                switch (Type){
                    case TYPE_WEREWOLF:
                        content = String.format(context.getString(R.string.kill_comfirm), position+1);
                        break;
                    case TYPE_WITCH:
                        content = String.format(context.getString(R.string.witch_kill_comfirm), position+1);
                        break;
                    case TYPE_SEER:
                        content = String.format(context.getString(R.string.seer_comfirm), position+1);
                        break;
                }
                DialogViewer dialogViewer = new DialogViewer(context, content, new DialogListener() {
                    @Override
                    public void onDialogClick(Dialog dialog, boolean isLeftButonClick, boolean isRightButtonClick) {
                        if(isRightButtonClick){
                            killInterface.kill(position);
                        }
                    }
                });
                dialogViewer.show();
            }
        });

        return convertView;
    }

    private class ViewHoder {
        public Button killButton;
    }


}
