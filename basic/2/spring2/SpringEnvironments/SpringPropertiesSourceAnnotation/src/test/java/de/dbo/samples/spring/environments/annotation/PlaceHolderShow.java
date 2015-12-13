package de.dbo.samples.spring.environments.annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class PlaceHolderShow {
    private static final Logger log = LoggerFactory.getLogger(PlaceHolderShow.class);
    
    private String placeHolder;
    private String placeHolder2;
    private String placeHolder3;
    
    public PlaceHolderShow() {
	
    }
    
    public void init() {
	final StringBuilder sb = new StringBuilder("Values in the place-holders after init:");
	sb.append("\n\t - placeHolder  = " + placeHolder);
	sb.append("\n\t - placeHolder2 = " + placeHolder2);
	sb.append("\n\t - placeHolder3 = " + placeHolder3);

	log.info(sb.toString());
    }

    public String getPlaceHolder() {
        return placeHolder;
    }

    public void setPlaceHolder(String placeHolder) {
        this.placeHolder = placeHolder;
    }
    
    public String getPlaceHolder2() {
        return placeHolder2;
    }

    public void setPlaceHolder2(String placeHolder2) {
        this.placeHolder2 = placeHolder2;
    }

    public String getPlaceHolder3() {
        return placeHolder3;
    }

    public void setPlaceHolder3(String placeHolder3) {
        this.placeHolder3 = placeHolder3;
    }
    
    

}
