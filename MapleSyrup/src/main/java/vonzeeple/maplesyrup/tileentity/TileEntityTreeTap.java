package vonzeeple.maplesyrup.tileentity;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.TileFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vonzeeple.maplesyrup.api.TappableBlocksHandler;
import vonzeeple.maplesyrup.client.particle.ParticleSap;

public class TileEntityTreeTap extends TileFluidHandler implements ITickable{// implements ITickable

	public static final int CAPACITY = 1 * Fluid.BUCKET_VOLUME;
	private int tickCounter=0;
	//private FluidTank tank;


	public TileEntityTreeTap() {
		tank.setCapacity(CAPACITY);
		tank.setCanFill(false);

	}

	public int getSapLevel(){
		return tank.getFluidAmount();
	}
	
	public String getFluidName(){
		if(tank.getFluid()!=null){
		return tank.getFluid().getLocalizedName();
		}
		else{
		return "null";
		}
	}
	public Fluid getFluid(){
		if(tank.getFluid()!=null){
		return tank.getFluid().getFluid();
		}
		else{
		return null;
		}

	}	
	
	
	
	public void update(){
		
		
	tickCounter++;	
	if(tank.getFluidAmount()<CAPACITY && tickCounter%80==0){
		
		if(this.checkTapping()!=null){
		
			//Only server side
			if(!this.getWorld().isRemote){
			tank.fillInternal(this.checkTapping(), true);
			this.markDirty();
			}
			

		
		//Only client side
		if(this.getWorld().isRemote){

			this.spawnParticles(this.getWorld(), pos,this.getWorld().getBlockState(pos)); 
		}
		tickCounter=1;
		}
		
	}
	
	}
	
    @SideOnly(Side.CLIENT)	
	private void spawnParticles(World worldIn, BlockPos pos, IBlockState state)
    {

   	 double x1;
   	 double z1;
   	 //Maybe check if the block is TreeTap
   	 
        switch ((EnumFacing)state.getValue(BlockHorizontal.FACING))
        {

            case NORTH:
           	 x1=0.5D;
           	 z1=0.8D;
           	 break;
            case SOUTH:
           	 x1=0.5D;
           	 z1=0.2D;
           	 break;
            case WEST:
           	 x1=0.8D;
           	 z1=0.5D;
           	 break;
            case EAST:
            default:
           	 x1=0.2D;
           	 z1=0.5D;
        }
   	 
   	 double xpos=pos.getX()+x1;
   	 double ypos=pos.getY()+0.55D;
   	 double zpos=pos.getZ()+z1;
       
        ParticleSap newEffect = new ParticleSap(worldIn, xpos, ypos, zpos);
        Minecraft.getMinecraft().effectRenderer.addEffect(newEffect);
        //worldIn.playSound((double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, SoundEvents.UI_BUTTON_CLICK, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
    
    }
    
    public FluidStack checkTapping(){
    	IBlockState state = this.getWorld().getBlockState(pos);

 		BlockPos pos2;
        switch ((EnumFacing)state.getValue(BlockHorizontal.FACING))
        {

            case NORTH:
            	pos2=pos.south();
           	 break;
            case SOUTH:
            	pos2=pos.north();
           	 break;
            case WEST:
            	pos2=pos.east();
           	 break;
            case EAST:
            default:
            	pos2=pos.west();
        }
        if (TappableBlocksHandler.isTappableBlock(this.getWorld().getBlockState(pos2))){
        	return TappableBlocksHandler.getTappableSap(this.getWorld().getBlockState(pos2));
        }
        else{
        	return null;
        }
        
 	}

}
