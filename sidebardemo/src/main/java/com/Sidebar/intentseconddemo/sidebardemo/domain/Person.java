package com.Sidebar.intentseconddemo.sidebardemo.domain;

import android.widget.ImageView;

/**
 * Created by Administrator on 2016/6/21.
 */
public class Person {
    /**
     * 用户名字
     */
    private String name;
    /**
     * 用户名字首字母
     */
    private String initial;
     /**
     * 用户头像
     */
    protected ImageView avatar ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public ImageView getAvatar() {
        return avatar;
    }

    public void setAvatar(ImageView avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "Person{" +
                "initial='" + initial + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
