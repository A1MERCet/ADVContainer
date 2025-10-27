package com.aimercet.advcontainer.bridge.protocollib;

import com.aimercet.advcontainer.ADVContainer;
import com.aimercet.brlib.log.Logger;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ProtocolLibManager
{
    public static ProtocolLibManager instance;
    public final ProtocolManager pm;

    public ProtocolLibManager()
    {
        instance = this;
        pm = ProtocolLibrary.getProtocolManager();;

        init();
    }

    private ProtocolLibManager init()
    {
        pm.addPacketListener(
                new PacketAdapter(ADVContainer.instance, ListenerPriority.HIGH, PacketType.Play.Server.WINDOW_ITEMS)
                {
                    @Override
                    public void onPacketSending(PacketEvent e)
                    {
                        Player p = e.getPlayer();
                        PacketContainer pkt = e.getPacket();

                        // windowID 0 表示玩家主背包
                        if (pkt.getIntegers().read(0) != 0) return;

                        // 读取 ItemStack 列表（索引 1 是 1.12 的字段顺序）
                        List<ItemStack> items = pkt.getItemListModifier().read(0);
                        Logger.debug("WINDOW_ITEMS -> " + p.getName() + " 背包现在有 " + items.size() + " 格数据");
                        // 这里你可以把 items 和上一次缓存对比，找出新增格子
                    }
                });

        return this;
    }
}
