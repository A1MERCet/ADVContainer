package com.aimercet.advcontainer.api.gui;

import com.aimercet.advcontainer.container.handler.ItemSource;

public class GUIActionState
{
    public class Cursor
    {
        private ItemSource source;
        private boolean rotate;

        public Cursor()
        {

        }

        public ItemSource getSource() {return source;}
        public Cursor setSource(ItemSource source) {this.source = source;return this;}
        public Cursor set(ItemSource source,boolean rotate) {setSource(source);setRotate(rotate);return this;}
        public boolean isRotate() {return rotate;}
        public Cursor setRotate(boolean rotate) {this.rotate = rotate;return this;}
        public Cursor clear(){
            setSource(null);
            setRotate(false);
            return this;
        }
    }

    private final Cursor cursor;

    public GUIActionState()
    {
        cursor = new Cursor();
    }

    public Cursor getCursor() {return cursor;}

}
