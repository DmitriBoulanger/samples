package de.dbo.samples.spring.javadependencyinjection.consumer;

import de.dbo.samples.spring.javadependencyinjection.service.Service;

public class ConsumerImpl implements Consumer{

	private Service service;
	
	public ConsumerImpl(Service service){
		this.service=service;
	}
	
	public ConsumerImpl(){}
	
	public void setService(Service service) {
		this.service = service;
	}

	@Override
	public void processMessages(String msg, String rec){
		//do some msg validation, manipulation logic etc
		this.service.sendMessage(msg, rec);
	}

}
