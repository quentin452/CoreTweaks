package makamys.toomanycrashes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

public class TMCPlugin implements IFMLLoadingPlugin {

	public TMCPlugin() {
		System.out.println("Instantiating TMCPlugin");
	}
	
	@Override
	public String[] getASMTransformerClass() {
		Config.reload();
		List<String> transformerClasses = new ArrayList<>();
		if(JarDiscovererCache.isActive()) {
			transformerClasses.add("makamys.toomanycrashes.ASMModParserTransformer");
		}
		if(ProfilerTransformer.isActive()) {
			transformerClasses.add("makamys.toomanycrashes.ProfilerTransformer");
		}
		return transformerClasses.toArray(new String[] {});
	}

	@Override
	public String getModContainerClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSetupClass() {
		Config.reload();
		if(JarDiscovererCache.isActive()) {
			JarDiscovererCache.load();
		}
		if(ProfilerTransformer.isActive()) {
			ProfilerTransformer.init();
		}
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getAccessTransformerClass() {
		// TODO Auto-generated method stub
		return null;
	}
	

	
}
