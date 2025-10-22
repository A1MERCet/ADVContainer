package com.aimercet.advcontainer.loot;

import com.aimercet.advcontainer.loot.Condition.ISpawnCondition;
import com.aimercet.advcontainer.loot.item.ILootTable;
import com.aimercet.advcontainer.loot.trigger.ILootTrigger;

import java.util.UUID;

public interface ILoot
{
    String getLootID();
    String getLootLang();
    ISpawnCondition getLootCondition();
    void setLootCondition(ISpawnCondition condition);

    ILootTable getLootTable();
    ILootState createLootState(String id);
    default ILootState createLootState(){return createLootState(UUID.randomUUID().toString());}

    /**
     * 当被触发刷新时执行<br/>
     * 例如容器loot被玩家打开或者地面loot附件有玩家靠近<br/>
     * 仅isAutoRefresh为false时<br/>
     */
    default void onRefreshTrigger(ILootTrigger trigger, ILootState lootState)
    {
        if(isAutoRefresh())return;
        if(lootState.getPrevRefreshTime()<=0 && lootState.getNextRefreshTime()>=System.currentTimeMillis())
            refresh(trigger,lootState);
    }

    default void refresh(ILootTrigger trigger, ILootState lootState)
    {
        lootState.onRefresh(trigger,this);
    }

    default boolean canRefresh(ILootTrigger trigger, ILootState lootState)
    {
        if(getRefreshTime()<=0L && lootState.getPrevRefreshTime()>0L)return false;
        if(lootState.getNextRefreshTime() < System.currentTimeMillis())return false;

        ISpawnCondition condition = getLootCondition();
        return condition != null && condition.check();
    }


    /**
     * 后台自动刷新物资而非需要玩家触发才重置刷新时间
     */
    boolean isAutoRefresh();
    /**
     * -1为不刷新
     */
    long getRefreshTime();
    /**
     * 是否清理之前生成的旧物品<br/>
     */
    boolean isClearUp();
}
