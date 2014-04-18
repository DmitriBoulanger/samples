package de.dbo.samples.gui.swing.treetable.api.factory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

final class FactoryCtx extends FactoryAbstraction  {
	
	public static final Factory instance(final String config) {
		final Factory exsistingInstance =  FACTORIES.get(config);
		if (null!=exsistingInstance) {
			return exsistingInstance;
		} 
		
		final ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
		final Factory newInstance = (Factory) ctx.getBean("factory");
		FACTORIES.put(config,newInstance);
		log.info("instance created for config=" + config);
		return newInstance;
	}
	 
	FactoryCtx() {
		
	}
	
}
