package de.dbo.samples.javafx.spring0.components;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import javafx.scene.Node;
import javafx.scene.control.SplitPane;

public class RootComponent extends SplitPane {
    
        @Value("${application.rootWidth:800}")
    	private String rootWidth;
    	
    	@Value("${application.rootHeight:600}")
    	private String rootHeight;
	
	@Value("${application.css:css/application.css}")
	private String css;
	
	private List<Node> leafs;
	
	public RootComponent() {
	 
	}
	
	public void init() {
	    this.setPrefSize(getRootWidth(), getRootHeight());
	    this.getItems().addAll(leafs);	
	}
	
	//
	// Getters and Setters
	//

	public List<Node> getLeafs() {
		return leafs;
	}

	public void setLeafs(List<Node> leafs) {
		this.leafs = leafs;
	}
	

	public String getCss() {
	    return css;
	}

	public void setCss(String css) {
	    this.css = css;
	}

	public double getRootWidth() {
	    return Double.parseDouble(rootWidth);
	}

	public void setRootWidth(String rootWidth) {
	    this.rootWidth = rootWidth;
	}

	public double getRootHeight() {
	    return Double.parseDouble(rootHeight);
	}

	public void setRootHeight(String rootHeight) {
	    this.rootHeight = rootHeight;
	}
}
