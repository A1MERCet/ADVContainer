package com.aimercet.advcontainer.container.handler;


public class TransferResult
{
    public final RemoveResult removeResult;
    public final PlaceResult placeResult;

    public TransferResult(RemoveResult removeResult, PlaceResult placeResult) {
        this.removeResult = removeResult;
        this.placeResult = placeResult;
    }


}
