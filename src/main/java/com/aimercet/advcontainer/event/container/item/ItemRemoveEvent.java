package com.aimercet.advcontainer.event.container.item;

import com.aimercet.advcontainer.container.handler.RemoveResult;
import com.aimercet.advcontainer.event.container.ActionStage;
import com.aimercet.advcontainer.event.container.ContainerSlotActionEvent;

public class ItemRemoveEvent extends ContainerSlotActionEvent
{
    public final RemoveResult result;

    public ItemRemoveEvent(ActionStage stage, RemoveResult result)
    {
        super(stage);
        this.result = result;
    }
}
