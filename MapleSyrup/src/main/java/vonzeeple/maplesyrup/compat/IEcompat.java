package vonzeeple.maplesyrup.compat;

import blusunrize.immersiveengineering.api.crafting.BottlingMachineRecipe;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import vonzeeple.maplesyrup.common.Content;

public class IEcompat {

	
	
	public static void preInit()
	{
		
	}
	public static void init()
	{
		BottlingMachineRecipe.addRecipe(new ItemStack(Content.itemMapleSyrupBottle,1), new ItemStack(Items.GLASS_BOTTLE,1), new FluidStack(Content.fluidMapleSyrup,250));
		
	}
	public static void postInit()
	{
		
	}
}
