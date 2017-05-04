package vonzeeple.maplesyrup.util;

import net.minecraft.block.BlockFurnace;
import net.minecraft.tileentity.TileEntityFurnace;

public class FurnaceAdapter {
	
	
	static boolean haveFuel(TileEntityFurnace tileEntity){
		return tileEntity.getStackInSlot(1)!=null;
	}

	public static void doHeatTick(TileEntityFurnace tileEntity){
		int burnTime = tileEntity.getField(0);
		if(burnTime<=0 && haveFuel(tileEntity)){
			tileEntity.decrStackSize(1, 1);
			//tileEntity.setField(0, TileEntityFurnace.getItemBurnTime(item));
			tileEntity.setField(0, tileEntity.getField(1));
			BlockFurnace.setState(true, tileEntity.getWorld(), tileEntity.getPos());
		}
	}

}
