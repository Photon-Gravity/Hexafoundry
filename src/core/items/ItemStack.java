package core.items;

public class ItemStack {
    public ItemType type;

    public AlloyMix composition;
    public int amount;

    public ItemStack(ItemType type, AlloyMix composition, int amount){
        this.type = type;
        this.composition = composition;
        this.amount = amount;
    }

    public ItemStack(Item item, int amount){
        this.amount = amount;
        this.type = item.type;
        this.composition = item.composition;
    }
}
