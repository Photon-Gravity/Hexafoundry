package core.terrain.constrution.types;

import core.World;
import core.items.Item;
import core.items.ItemType;
import core.terrain.Axial;
import core.terrain.constrution.blocks.Block;

public class ReshaperType extends MultiBlockType{

    float speed = 0.2f/60f;
    int itemCap = 5;

    public ItemType ingredientType;
    public ItemType resultType;

    public Axial outputTarget;
    public ReshaperType(String name) {
        super(name);
    }

    @Override
    public void update(Block block) {
        super.update(block);

        if(working(block)){
            block.progress += speed;
            if(block.progress >= 1){
                Item toConvert = block.items.get(0);
                block.items.remove(0);
                block.items.add(convertItem(toConvert));
                block.progress = 0;
            }
        }

        Block target = World.blocks.get(block.pos.trns(outputTarget.rotate(block.rotation)));
        if (target != null) {
            int i = 0;
            while(i < block.items.size()){
                Item item = block.items.get(i);
                if(target.type.acceptsItem(item, block, target) && item.type != ingredientType){
                    block.items.remove(item);
                    target.type.handleItem(item, block, target);
                } else {
                    i++;
                }
            }
        }
    }


    @Override
    public boolean acceptsItem(Item item, Block source, Block target) {
        return item.type == ingredientType && target.items.size() < itemCap;
    }

    public boolean working(Block block){
        for(Item item : block.items){
            if(item.type == ingredientType){
                return true;
            }
        }
        return false;
    }

    public Item convertItem(Item item){
        if(item.type == ingredientType){
            return new Item(resultType, item.composition.cpy());
        } else {
            return item;
        }
    }
}
