package de.dbo.samples.gui.swing.treetable.api.factory;

public final class FactoryMgr {

	public static final Factory instance(final String config) {
		
		if (config.endsWith(".xml")) {
			return FactoryCtx.instance(config);
		} else if (config.endsWith(".properties"))  {
			return FactoryProp.instance(config);
		} else {
			throw new FactoryException("Can't recognize type for resource: " + config);
		}
	}
}
