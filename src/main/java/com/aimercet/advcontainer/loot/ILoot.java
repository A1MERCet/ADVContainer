package com.aimercet.advcontainer.loot;

import com.aimercet.advcontainer.loot.item.ILootTable;
import com.aimercet.advcontainer.loot.trigger.ILootTrigger;

public interface ILoot
{
    String getLootID();
    String getLootLang();

    ILootTable getLootTable();

    default void refresh(ILootTrigger trigger, ILootState lootState)
    {
        lootState.onRefresh(trigger,this);
    }
    /**
     * -1为不刷新
     */
    long getRefreshTime();
    /**
     * 是否清理之前生成的旧物品<br/>
     */
    boolean isClearUp();
}
