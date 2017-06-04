package vonzeeple.maplesyrup.api;

import java.util.HashMap;

import net.minecraft.block.state.IBlockState;
import net.minecraftforge.fluids.FluidStack;
import vonzeeple.maplesyrup.util.Logger;

public class TappableBlocksHandler {

	static final HashMap<IBlockState, FluidStack> tappableBlocks = new HashMap<IBlockState, FluidStack>();

	public static void registerBlock(IBlockState state, FluidStack sap)
	{
		if(state != null && sap != null )
			tappableBlocks.put(state, sap);
		Logger.info("Tappable block "+ state.getBlock().getUnlocalizedName()+" with sap "+ sap.getFluid().getName()+ " added");
	}	

	public static boolean isTappableBlock(IBlockState state){
		if(state != null)
			return tappableBlocks.containsKey(state);
			return false;
	}
	

	public static FluidStack getTappableSap(IBlockState state)
	{
		if(state != null)
		{
			if(tappableBlocks.containsKey(state))
				return tappableBlocks.get(state);
		}
		return null;
}



}
