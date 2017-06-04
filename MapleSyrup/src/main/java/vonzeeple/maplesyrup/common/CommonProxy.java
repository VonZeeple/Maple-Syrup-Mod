package vonzeeple.maplesyrup.common;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import vonzeeple.maplesyrup.MapleSyrup;
import vonzeeple.maplesyrup.compat.IEcompat;
import vonzeeple.maplesyrup.util.ModConfig;

public class CommonProxy {
    // Config instance
    public static Configuration config;

	public void preInit(FMLPreInitializationEvent event) {

		Content.registerContent();
		
		//Config file generation
        File directory = event.getModConfigurationDirectory();
        config = new Configuration(new File(directory.getPath(), MapleSyrup.MODID+".cfg"));
        ModConfig.readConfig();
	}

	public void init(FMLInitializationEvent e) {
		// TODO Auto-generated method stub
		if(Loader.isModLoaded("immersiveengineering"))
		{
		IEcompat.init();
		}

	}

	public void postInit(FMLPostInitializationEvent event) {
		// TODO Auto-generated method stub
        if (config.hasChanged()) {
            config.save();
        }
	}

}
