package vonzeeple.maplesyrup.block;

import jline.internal.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import vonzeeple.maplesyrup.MapleSyrup;
import vonzeeple.maplesyrup.common.Content;
import vonzeeple.maplesyrup.tileentity.TileEntityEvaporator;
import vonzeeple.maplesyrup.util.Logger;

public class BlockEvaporator extends Block implements ITileEntityProvider {

	protected String name;
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	
	public BlockEvaporator(String name) {
		super(Material.IRON);
    	this.setCreativeTab(MapleSyrup.creativeTab);
		this.name=name;
		setUnlocalizedName(name);
		setRegistryName(name);	
	}
	@Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
	@Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {

		return new TileEntityEvaporator();
	}
    /**
     * Called by ItemBlocks just before a block is actually set in the world, to allow for adjustments to the
     * IBlockstate
     */
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
	    // find the quadrant the player is facing
	    EnumFacing enumfacing = (placer == null) ? EnumFacing.NORTH : EnumFacing.fromAngle(placer.rotationYaw);
	    return this.getDefaultState().withProperty(FACING, enumfacing);
        
    	/*
    	if (facing.getAxis().isHorizontal() )
        {
            return this.getDefaultState().withProperty(FACING, facing);
        }
        else
        {

            return this.getDefaultState();
        }
        */
    }
    
    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
    }
    
    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return ((EnumFacing)state.getValue(FACING)).getIndex();
    }
    
    
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }
	
 	@Override
 	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
 		if(worldIn.isRemote && heldItem.getItem() == Content.itemHydrometer){
 		playerIn.addChatComponentMessage(new TextComponentString("Progress: "+((TileEntityEvaporator)worldIn.getTileEntity(pos)).getConcentration()+"%"));
 		}
 		
 		IFluidHandler fluidHandler=worldIn.getTileEntity(pos).getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side);
 		//Fill/drain with a bucket
 		FluidUtil.interactWithFluidHandler(heldItem, fluidHandler, playerIn);		
 		Logger.info("Sap level: "+((TileEntityEvaporator) worldIn.getTileEntity(pos)).getFluid()+" "+((TileEntityEvaporator) worldIn.getTileEntity(pos)).getSapLevel()+"mB");
 		Logger.info("SugarContent: "+((TileEntityEvaporator) worldIn.getTileEntity(pos)).getSugarContent()+" a.u.");
 		//FluidUtil.interactWithFluidHandler;
 		return true;
    }

}
