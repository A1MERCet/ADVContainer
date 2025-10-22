package com.aimercet.advcontainer.loot.Condition;

public interface ISpawnCondition
{
    default boolean check()
    {
        return true;
    }
}
