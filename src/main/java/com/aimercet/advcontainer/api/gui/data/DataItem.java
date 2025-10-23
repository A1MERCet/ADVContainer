package com.aimercet.advcontainer.api.gui.data;

public class DataItem
{
    public String id;
    public String name;
    public String image;
    public long color;
    public long amount;

    public String getID() {return id;}
    public DataItem setID(String id) {this.id = id;return this;}
    public String getName() {return name;}
    public DataItem setName(String name) {this.name = name;return this;}
    public String getImage() {return image;}
    public DataItem setImage(String image) {this.image = image;return this;}
    public long getColor() {return color;}
    public DataItem setColor(long color) {this.color = color;return this;}
    public long getAmount() {return amount;}
    public DataItem setAmount(long amount) {this.amount = amount;return this;}

    @Override
    public String toString() {
        return getClass().getSimpleName()+"[{]" +
                "id='" + id + '\'' +
                ", amount=" + amount +
                ']';
    }
}
