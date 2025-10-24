package com.aimercet.advcontainer.api.gui.container;

import com.aimercet.advcontainer.container.IContainer;
import com.aimercet.advcontainer.player.modules.ModuleContainerState;
import com.aimercet.brlib.player.PlayerState;

import java.util.List;

public interface IPartContainer
{
    String getPartStyle();
    IContainer getContainer();
    PlayerState getPlayerState();
    List<IPartStock> getStockParts();


    /**
     * onEnable推荐在该gui组件渲染在玩家客户端上的时候调用(或者是拥有该组件的gui被玩家打开的时候调用) onDisable反之<br/>
     * 默认onEnable后给予玩家该容器的互动权限 onDisable移除互动权限<br/>
     */
    default void onEnable(){
        if(getPlayerState()==null || getContainer()==null)return;

        ModuleContainerState module = ModuleContainerState.get(getPlayerState());
        if(module==null)return;

        module.handleSource.getAllowedContainers().put(getContainer().getUUID(),getContainer());
    }
    default void onDisable()
    {
        if(getPlayerState()==null || getContainer()==null)return;

        ModuleContainerState module = ModuleContainerState.get(getPlayerState());
        if(module==null)return;

        module.handleSource.getAllowedContainers().remove(getContainer().getUUID());
    }
}
