package vonzeeple.maplesyrup.client.render;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import vonzeeple.maplesyrup.MapleSyrup;
import vonzeeple.maplesyrup.common.Content;
import vonzeeple.maplesyrup.tileentity.TESREvaporator;
import vonzeeple.maplesyrup.tileentity.TileEntityEvaporator;

public class RenderRegister {
	
	public static void tileEntitySpecialRendererRegister(){
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEvaporator.class, new TESREvaporator());
	}
	
	public static void itemBlockRendering(){	
		//ATTENTION getItemModelMesher ne fonctionne que en init et le modelLoader de Forge en preinit!!!
		
		ModelLoader.setCustomModelResourceLocation(Content.itemMapleLog, 0, new ModelResourceLocation(MapleSyrup.MODID + ":maple_log", "inventory"));	
		ModelLoader.setCustomModelResourceLocation(Content.itemMapleSapling, 0, new ModelResourceLocation(MapleSyrup.MODID + ":maple_sapling", "inventory"));
		
		//for(int meta=0; meta<16; meta++){
		ModelLoader.setCustomModelResourceLocation(Content.itemMapleLeaves, 0, new ModelResourceLocation(MapleSyrup.MODID + ":maple_leaves", "inventory"));
		//}
		
		
		ModelLoader.setCustomModelResourceLocation(Content.itemHydrometer, 0, new ModelResourceLocation(MapleSyrup.MODID + ":item_hydrometer", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Content.itemMapleSyrupBottle, 0, new ModelResourceLocation(MapleSyrup.MODID + ":bottle_maplesyrup", "inventory"));
		
		
		ModelLoader.setCustomModelResourceLocation(Content.itemTreeTap, 0, new ModelResourceLocation(MapleSyrup.MODID+":tree_tap", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Content.itemEvaporator, 0, new ModelResourceLocation(MapleSyrup.MODID+":evaporator", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Content.itemLargeEvaporator, 0, new ModelResourceLocation(MapleSyrup.MODID+":large_evaporator", "inventory"));
		
        // use a custom state mapper which will ignore the LEVEL property
        ModelLoader.setCustomStateMapper(Content.blockFluidMapleSyrup, new StateMapperBase()
        {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state)
            {
                return new ModelResourceLocation(MapleSyrup.MODID.toLowerCase() + ":fluids", "maple_syrup_fluid");
            }
        });
        ModelLoader.setCustomStateMapper(Content.blockFluidMapleSap, new StateMapperBase()
        {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state)
            {
                return new ModelResourceLocation(MapleSyrup.MODID.toLowerCase() + ":fluids", "maple_sap_fluid");
            }
        });
        
     // use a custom state mapper which will ignore the DECAY property of leaves
        ModelLoader.setCustomStateMapper(Content.mapleLeaves, new StateMapperBase()
        {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state)
            {
                return new ModelResourceLocation(MapleSyrup.MODID.toLowerCase() + ":maple_leaves", "normal");
            }
        });
      

	}




}
