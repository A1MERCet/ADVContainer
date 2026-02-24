package com.aimercet.advcontainer.api.gui;

import com.aimercet.advcontainer.api.gui.cursor.ICursorSource;

public class GUIActionState
{
    public class Cursor
    {
        private ICursorSource source;
        private boolean rotate;

        public Cursor()
        {

        }

        public ICursorSource getSource() {return source;}
        public Cursor setSource(ICursorSource source) {this.source = source;return this;}
        public Cursor set(ICursorSource source, boolean rotate) {setSource(source);setRotate(rotate);return this;}
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
