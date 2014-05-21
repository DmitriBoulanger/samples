package de.dbo.samples.image.houghtransform.junit;

import de.dbo.samples.image.houghtransform.HoughCategorizerFactory;
import de.dbo.samples.image.houghtransform.api.HTException;
import de.dbo.samples.image.houghtransform.core.Categorizer;
import de.dbo.samples.image.houghtransform.testimpl.JUnit;

import org.junit.Test;

public class System extends JUnit {

    @Test(expected = HTException.class)
    public void incorrectCtxName() throws Exception {
        try {
            new Categorizer("bla bla bla ... ");
        }
        catch(Exception e) {
            log.info("incorrectCtxName: " + e.getMessage());
            throw e;
        }
    }

    @Test(expected = HTException.class)
    public void noOMRMarker() throws Exception {
        try {
            HoughCategorizerFactory.newInstance(null);
        }
        catch(Exception e) {
            log.info("noOMRMarker: " + e.getMessage());
            throw e;
        }
    }
}
