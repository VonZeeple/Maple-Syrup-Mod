package vonzeeple.maplesyrup.api;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraftforge.fluids.Fluid;
import vonzeeple.maplesyrup.util.Logger;

public class TappableBlocksHandler {

	static final HashMap<String, String> tappableBlocks = new HashMap<String, String>();

	
	public static void registerBlock(Block block, Fluid sap)
	{
		if(block != null && sap != null )
			tappableBlocks.put(block.getUnlocalizedName(), sap.getName());
		Logger.info("Tappable block "+ block.getUnlocalizedName()+" with sap "+ sap.getName()+ " added");
	}	

	public static boolean isTappableBlock(Block block){
		if(block != null)
			return tappableBlocks.containsKey(block.getUnlocalizedName());
			return false;
	}
	

	public static String getTappableSap(Block block)
	{
		if(block != null)
		{
			String s = block.getUnlocalizedName();
			if(tappableBlocks.containsKey(s))
				return tappableBlocks.get(s);
		}
		return null;
}



}
