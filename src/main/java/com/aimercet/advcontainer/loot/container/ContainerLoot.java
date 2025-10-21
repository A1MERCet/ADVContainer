package com.aimercet.advcontainer.loot.container;

import com.aimercet.advcontainer.container.ContainerManager;
import com.aimercet.advcontainer.container.ContainerTemplate;
import com.aimercet.advcontainer.item.ItemManager;
import com.aimercet.advcontainer.item.item.TypeItem;
import com.aimercet.advcontainer.loot.ILootState;
import com.aimercet.advcontainer.loot.item.ILootTable;
import com.aimercet.advcontainer.loot.trigger.ILootTrigger;
import com.aimercet.advcontainer.util.UtilItem;
import com.aimercet.brlib.localization.Localization;
import com.aimercet.brlib.log.Logger;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ContainerLoot implements IContainerLoot
{
    private final String lootID;
    private final String lootLang;
    private long refreshTime;
    private boolean clearup;
    private ILootTable lootTable;

    private ContainerTemplate containerTemplate;

    public ContainerLoot(String lootID)
    {
        this.lootID = lootID;
        this.lootLang = Localization.register("loot."+lootID,lootID);
    }

    @Override
    public void refresh(ILootTrigger trigger, ILootState lootState)
    {
        if(lootState instanceof IContainerLootState)
        {
            IContainerLootState ls = (IContainerLootState) lootState;
            if(ls.getContainer()!=null){
                List<ItemStack> list = lootTable.random(trigger);

                StringBuilder b =  new StringBuilder().append("\n容器物资["+lootID+"]生成物品["+list.size()+"]\n");
                list.forEach(i->{
                    ls.getContainer().getHandler().place(ContainerManager.instance.handleSourceSystem, UtilItem.toSlotItem(i),ls.getContainer());

                    TypeItem t = ItemManager.Get(i);
                    b.append("  "+(t==null?i.getType().name():Localization.get(t.fullLang))+"\n");
                });
                Logger.info(b.toString());
            }
        }

        IContainerLoot.super.refresh(trigger, lootState);
    }

    @Override public ILootTable getLootTable() {return lootTable;}
    @Override public ContainerTemplate getContainerTemplate() {return containerTemplate;}
    @Override public IContainerLootState createContainerLootState() {return new ContainerLootState(this);}
    @Override public String getLootID() {return lootID;}
    @Override public String getLootLang() {return lootLang;}
    @Override public long getRefreshTime() {return refreshTime;}
    @Override public boolean isClearUp() {return clearup;}
    public ContainerLoot setRefreshTime(long refreshTime) {this.refreshTime = refreshTime;return this;}
    public ContainerLoot setClearup(boolean clearup) {this.clearup = clearup;return this;}
    public ContainerLoot setLootTable(ILootTable lootTable) {this.lootTable = lootTable;return this;}
    public ContainerLoot setContainerTemplate(ContainerTemplate containerTemplate) {this.containerTemplate = containerTemplate;return this;}
}
