package vonzeeple.maplesyrup.common;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import vonzeeple.maplesyrup.MapleSyrup;
import vonzeeple.maplesyrup.api.EvaporationProcessesHandler;
import vonzeeple.maplesyrup.api.TappableBlocksHandler;
import vonzeeple.maplesyrup.block.BlockEvaporator;
import vonzeeple.maplesyrup.block.BlockLargeEvaporator;
import vonzeeple.maplesyrup.block.BlockMapleLeaves;
import vonzeeple.maplesyrup.block.BlockMapleLog;
import vonzeeple.maplesyrup.block.BlockMapleSapFluid;
import vonzeeple.maplesyrup.block.BlockMapleSapling;
import vonzeeple.maplesyrup.block.BlockMapleSyrupFluid;
import vonzeeple.maplesyrup.block.BlockTreeTap;
import vonzeeple.maplesyrup.block.ItemBlockLargeEvaporator;
import vonzeeple.maplesyrup.item.ItemMapleSyrupBottle;
import vonzeeple.maplesyrup.item.ItemHydrometer;
import vonzeeple.maplesyrup.tileentity.TileEntityEvaporator;
import vonzeeple.maplesyrup.tileentity.TileEntityTreeTap;

public class Content {

	public static Block mapleSapling;	
	public static Block mapleLog;
	public static Item itemMapleSapling;
	public static Item itemMapleLog;

	public static Block treeTap;
	public static TileEntity tileTreeTap;
	public static Item itemTreeTap;

	public static Block mapleLeaves;
	public static Item itemMapleLeaves;

	public static Block blockEvaporator;
	public static Item itemEvaporator;

	public static Block blockLargeEvaporator;
	public static Item itemLargeEvaporator;
	
    public static Item itemHydrometer;
    
    public static Fluid fluidMapleSyrup;
    public static Block blockFluidMapleSyrup;   
    public static Item itemFluidMapleSyrup;
 
    public static Fluid fluidMapleSap;
    public static Block blockFluidMapleSap;   
    public static Item itemFluidMapleSap;
    
    public static Item itemMapleSyrupBottle;
    
	public static void createBlocks() {
		
		//WARNING never change the registry name!	
		
		//Blocks 
		
		GameRegistry.register(mapleSapling = new BlockMapleSapling("maple_sapling"));
        GameRegistry.register(itemMapleSapling = new ItemBlock(mapleSapling).setRegistryName(mapleSapling.getRegistryName()));
		
        GameRegistry.register(mapleLog = new BlockMapleLog("maple_log"));
        GameRegistry.register(itemMapleLog = new ItemBlock(mapleLog).setRegistryName(mapleLog.getRegistryName()));		
		
        GameRegistry.register(treeTap = new BlockTreeTap("tree_tap"));
        GameRegistry.register(itemTreeTap = new ItemBlock(treeTap).setRegistryName(treeTap.getRegistryName()));	

        GameRegistry.register(mapleLeaves = new BlockMapleLeaves("maple_leaves"));
        GameRegistry.register(itemMapleLeaves = new ItemBlock(mapleLeaves).setRegistryName(mapleLeaves.getRegistryName()));	
        
        GameRegistry.register(blockEvaporator = new BlockEvaporator("evaporator"));
        GameRegistry.register(itemEvaporator = new ItemBlock(blockEvaporator).setRegistryName(blockEvaporator.getRegistryName()));	      
        
        GameRegistry.register(blockLargeEvaporator = new BlockLargeEvaporator("large_evaporator"));
        GameRegistry.register(itemLargeEvaporator = new ItemBlockLargeEvaporator(blockLargeEvaporator).setRegistryName(blockLargeEvaporator.getRegistryName()));	      
        
        //Item registration
		GameRegistry.register(itemHydrometer= new ItemHydrometer("item_hydrometer"));
		GameRegistry.register(itemMapleSyrupBottle= new ItemMapleSyrupBottle("bottle_maplesyrup"));
		
		//Fluids registration
		
		//Maple syrup 
	    FluidRegistry.registerFluid(fluidMapleSyrup = new Fluid("maple_syrup_fluid", new ResourceLocation(MapleSyrup.MODID+":blocks/maplesyrup_still") , new ResourceLocation(MapleSyrup.MODID+":blocks/maplesyrup_flow")).setUnlocalizedName("maple_syrup_fluid"));	    
	    GameRegistry.register(blockFluidMapleSyrup = new BlockMapleSyrupFluid(fluidMapleSyrup, "maple_syrup_fluid") );
	    GameRegistry.register(itemFluidMapleSyrup = new ItemBlock(blockFluidMapleSyrup).setRegistryName("maple_syrup_fluid"));		
	    FluidRegistry.addBucketForFluid(fluidMapleSyrup);
	    
	    //Maple Sap
	    FluidRegistry.registerFluid(fluidMapleSap = new Fluid("maple_sap_fluid", new ResourceLocation(MapleSyrup.MODID+":blocks/maplesap_still") , new ResourceLocation(MapleSyrup.MODID+":blocks/maplesap_flow")).setUnlocalizedName("maple_sap_fluid"));	    
	    GameRegistry.register(blockFluidMapleSap = new BlockMapleSapFluid(fluidMapleSap, "maple_sap_fluid") );
	    GameRegistry.register(itemFluidMapleSap = new ItemBlock(blockFluidMapleSap).setRegistryName("maple_sap_fluid"));		
	    FluidRegistry.addBucketForFluid(fluidMapleSap);
	    

	    
	    
	    
	    //Tile entities
	    
	    GameRegistry.registerTileEntity(TileEntityTreeTap.class, "tileTreeTap");
	    GameRegistry.registerTileEntity(TileEntityEvaporator.class, "tileEvaporator");
	    //Register tappable blocks
	    TappableBlocksHandler.registerBlock(mapleLog, fluidMapleSap);
	    TappableBlocksHandler.registerBlock(Blocks.LOG, FluidRegistry.WATER);
	    
	    //Register evaporable fluids
	    EvaporationProcessesHandler.registerProcess(fluidMapleSap, fluidMapleSyrup, 20);
	    //OreDictionnary
	    //For compat with harvestcraft
	    OreDictionary.registerOre("cropMaplesyrup",itemMapleSyrupBottle);
	    OreDictionary.registerOre("LogWood",mapleLog);
	}

	
}
