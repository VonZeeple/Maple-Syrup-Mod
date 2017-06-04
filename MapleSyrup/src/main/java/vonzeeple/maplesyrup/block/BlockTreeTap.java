package vonzeeple.maplesyrup.block;

import java.util.Random;

import jline.internal.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vonzeeple.maplesyrup.MapleSyrup;
import vonzeeple.maplesyrup.api.PropertiesHandler;
import vonzeeple.maplesyrup.tileentity.TileEntityTreeTap;
import vonzeeple.maplesyrup.util.Logger;

public class BlockTreeTap extends Block implements ITileEntityProvider {

		public String name;


		
	    public static final PropertyDirection FACING = BlockHorizontal.FACING;
	    public static final PropertyBool LIQUIDRENDER =  PropertiesHandler.LIQUIDRENDER;
	    
	    protected static final AxisAlignedBB TAP_EAST_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.25D, 0.5D, 0.75D, 0.75D);
	    protected static final AxisAlignedBB TAP_WEST_AABB = new AxisAlignedBB(0.5D, 0.0D, 0.25D, 1.0D, 0.75D, 0.75D);
	    protected static final AxisAlignedBB TAP_SOUTH_AABB = new AxisAlignedBB(0.25D, 0.0D, 0.0D, 0.75D, 0.75D, 0.5D);
	    protected static final AxisAlignedBB TAP_NORTH_AABB = new AxisAlignedBB(0.25D, 0.0D, 0.5D, 0.75D, 0.75D, 1.0D);

	    
	public BlockTreeTap(String name) {
		super(Material.SAND);
    	this.setCreativeTab(MapleSyrup.creativeTab);
		this.name=name;
		setUnlocalizedName(name);
		setRegistryName(name);
		this.setTickRandomly(true);
		this.setHardness(0.2f);
		this.setDefaultState(this.getDefaultState().withProperty(LIQUIDRENDER, false));

	}


	@Override
    public String getHarvestTool(IBlockState state)
    {
        return null;
    }
	
	
	//Give the bounding box according to the direction
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        switch ((EnumFacing)state.getValue(FACING))
        {
            case NORTH:
                return TAP_NORTH_AABB;
            case SOUTH:
                return TAP_SOUTH_AABB;
            case WEST:
                return TAP_WEST_AABB;
            case EAST:
            default:
                return TAP_EAST_AABB;
        }
    }
    
    //Can only be placed on a solid wall
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        return worldIn.getBlockState(pos.west()).isSideSolid(worldIn, pos.west(), EnumFacing.EAST) ||
               worldIn.getBlockState(pos.east()).isSideSolid(worldIn, pos.east(), EnumFacing.WEST) ||
               worldIn.getBlockState(pos.north()).isSideSolid(worldIn, pos.north(), EnumFacing.SOUTH) ||
               worldIn.getBlockState(pos.south()).isSideSolid(worldIn, pos.south(), EnumFacing.NORTH);
    }
    

    /**
     * Called by ItemBlocks just before a block is actually set in the world, to allow for adjustments to the
     * IBlockstate
     */
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        if (facing.getAxis().isHorizontal() && this.canBlockStay(worldIn, pos, facing))
        {
            return this.getDefaultState().withProperty(FACING, facing);
        }
        else
        {
            for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL)
            {
                if (this.canBlockStay(worldIn, pos, enumfacing))
                {
                    return this.getDefaultState().withProperty(FACING, enumfacing);
                }
            }

            return this.getDefaultState();
        }
    }
    
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn)
    {
        this.checkAndDropBlock(worldIn, pos, state);
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        this.checkAndDropBlock(worldIn, pos, state);
    }

    protected void checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!this.canBlockStay(worldIn, pos, state.getValue(FACING)))
        {
            this.dropBlockAsItem(worldIn, pos, state, 0);
            worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
        }
    }
    
    protected boolean canBlockStay(World worldIn, BlockPos pos, EnumFacing facing)
    {
        return worldIn.getBlockState(pos.offset(facing.getOpposite())).isSideSolid(worldIn, pos.offset(facing.getOpposite()), facing);
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
        return new BlockStateContainer(this, new IProperty[] {FACING,LIQUIDRENDER});
    }
	
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
	
	@SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
        return false;
    }
	

     @Override
     public boolean isVisuallyOpaque() { return false; }
     //---------------------------------------------
     //Liquid container:
     //---------------------------------------------
 	@Override
 	public TileEntity createNewTileEntity(World worldIn, int meta) {
 		return new TileEntityTreeTap();
 	}
 	
 	

 	@Override
 	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
 		IFluidHandler fluidHandler=worldIn.getTileEntity(pos).getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side);
 		//Fill/drain with a bucket
 		FluidUtil.interactWithFluidHandler(heldItem, fluidHandler, playerIn);
 		
 		
 		Logger.info("Sap level: "+((TileEntityTreeTap) worldIn.getTileEntity(pos)).getFluid()+" "+((TileEntityTreeTap) worldIn.getTileEntity(pos)).getSapLevel()+"mB");
 		return true;
    }
     



}
