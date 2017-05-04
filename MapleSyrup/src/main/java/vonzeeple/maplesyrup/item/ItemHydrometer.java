package vonzeeple.maplesyrup.item;

import net.minecraft.item.Item;
import vonzeeple.maplesyrup.MapleSyrup;

public class ItemHydrometer extends Item{

	protected String name;
	
	public ItemHydrometer(String name){
		
		super();
		this.name = name;
		this.setCreativeTab(MapleSyrup.creativeTab);
		setUnlocalizedName(name);
		setRegistryName(name);		
	}
}
