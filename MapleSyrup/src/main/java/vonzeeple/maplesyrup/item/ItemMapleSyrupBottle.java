package vonzeeple.maplesyrup.item;

import net.minecraft.item.Item;
import vonzeeple.maplesyrup.MapleSyrup;

public class ItemMapleSyrupBottle extends Item 
{

	protected String name;

	public ItemMapleSyrupBottle(String name) {
		super();
		setMaxStackSize(16);
		this.name = name;
		this.setCreativeTab(MapleSyrup.creativeTab);
		setUnlocalizedName(name);
		setRegistryName(name);
	}

	

}
