package com.aimercet.advcontainer.container.handler.gui;

import com.aimercet.advcontainer.container.handler.SlotSource;
import com.sun.istack.internal.Nullable;

/**
 * 用于GUI交互(点击之类的)的结构类
 */
public class ActionState
{
    public enum Type
    {
        LCLICK(true),
        DUAL_LCLICK(true),
        RCLICK(true),
        DUAL_RCLICK(true),
        SHIFT_LCLICK(true),
        SHIFT_RCLICK(true),
        CTRL_LCLICK(true),
        CTRL_RCLICK(true),
        SHIFT_MCLICK(true),
        CTRL_MCLICK(true),
        ;

        public final boolean click;

        Type(boolean click) {
            this.click = click;
        }
    }

    public final Type type;
    @Nullable public final SlotSource source;
    public boolean rotate;

    public ActionState(Type type, SlotSource source, boolean rotate)
    {
        this(type,source);
        this.rotate = rotate;
    }

    public ActionState(Type type, SlotSource source)
    {
        this.type = type;
        this.source = source;
    }

    public ActionState(Type type)
    {
        this.type = type;
        this.source = null;
    }
}
