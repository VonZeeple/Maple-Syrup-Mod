package vonzeeple.maplesyrup.common;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapelessOreRecipe;
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
 
    public static Fluid fluidBirchSyrup;
    public static Block blockFluidBirchSyrup;   
    public static Item itemFluidBirchSyrup;
    
    public static Fluid fluidMapleSap;
    public static Block blockFluidMapleSap;   
    public static Item itemFluidMapleSap;
 
    public static Fluid fluidBirchSap;
    public static Block blockFluidBirchSap;   
    public static Item itemFluidBirchSap;
    
    public static Fluid fluidHeveaSap;
    public static Block blockFluidHeveaSap;   
    
    public static Item itemMapleSyrupBottle;
    
	public static void registerContent() {
		
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
		GameRegistry.register(itemMapleSyrupBottle= new ItemMapleSyrupBottle("bottle_maplesyrup").setContainerItem(Items.GLASS_BOTTLE));
		
		//Fluids registration
		
		//Maple syrup 
	    FluidRegistry.registerFluid(fluidMapleSyrup = new Fluid("maple_syrup_fluid", new ResourceLocation(MapleSyrup.MODID+":blocks/maplesyrup_still") , new ResourceLocation(MapleSyrup.MODID+":blocks/maplesyrup_flow")).setUnlocalizedName("maple_syrup_fluid"));	    
	    GameRegistry.register(blockFluidMapleSyrup = new BlockMapleSyrupFluid(fluidMapleSyrup, "maple_syrup_fluid") );
	    FluidRegistry.addBucketForFluid(fluidMapleSyrup);
	    
		//Birch syrup 
	    FluidRegistry.registerFluid(fluidBirchSyrup = new Fluid("birch_syrup_fluid", new ResourceLocation(MapleSyrup.MODID+":blocks/maplesyrup_still") , new ResourceLocation(MapleSyrup.MODID+":blocks/maplesyrup_flow")).setUnlocalizedName("maple_syrup_fluid"));	    
	    GameRegistry.register(blockFluidBirchSyrup = new BlockMapleSyrupFluid(fluidBirchSyrup, "birch_syrup_fluid") );
	    FluidRegistry.addBucketForFluid(fluidBirchSyrup);
	    
	    //Maple Sap
	    FluidRegistry.registerFluid(fluidMapleSap = new Fluid("maple_sap_fluid", new ResourceLocation(MapleSyrup.MODID+":blocks/maplesap_still") , new ResourceLocation(MapleSyrup.MODID+":blocks/maplesap_flow")).setUnlocalizedName("maple_sap_fluid"));	    
	    GameRegistry.register(blockFluidMapleSap = new BlockMapleSapFluid(fluidMapleSap, "maple_sap_fluid") );	
	    FluidRegistry.addBucketForFluid(fluidMapleSap);
	    //Birch Sap
	    FluidRegistry.registerFluid(fluidBirchSap = new Fluid("birch_sap_fluid", new ResourceLocation(MapleSyrup.MODID+":blocks/maplesap_still") , new ResourceLocation(MapleSyrup.MODID+":blocks/maplesap_flow")).setUnlocalizedName("birch_sap_fluid"));	    
	    GameRegistry.register(blockFluidBirchSap = new BlockMapleSapFluid(fluidBirchSap, "birch_sap_fluid") );
	    FluidRegistry.addBucketForFluid(fluidBirchSap);	    
	    //Hevea Sap
	    FluidRegistry.registerFluid(fluidHeveaSap = new Fluid("hevea_sap_fluid", new ResourceLocation(MapleSyrup.MODID+":blocks/heveasap_still") , new ResourceLocation(MapleSyrup.MODID+":blocks/heveasap_flow")).setUnlocalizedName("hevea_sap_fluid"));	    
	    GameRegistry.register(blockFluidHeveaSap = new BlockMapleSapFluid(fluidHeveaSap, "hevea_sap_fluid") );
	    FluidRegistry.addBucketForFluid(fluidHeveaSap);	  
	    
	    //Tile entities
	    
	    GameRegistry.registerTileEntity(TileEntityTreeTap.class, "tileTreeTap");
	    GameRegistry.registerTileEntity(TileEntityEvaporator.class, "tileEvaporator");
	    GameRegistry.registerTileEntity(TileEntityEvaporator.class, "tileLargeEvaporator");
	    
	    //Register tappable blocks
	    TappableBlocksHandler.registerBlock(mapleLog.getDefaultState(), new FluidStack(fluidMapleSap,100));
	    TappableBlocksHandler.registerBlock(Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.BIRCH), new FluidStack(fluidBirchSap,100));
	    TappableBlocksHandler.registerBlock(Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE), new FluidStack(fluidHeveaSap,100));
	    
	    
	    //Register evaporable fluids
	    EvaporationProcessesHandler.registerProcess(fluidMapleSap, fluidMapleSyrup, 20);
	    EvaporationProcessesHandler.registerProcess(fluidBirchSap, fluidBirchSyrup, 40);
	    //Recipes
	    GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Content.itemMapleSyrupBottle,4), Items.GLASS_BOTTLE,Items.GLASS_BOTTLE,Items.GLASS_BOTTLE,Items.GLASS_BOTTLE, UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, fluidMapleSyrup)));
	    

	    //OreDictionnary
	    OreDictionary.registerOre("BottleSyrup",itemMapleSyrupBottle);
	    OreDictionary.registerOre("LogWood",mapleLog);
	    
	    //For compat with harvestcraft
	    OreDictionary.registerOre("cropMaplesyrup",itemMapleSyrupBottle);
	    
	}

	
}
