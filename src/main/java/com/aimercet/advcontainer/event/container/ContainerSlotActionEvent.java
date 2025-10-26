package com.aimercet.advcontainer.event.container;

public class ContainerSlotActionEvent extends ContainerEvent
{

    public final ActionStage stage;

    public ContainerSlotActionEvent(ActionStage stage)
    {
        this.stage = stage;
    }
}
