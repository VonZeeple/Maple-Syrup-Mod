package vonzeeple.maplesyrup.util;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

public class FurnaceAdapter {
	
	
	static boolean haveFuel(TileEntityFurnace tileEntity){
		return tileEntity.getStackInSlot(1)!=null;
	}
	
	//Add a dummy item in the top slot of the furnace, update the furnace and remove the dummy item
	public static void doHeatTick(TileEntityFurnace tileEntity){
		int burnTime = tileEntity.getField(0);
		if(burnTime<=0 && haveFuel(tileEntity) && tileEntity.getStackInSlot(0)==null){		
			tileEntity.setInventorySlotContents(0,new ItemStack(Blocks.COBBLESTONE,1));
			tileEntity.update();
			tileEntity.setInventorySlotContents(0,null);

		}
	}

}
