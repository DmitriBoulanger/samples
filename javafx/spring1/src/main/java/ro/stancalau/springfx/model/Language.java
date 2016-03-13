package ro.stancalau.springfx.model;

public enum Language{
	
	EN("en"), RO("ro");
	
	private String value;
	
	private Language(String s){
		value=s;
	}
	
	public String getValue(){
		return value;
	}
	
}
