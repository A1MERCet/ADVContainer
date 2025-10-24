package com.aimercet.advcontainer.api.gui;

import org.bukkit.configuration.ConfigurationSection;

public class GPartStyle implements Cloneable {

    String style;
    int x, y, w, h;

    public GPartStyle() {}

    public GPartStyle(String style ,int x, int y, int w, int h) {
        this.style = style;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public void load(ConfigurationSection section)
    {
        if(section == null) return;
        style = section.getString("style");
        x = section.getInt("x");
        y = section.getInt("y");
        w = section.getInt("w");
        h = section.getInt("h");
    }

    @Override
    public GPartStyle clone(){
        try {
            GPartStyle clone = (GPartStyle)super.clone();

            clone.style = style;
            clone.x = x;
            clone.y = y;
            clone.w = w;
            clone.h = h;

            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public GPartStyle setLocation(int x, int y) {setX(x);setY(y);return this;}
    public GPartStyle setWidth(int x, int y) {setW(x);setH(y);return this;}
    public String getStyle() {return style;}
    public GPartStyle setStyle(String style) {this.style = style;return this;}
    public int getX() {return x;}
    public GPartStyle setX(int x) {this.x = x;return this;}
    public int getY() {return y;}
    public GPartStyle setY(int y) {this.y = y;return this;}
    public int getW() {return w;}
    public GPartStyle setW(int w) {this.w = w;return this;}
    public int getH() {return h;}
    public GPartStyle setH(int h) {this.h = h;return this;}
}
