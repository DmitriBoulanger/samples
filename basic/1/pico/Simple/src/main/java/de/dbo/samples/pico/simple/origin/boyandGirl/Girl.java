package de.dbo.samples.pico.simple.origin.boyandGirl;

public class Girl {
    Boy boy;

    public Girl(Boy boy) {
        this.boy = boy;
    }

    public void kissSomeone() {
        boy.kiss(this);
    }
}
