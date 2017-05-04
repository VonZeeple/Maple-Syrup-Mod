package vonzeeple.maplesyrup.fluid;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import vonzeeple.maplesyrup.api.EvaporationProcessesHandler;

public class FluidTankEvaporator extends FluidTank {
	
	protected float sugarContent;
	
	public FluidTankEvaporator(int capacity) {
		super(capacity);
		this.sugarContent=0;

	}
	
	@Override
    public boolean canFillFluidType(FluidStack fluid)
    {
        return EvaporationProcessesHandler.canBeEvaporated(fluid.getFluid());
    }
	
	@Override
	public FluidStack drain(int maxDrain, boolean doDrain){
		int amount=0;
		if(super.drain(maxDrain, false)!=null)
		amount=super.drain(maxDrain, false).amount;

		if(doDrain){
			sugarContent*=(1f-(float)amount/(float)getFluidAmount());
		}
		return super.drain(maxDrain, doDrain);
	}	
	
	@Override
	public FluidStack drain(FluidStack resource, boolean doDrain){
		int amount=super.drain(resource, false).amount;
		if(doDrain){
			sugarContent*=(1f-(float)amount/(float)getFluidAmount());
		}
		return super.drain(resource, doDrain);
	}

	
	@Override
    public int fill(FluidStack resource, boolean doFill)
    {
		int amount;
		if(resource==null)
			return 0;
		if(!EvaporationProcessesHandler.canBeEvaporated(resource.getFluid()))
			return 0;
	
		FluidStack fs= this.getFluid();
		
    //Dilution
	if(fs!=null){
		if(EvaporationProcessesHandler.getConcentratedFluid(resource.getFluid())== fs.getFluid().getName()){

			amount=fillInternal(new FluidStack(fs, resource.amount), false);
			int oldAmount=fs.amount;
			if(doFill){
			drainInternal(new FluidStack(fs,oldAmount),true);
			fillInternal(new FluidStack(resource,oldAmount),true);
			sugarContent+=(float)amount/1000f;
			return fillInternal(resource, true);}else{
			
			return fillInternal(new FluidStack(fs, resource.amount), false);}
			
		}}	
		
		//otherwise	
		if(doFill)
			sugarContent+=(float)fill(resource, false)/1000f;
		
		return fillInternal(resource, doFill);
    }
	
	
	//Evaporate: lower the liquid level but keep the same sugar content
	public void evaporate(){
		FluidStack fluid=this.getFluid();
		if(fluid==null)
			return;
		if(!EvaporationProcessesHandler.canBeEvaporated(fluid.getFluid()))
			return;
		
		//Test if the content can be transformed in the concentrated version
				if(sugarContent>=EvaporationProcessesHandler.getRatio(fluid.getFluid())*(float)this.getFluidAmount()/1000f){
					int fluidamount=getFluidAmount();	
					String conFluidname=EvaporationProcessesHandler.getConcentratedFluid(fluid.getFluid());
					FluidStack conFluid= FluidRegistry.getFluidStack(conFluidname, fluidamount);
					drainInternal(new FluidStack(fluid, fluidamount) ,true);	
					fillInternal(conFluid,true);
		        	fluid=this.getFluid();
					return;
				}
				

				drainInternal(new FluidStack(this.getFluid(), 20) ,true);		
					
	}
	
	@Override
    public FluidTank readFromNBT(NBTTagCompound nbt)
    {
        if (nbt.hasKey("sugarContent"))
        {
        	sugarContent = nbt.getFloat("sugarContent");
        }
        else
        {
            sugarContent = 0f;
        }
    	return super.readFromNBT(nbt);
    }

	@Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
    	nbt.setFloat("sugarContent", this.getSugarContent());
    	return super.writeToNBT(nbt);
    }
    

	public float getSugarContent() {
		return sugarContent;
	}

}
