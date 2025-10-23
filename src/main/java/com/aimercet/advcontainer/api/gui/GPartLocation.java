package com.aimercet.advcontainer.api.gui;

public class GPartLocation {
    int x, y, w, h;

    public GPartLocation() {}

    public GPartLocation(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public GPartLocation setLocation(int x, int y) {setX(x);setY(y);return this;}
    public GPartLocation setWidth(int x, int y) {setW(x);setH(y);return this;}
    public int getX() {return x;}
    public GPartLocation setX(int x) {this.x = x;return this;}
    public int getY() {return y;}
    public GPartLocation setY(int y) {this.y = y;return this;}
    public int getW() {return w;}
    public GPartLocation setW(int w) {this.w = w;return this;}
    public int getH() {return h;}
    public GPartLocation setH(int h) {this.h = h;return this;}
}
