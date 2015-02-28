package de.dbo.samples.spring.autowiring.abc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class B {
    
    @Autowired
    private C c;

    public C getC() {
        return c;
    }

//    public void setC(C c) {
//        this.c = c;
//    }
    
    

}
