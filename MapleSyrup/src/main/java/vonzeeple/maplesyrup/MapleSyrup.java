package vonzeeple.maplesyrup;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vonzeeple.maplesyrup.common.CommonProxy;
import vonzeeple.maplesyrup.common.Content;

@Mod(modid = MapleSyrup.MODID, name = MapleSyrup.MODNAME, version = MapleSyrup.VERSION)
public class MapleSyrup {

	public static final String MODID = "maplesyrup";
	public static final String MODNAME = "Maple syrup";
	public static final String VERSION = "0.0.1";

	// On instancie la classe du mod
	@Instance
	public static MapleSyrup instance = new MapleSyrup();

	@SidedProxy(clientSide = "vonzeeple." + MODID + ".client.ClientProxy", serverSide = "vonzeeple." + MODID
			+ ".server.ServerProxy")
	public static CommonProxy proxy;

	// Ajout du creative tab
	public static CreativeTabs creativeTab = new CreativeTabs(MODID) {

		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return Item.getItemFromBlock(Content.mapleSapling);
		}

	};
	
	//Static initializer
	static {
		FluidRegistry.enableUniversalBucket(); // Must be called before preInit
	}

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		MapleSyrup.proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		MapleSyrup.proxy.init(e);
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		MapleSyrup.proxy.postInit(event);
	}
	

}
