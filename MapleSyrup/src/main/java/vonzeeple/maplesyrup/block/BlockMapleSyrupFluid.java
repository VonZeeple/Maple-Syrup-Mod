package vonzeeple.maplesyrup.block;

import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import vonzeeple.maplesyrup.MapleSyrup;

public class BlockMapleSyrupFluid extends BlockFluidClassic{
	
	public BlockMapleSyrupFluid(Fluid fluid, String name){
		super(fluid, Material.WATER);
		setCreativeTab(MapleSyrup.creativeTab);	
		setUnlocalizedName(name);
		setRegistryName(name);	
	}

}
