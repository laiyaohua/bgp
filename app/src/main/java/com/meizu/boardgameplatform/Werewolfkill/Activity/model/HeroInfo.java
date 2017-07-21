package com.meizu.boardgameplatform.Werewolfkill.Activity.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/2/5.
 */
public class HeroInfo implements Serializable {
    int positive;
    int name;
    int imgId;
    int numMax;
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumMax() {
        return numMax;
    }

    public void setNumMax(int numMax) {
        this.numMax = numMax;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }


    public int getPositive() {
        return positive;
    }

    public void setPositive(int positive) {
        this.positive = positive;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }
}
