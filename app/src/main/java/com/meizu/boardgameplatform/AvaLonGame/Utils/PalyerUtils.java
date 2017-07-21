package com.meizu.boardgameplatform.AvaLonGame.Utils;

import com.meizu.boardgameplatform.AvaLonGame.model.PlayerInfo;
import com.meizu.boardgameplatform.R;

import java.util.ArrayList;

/**
 * Created by laiyaohua on 2017-7-20.
 */

public class PalyerUtils {
    public static ArrayList<PlayerInfo> mPlayerDataList = new ArrayList<PlayerInfo>();

    public static PlayerInfo creatPlyer(int playerId) {
        PlayerInfo playerInfo = new PlayerInfo();
        switch (playerId) {
            case 5:
                playerInfo.setImgId(R.mipmap.home_5);
                break;
            case 6:
                playerInfo.setImgId(R.mipmap.home_6);
                break;
            case 7:
                playerInfo.setImgId(R.mipmap.home_7);
                break;
            case 8:
                playerInfo.setImgId(R.mipmap.home_8);
                break;
            case 9:
                playerInfo.setImgId(R.mipmap.home_8);
                break;
        }
        return playerInfo;
    }

    public static ArrayList<PlayerInfo> getPlayerIdList(int playerId) {
        ArrayList<PlayerInfo> playerInfoList = new ArrayList<>();
        switch (playerId) {
            case 5:

        }
        return playerInfoList;
    }
}

