package vonzeeple.maplesyrup.tileentity;

import java.util.Random;

import jline.internal.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.TileFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vonzeeple.maplesyrup.api.EvaporationProcessesHandler;
import vonzeeple.maplesyrup.fluid.FluidTankEvaporator;
import vonzeeple.maplesyrup.util.FurnaceAdapter;

public class TileEntityEvaporator extends TileFluidHandler implements ITickable{

	public static final int CAPACITY = 20 * Fluid.BUCKET_VOLUME;

	
	
	public TileEntityEvaporator(){
		tank= new FluidTankEvaporator(CAPACITY);

	}
	
	public int getConcentration(){
		if(tank.getFluid()==null || tank.getFluidAmount()==0)
			return 0;
		if(!EvaporationProcessesHandler.canBeEvaporated(tank.getFluid().getFluid()))
			return 100;
		return (int)(getSugarContent()*100/tank.getFluidAmount()*1000/EvaporationProcessesHandler.getRatio(tank.getFluid().getFluid()));
	}
	
	public IBlockState getBlockState(){
		return this.getWorld().getBlockState(pos);
	}
	
	public int getSapLevel(){
		return tank.getFluidAmount();
	}

	public FluidStack getFluidStack(){
		return tank.getFluid();
	}
	
	public String getFluid(){
		if(tank.getFluid()!=null){
		return tank.getFluid().getLocalizedName();
		}
		else{
		return "null";
		}
	}
	
	public void update(){
		Random rnd = new Random();
		if(checkForFurnace()){
			FurnaceAdapter.doHeatTick((TileEntityFurnace)this.getWorld().getTileEntity(pos.down()));
		}
		
		if(!this.getWorld().isRemote){
		if(tank.getFluid()!=null)
		((FluidTankEvaporator) tank).evaporate();
		


			IBlockState oldState=this.getWorld().getBlockState(pos);
			this.getWorld().notifyBlockUpdate(pos, oldState, oldState, 3);
		}else{
			//if(((TileEntityFurnace)this.getWorld().getTileEntity(pos.down())).isBurning()){
			if(rnd.nextFloat()<0.25f){
				if(this.getWorld().isRemote)
				spawnParticles(this.getWorld(), pos, this.getWorld().getBlockState(pos));
			}
		//}
		}

	}
	
	private boolean checkForFurnace(){
		Block block=this.getWorld().getBlockState(pos.down()).getBlock();
		return( block == Blocks.FURNACE);
	}

	public float getSugarContent() {		
		return ((FluidTankEvaporator)tank).getSugarContent();
	}	
	
	public float getLevelRatio(){	
		return (float)tank.getFluidAmount()/(float)tank.getCapacity();
	}

    @SideOnly(Side.CLIENT)	
	private void spawnParticles(World worldIn, BlockPos pos, IBlockState state)
    {
       Random rand=new Random();
     for (int j = 0; j < 2; ++j)
     {
         double xpos = (double)pos.getX() + rand.nextDouble();
         double ypos = (double)pos.getY() + 1.5;
         double zpos = (double)pos.getZ() + rand.nextDouble();
         worldIn.spawnParticle(EnumParticleTypes.CLOUD, xpos, ypos, zpos, 0.0D, 0.0D, 0.0D, new int[0]);
     }
    
    }
    
	@Override
	public NBTTagCompound getUpdateTag() {
		return writeToNBT(new NBTTagCompound());
	}

	@Nullable
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(getPos(), 0, getUpdateTag());
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		readFromNBT(pkt.getNbtCompound());
}

}
