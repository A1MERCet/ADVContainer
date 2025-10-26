package com.aimercet.advcontainer.event.container.item;

import com.aimercet.advcontainer.container.handler.PlaceResult;
import com.aimercet.advcontainer.event.container.ActionStage;
import com.aimercet.advcontainer.event.container.ContainerSlotActionEvent;

public class ItemPlaceEvent extends ContainerSlotActionEvent
{
    public final PlaceResult result;

    public ItemPlaceEvent(ActionStage stage, PlaceResult result)
    {
        super(stage);
        this.result = result;
    }
}
