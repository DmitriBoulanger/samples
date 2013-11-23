package de.dbo.samples.xml0.junit;

import org.junit.Ignore;
import org.junit.Test;

/**
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * Programs are meant to be read by humans and only incidentally for computers to execute (D. Knuth)
 *
 */
public class XmlUnitInstanceTest  {
	private static XmlUnitInstance xmlUnit = new XmlUnitInstance("XmlUnitInstance Test");
	
    @Test
    public void testForEquality() throws Exception {
        xmlUnit.testForEquality();
    }

    @Test
    public void testIdentical() throws Exception {
       xmlUnit.testIdentical();
    }

    @Test
    public void testAllDifferences() throws Exception {
      xmlUnit.testAllDifferences();
    }

    @Test
    public void testCompareToSkeletonXML() throws Exception {
       xmlUnit.testCompareToSkeletonXML();
    }

    @Test
    public void testRepeatedChildElements() throws Exception {
        xmlUnit.testRepeatedChildElements();
    }

    //TODO we need files ...
    @Ignore
    @Test
    public void testXSLTransformation() throws Exception {
       xmlUnit.testXSLTransformation();
    }

    //TODO we need files ...
    @Ignore
    @Test
    public void testAnotherXSLTransformation() throws Exception {
        xmlUnit.testAnotherXSLTransformation();
    }

    //TODO how does it work?
    @Ignore
    @Test
    public void testValidation() throws Exception {
       xmlUnit.testValidation();
    }

    @Test
    public void testXPaths() throws Exception {
      xmlUnit.testXPaths();
    }

    @Test
    public void testXPathValues() throws Exception {
        xmlUnit.testXPathValues();
    }

    @Test
    public void testXpathsInHTML() throws Exception {
        xmlUnit.testXpathsInHTML();
    }

    @Test
    public void testCountingNodeTester() throws Exception {
        xmlUnit.testCountingNodeTester();
    }

    @Test
    public void testCustomNodeTester() throws Exception {
        xmlUnit.testCustomNodeTester();
    }
}


