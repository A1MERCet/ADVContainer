package com.aimercet.advcontainer.container.handler.source;

import com.aimercet.advcontainer.container.handler.HandleResult;

import java.util.ArrayList;
import java.util.List;

public class InventoryHandleHistory
{
    private final List<HandleResult> resultList          = new ArrayList<>();

    public InventoryHandleHistory()
    {
    }

    public List<HandleResult> getResultList()   {return resultList;}
    public void add(HandleResult result)        {resultList.add(result);}
}
