package de.dbo.samples.spring.autowiring.special.abstraction.v1;

/**
 * Simple POJO
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 */
public final class ExtensionV1 extends Abstraction {

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
