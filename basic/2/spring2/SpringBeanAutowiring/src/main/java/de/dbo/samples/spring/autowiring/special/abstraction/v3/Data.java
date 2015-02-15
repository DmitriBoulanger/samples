package de.dbo.samples.spring.autowiring.special.abstraction.v3;

/**
 * POJO to be inserted into the field in the Abstraction
 * It is Spring-independent (no annotations)
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */


public final class Data {

    private String name;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }
}
