package vonzeeple.maplesyrup.tileentity;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.TileFluidHandler;
import vonzeeple.maplesyrup.util.MapleSyrupProperties;

public class TileEntityLargeEvaporator extends TileFluidHandler implements ITickable {

	public FluidTank[] tanks={new FluidTank(20000), new FluidTank(1000)};
	EnumFacing facing = EnumFacing.NORTH;
	private boolean dummy = false;
	private int offsetX=0;
	private int offsetY=0;
	private int offsetZ=0;
	
	public void readCustomNBT(NBTTagCompound nbt, boolean descPacket)
	{
		facing = EnumFacing.getFront(nbt.getInteger("facing"));
		dummy = nbt.getBoolean("dummy");
		offsetX = nbt.getInteger("dummyOffsetX");
		offsetY = nbt.getInteger("dummyOffsetY");
		offsetZ = nbt.getInteger("dummyOffsetZ");

	}
	public void writeCustomNBT(NBTTagCompound nbt, boolean descPacket)
	{
		nbt.setInteger("facing", facing.getIndex());
		nbt.setBoolean("dummy", dummy);
		nbt.setInteger("dummyOffsetX", offsetX);
		nbt.setInteger("dummyOffsetY", offsetY);
		nbt.setInteger("dummyOffsetZ", offsetZ);
}
	
	//Return the master Tile Entity
	public TileEntityLargeEvaporator getMaster(){
		if(offsetX==0 && offsetY==0 && offsetZ==0)
			return (TileEntityLargeEvaporator)this;
		TileEntity te = worldObj.getTileEntity(getPos().add(-offsetX,-offsetY,-offsetZ));
		return this.getClass().isInstance(te)?(TileEntityLargeEvaporator)te: null;
	}
	
	//Fluid handling
	protected IFluidTank[] getMasterFluidTank(){
		TileEntity master = getMaster();
		if(master!=null)
			return ((TileEntityLargeEvaporator)master).tanks;
		return new FluidTank[0];
	}
	
    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
    	if(dummy)
    		return false;
        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
    	if(dummy)
    		return null;
        return super.getCapability(capability, facing);
    }
	
	
	public int[] getRelativeOffset(){
	
		int[] vector={0,0,0};
		return vector;
	}
	
	public void placeDummies(BlockPos pos, IBlockState state, EnumFacing facing)
	{
		BlockPos[] poslist = {pos.offset(facing),pos.up(),pos.up().offset(facing)};

		if(this.dummy==true)
			return;
		for(BlockPos pos2:poslist){
			worldObj.setBlockState(pos2, state.withProperty(MapleSyrupProperties.MULTIBLOCKSLAVE, true));
			TileEntityLargeEvaporator teDummy = ((TileEntityLargeEvaporator)worldObj.getTileEntity(pos2));
			teDummy.facing = facing;
			teDummy.dummy = true;
			teDummy.offsetX=pos2.getX()-pos.getX();
			teDummy.offsetY=pos2.getY()-pos.getY();
			teDummy.offsetZ=pos2.getZ()-pos.getZ();
		}
	}
	
	public void breakDummies(BlockPos pos, IBlockState state)
	{
		EnumFacing facing=state.getValue(BlockHorizontal.FACING);
		BlockPos pos0=pos.add(-offsetX, -offsetY, -offsetZ);
		BlockPos[] poslist = {pos0,pos0.offset(facing),pos0.up(),pos0.up().offset(facing)};
		for(BlockPos pos2:poslist){
		worldObj.setBlockToAir(pos2);}
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
