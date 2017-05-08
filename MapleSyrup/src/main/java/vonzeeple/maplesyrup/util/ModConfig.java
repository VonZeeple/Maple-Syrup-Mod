package vonzeeple.maplesyrup.util;

import net.minecraftforge.common.config.Configuration;
import vonzeeple.maplesyrup.common.CommonProxy;

public class ModConfig {
	
public static void readConfig() {
    Configuration cfg = CommonProxy.config;
    try {
        cfg.load();
        initGeneralConfig(cfg);

    } catch (Exception e1) {
       Logger.error("Problem loading config file!");
    } finally {
        if (cfg.hasChanged()) {
            cfg.save();
        }
    }
}

private static void initGeneralConfig(Configuration cfg) {
    cfg.addCustomCategoryComment("general", "General configuration");
    // cfg.getBoolean() will get the value in the config if it is already specified there. If not it will create the value.
    //isThisAGoodTutorial = cfg.getBoolean("goodTutorial", CATEGORY_GENERAL, isThisAGoodTutorial, "Set to false if you don't like this tutorial");
    //yourRealName = cfg.getString("realName", CATEGORY_GENERAL, yourRealName, "Set your real name here");
}
}