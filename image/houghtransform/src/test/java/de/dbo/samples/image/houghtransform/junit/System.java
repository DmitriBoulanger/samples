package de.dbo.samples.image.houghtransform.junit;

import org.junit.Test;

import de.dbo.samples.image.houghtransform.HoughCategorizerFactory;
import de.dbo.samples.image.houghtransform.api.HoughTransformException;
import de.dbo.samples.image.houghtransform.core.CategorizerImpl;
import de.dbo.samples.image.houghtransform.testimpl.JUnit;

public class System extends JUnit {

    @Test(expected = HoughTransformException.class)
    public void incorrectCtxName() throws Exception {
        try {
            new CategorizerImpl("bla bla bla ... ");
        }
        catch(Exception e) {
            log.info("incorrect CTX-Name: " + e.getMessage());
            throw e;
        }
    }

    @Test(expected = HoughTransformException.class)
    public void noMarker() throws Exception {
        try {
            HoughCategorizerFactory.newInstance(null);
        }
        catch(Exception e) {
            log.info("no marker: " + e.getMessage());
            throw e;
        }
    }
}
