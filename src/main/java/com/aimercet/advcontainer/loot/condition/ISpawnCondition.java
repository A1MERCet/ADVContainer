package com.aimercet.advcontainer.loot.condition;

public interface ISpawnCondition
{
    default boolean check()
    {
        return true;
    }
}
