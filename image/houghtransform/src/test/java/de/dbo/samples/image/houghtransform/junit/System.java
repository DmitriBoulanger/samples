package de.dbo.samples.image.houghtransform.junit;

import org.junit.Test;

import de.dbo.samples.image.houghtransform.CategorizerFactory;
import de.dbo.samples.image.houghtransform.api.CategorizerException;
import de.dbo.samples.image.houghtransform.core.CategorizerImpl;
import de.dbo.samples.image.houghtransform.testimpl.JUnit;

public class System extends JUnit {

    @Test(expected = CategorizerException.class)
    public void incorrectCtxName() throws Exception {
        try {
            new CategorizerImpl("bla bla bla ... ");
        }
        catch(Exception e) {
            log.info("incorrect CTX-Name: " + e.getMessage());
            throw e;
        }
    }

    @Test(expected = CategorizerException.class)
    public void noMarker() throws Exception {
        try {
            CategorizerFactory.newInstance(null);
        }
        catch(Exception e) {
            log.info("no marker: " + e.getMessage());
            throw e;
        }
    }
}
