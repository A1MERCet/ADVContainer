package com.aimercet.advcontainer.util;

public class SizeInt
{
    public final int width;
    public final int height;

    public SizeInt(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    public int size(){return width*height;}

    public SizeInt swap() {return new SizeInt(this.height, this.width);}

    @Override public String toString() {return "SizeInt[" + "w=" + width + ", h=" + height + ']';}

    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof SizeInt){
            SizeInt o = (SizeInt) obj;
            return o.width==width && o.height==height;
        }else {
            return super.equals(obj);
        }
    }
}
