package vonzeeple.maplesyrup.client;

import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import vonzeeple.maplesyrup.MapleSyrup;
import vonzeeple.maplesyrup.client.render.RenderRegister;
import vonzeeple.maplesyrup.common.CommonProxy;

public class ClientProxy extends CommonProxy {

	 @Override
	   public void preInit(FMLPreInitializationEvent e) {
		 OBJLoader.INSTANCE.addDomain(MapleSyrup.MODID);
	       super.preInit(e);//Chaque methode appelle la methode de CommonProxy
	       RenderRegister.itemBlockRendering();
	   }

	   @Override
	   public void init(FMLInitializationEvent e) {
	       super.init(e);
	       RenderRegister.tileEntitySpecialRendererRegister();
	       
	       //RenderRegister.FluidRenderRegister();
	       //TileEntity render register
	       //RenderRegister.TileRenderRegister();
	       
	   }

	   @Override
	   public void postInit(FMLPostInitializationEvent e) {
	       super.postInit(e);
	   }



}
