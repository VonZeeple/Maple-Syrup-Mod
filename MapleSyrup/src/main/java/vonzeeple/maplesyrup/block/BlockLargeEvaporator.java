package vonzeeple.maplesyrup.block;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import vonzeeple.maplesyrup.MapleSyrup;
import vonzeeple.maplesyrup.tileentity.TileEntityLargeEvaporator;
import vonzeeple.maplesyrup.util.MapleSyrupProperties;

public class BlockLargeEvaporator extends BlockHorizontal implements ITileEntityProvider {
	
	protected String name;
	public static final PropertyBool MULTIBLOCKSLAVE = MapleSyrupProperties.MULTIBLOCKSLAVE;
	
	public BlockLargeEvaporator(String name) {
    	super(Material.ROCK);
    	this.setCreativeTab(MapleSyrup.creativeTab);
		this.name=name;
		setUnlocalizedName(name);
		setRegistryName(name);	

	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state){
		
		TileEntity te=worldIn.getTileEntity(pos);
		if(te!=null)
			((TileEntityLargeEvaporator)te).breakDummies(pos, state);
		super.breakBlock(worldIn, pos, state);
	}
 	
	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
	    EnumFacing enumfacing = (placer == null) ? EnumFacing.NORTH : EnumFacing.fromAngle(placer.rotationYaw);

	    return this.getDefaultState().withProperty(FACING, enumfacing).withProperty(MULTIBLOCKSLAVE, false);
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos,IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
	    TileEntity tile = worldIn.getTileEntity(pos);
	    if(tile!=null)
	    	((TileEntityLargeEvaporator)tile).placeDummies(pos, worldIn.getBlockState(pos), state.getValue(FACING));
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
    }	
	
	
	public boolean canBlockBePlaced(World world, BlockPos pos, IBlockState newState, EnumFacing side, float hitX, float hitY, float hitZ, EntityPlayer player, ItemStack stack){
		
		EnumFacing enumfacing = (player == null) ? EnumFacing.NORTH : EnumFacing.fromAngle(player.rotationYaw);
		BlockPos[] poslist = {pos,pos.offset(enumfacing),pos.up(),pos.up().offset(enumfacing)};
		
		for (BlockPos pos2 : poslist){
			if(!world.getBlockState(pos2).getBlock().isReplaceable(world, pos2))
				return false;
		}

		return true;
	}
	
	
	
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING,MULTIBLOCKSLAVE});
    }
    
	/**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
    	boolean isSlave=false;
    	if(meta>5){
    		isSlave=true;
    		meta-=6;}
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing).withProperty(MULTIBLOCKSLAVE, isSlave);
    }
    
    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
		int integer=0;
    	if(state.getValue(MULTIBLOCKSLAVE))
    		integer=6;

        return ((EnumFacing)state.getValue(FACING)).getIndex()+integer;
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
		return new TileEntityLargeEvaporator();
	}


}
