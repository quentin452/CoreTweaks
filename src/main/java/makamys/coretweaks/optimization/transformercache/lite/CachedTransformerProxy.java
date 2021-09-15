package makamys.coretweaks.optimization.transformercache.lite;

import net.minecraft.launchwrapper.IClassTransformer;

public class CachedTransformerProxy implements IClassTransformer {

    public int runs = 0;
    public int misses = 0;
    
    protected IClassTransformer original;
    
    public CachedTransformerProxy(IClassTransformer original) {
        this.original = original;
    }
    
    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        runs++;
        byte[] result = TransformerCache.instance.getCached(original, name, transformedName, basicClass);
        if(result == null) {
            misses++;
            TransformerCache.instance.prePutCached(original, name, transformedName, basicClass);
            result = original.transform(name, transformedName, basicClass);
            TransformerCache.instance.putCached(original, name, transformedName, result);
        }
        return result;
    }
    
    @Override
    public String toString() {
        return "CachedTransformerProxy{" + original.getClass().getCanonicalName() + "}";
    }

}
