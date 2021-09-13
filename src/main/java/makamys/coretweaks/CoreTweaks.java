package makamys.coretweaks;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import makamys.coretweaks.optimization.CacheTransformer;
import makamys.coretweaks.optimization.ThreadedTextureLoader;
import net.minecraft.launchwrapper.Launch;

public class CoreTweaks {
    
    public static final String MODID = "coretweaks";
    public static final String VERSION = "0.1";
    
    public static ThreadedTextureLoader textureLoader;
    
    public static CacheTransformer cacheTransformer;
    
    public static int boundTexture;
    public static boolean isStitching;
    
    public static final Logger LOGGER = LogManager.getLogger("coretweaks");
    
    public static void init(){
        Persistence.loadIfNotLoadedAlready();
        
        if(Config.threadedTextureLoader) {
                textureLoader = new ThreadedTextureLoader(
                    Config.textureLoaderThreadCount != 0 ? Config.textureLoaderThreadCount
                            : Runtime.getRuntime().availableProcessors());
        }
        
        if(Config.cacheTransformer) {
            cacheTransformer = CacheTransformer.register();
        }
        
        Persistence.lastVersion = CoreTweaks.VERSION;
        Persistence.save();
    }
    
    public static File getDataFile(String name) {
        return getDataFile(name, true);
    }
    
    public static File getDataFile(String name, boolean createIfNotExists) {
        File myDir = new File(Launch.minecraftHome, "coretweaks");
        if(!myDir.exists()) {
            myDir.mkdir();
        }
        File dataFile = new File(myDir, name);
        
        if(createIfNotExists) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return dataFile;
    }
}
