package com.aimercet.advcontainer.container.handler;


public class TransferResult
{
    public final RemoveResult removeResult;
    public final PlaceResult placeResult;

    public TransferResult(RemoveResult removeResult, PlaceResult placeResult) {
        this.removeResult = removeResult;
        this.placeResult = placeResult;
    }

    @Override public String toString() {return getClass().getSimpleName()+"\n:"+(removeResult==null?"null":removeResult.toString())+"\n:"+(placeResult==null?"null":placeResult.toString());}
}
