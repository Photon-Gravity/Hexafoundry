package core.items;

public class Item {
    public ItemType type;
    public AlloyMix composition;

    public Item(ItemType type, AlloyMix composition){
        this.type = type;
        this.composition = composition;
    }
}
