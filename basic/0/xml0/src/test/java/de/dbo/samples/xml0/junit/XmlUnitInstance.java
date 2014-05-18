package de.dbo.samples.xml0.junit;

/*
******************************************************************
Copyright (c) 2001, Jeff Martin, Tim Bacon
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions
are met:

    * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above
      copyright notice, this list of conditions and the following
      disclaimer in the documentation and/or other materials provided
      with the distribution.
    * Neither the name of the xmlunit.sourceforge.net nor the names
      of its contributors may be used to endorse or promote products
      derived from this software without specific prior written
      permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
POSSIBILITY OF SUCH DAMAGE.

******************************************************************
*/
 
import static de.dbo.samples.util.print.Print.lines;
import static de.dbo.samples.xml0.junit.FileSystemUtils.path;
import static de.dbo.samples.xml0.junit.FileSystemUtils.read;

import org.custommonkey.xmlunit.AbstractNodeTester;
import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.DifferenceListener;
import org.custommonkey.xmlunit.ElementNameAndTextQualifier;
import org.custommonkey.xmlunit.HTMLDocumentBuilder;
import org.custommonkey.xmlunit.IgnoreTextAndAttributeValuesDifferenceListener;
import org.custommonkey.xmlunit.NodeTest;
import org.custommonkey.xmlunit.NodeTestException;
import org.custommonkey.xmlunit.TolerantSaxDocumentBuilder;
import org.custommonkey.xmlunit.Transform;
import org.custommonkey.xmlunit.Validator;
import org.custommonkey.xmlunit.XMLTestCase;
import org.custommonkey.xmlunit.XMLUnit;
import org.custommonkey.xmlunit.examples.CountingNodeTester;
import org.junit.Ignore;
import org.slf4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.List;

import javax.xml.transform.stream.StreamSource;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Example XMLUnit XMLTestCase code
 * Demonstrates use of:<br />
 * <ul>
 * <li>XMLTestCase: assertXMLEqual(), assertXMLNotEqual(),
 * assertXpathExists(), assertXpathNotExists(), assertXpathEvaluatesTo(),
 * assertXpathsEqual(), assertXpathsNotEqual(), assertNodeTestPasses()</li>
 * <li>Diff: similar(), identical()</li>
 * <li>DetailedDiff: getAllDifferences()</li>
 * <li>DifferenceListener: use with Diff class,
 * IgnoreTextAndAttributeValuesDifferenceListener implementation</li>
 * <li>ElementQualifier: use with Diff class,
 * ElementNameAndTextQualifier implementation</li>
 * <li>Transform: constructors, getResultDocument(), use with Diff class</li>
 * <li>Validator: constructor, isValid()</li>
 * <li>TolerantSaxDocumentBuilder and HTMLDocumentBuilder usage</li>
 * <li>NodeTest: CountingNodeTester and custom implementations</li>
 * <li>XMLUnit static methods: buildDocument(), buildControlDocument(),
 * buildTestDocument(), setIgnoreWhitespace()</li>
 * </ul>
 * <br />Examples and more at <a href="http://xmlunit.sourceforge.net"/>xmlunit.sourceforge.net</a>
 * 
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * Programs are meant to be read by humans and only incidentally for computers to execute (D. Knuth)
 *
 */
public class XmlUnitInstance extends XMLTestCase {
	private static final Logger log = getLogger(XmlUnitInstance.class);
	
    public XmlUnitInstance(String name) {
        super(name);
    }
    
    public void testForEquality() throws Exception {
    	final StringBuffer sb = new StringBuffer();
    	sb.append("<msg>");
    	sb.append("<uuid>0x00435A8C</uuid>");
    	sb.append("</msg>");
    	final StringBuffer sb2 = new StringBuffer();
    	sb2.append("<msg>");
    	sb2.append("<localId>2376</localId>");
    	sb2.append("</msg>");
        final String control = sb.toString();
        final String test    = sb2.toString();
        
//       assertXMLEqual("comparing test to control", control, test);
        assertXMLNotEqual("test not similar to control", control, test);
    }

    public void testIdentical() throws Exception {
    	final StringBuffer sb = new StringBuffer();
    	sb.append("<struct>");
    	sb.append("<int>3</int><boolean>false</boolean>");
    	sb.append("</struct>");
    	final StringBuffer sb2 = new StringBuffer();
    	sb2.append("<struct>");
    	sb2.append("<boolean>false</boolean><int>3</int>");
    	sb2.append("</struct>");
        final String control = sb.toString();
        final String test    = sb2.toString();
        printDifferences(control,test,"Identical");
    	
        final Diff myDiff = new Diff(control, test);
        assertTrue("pieces of XML are similar " + myDiff, myDiff.similar());
        assertFalse("but are they identical? " + myDiff, myDiff.identical());
    }

    public void testAllDifferences() throws Exception {
    	final StringBuffer sb = new StringBuffer();
    	sb.append("<news>");
    	sb.append("<item id=\"1\">War</item>");
    	sb.append("<item id=\"2\">Plague</item>");
    	sb.append("<item id=\"3\">Famine</item>");
    	sb.append("</news>");
    	final StringBuffer sb2 = new StringBuffer();
    	sb2.append("<news>");
    	sb2.append("<item id=\"1\">Peace</item>");
    	sb2.append("<item id=\"2\">Health</item>");
    	sb2.append("<item id=\"3\">Plenty</item>");
    	sb2.append("</news>");
        final String control = sb.toString();
        final String test    = sb2.toString();
        printDifferences(control,test,"AllDifferences");
    	
        final DetailedDiff detailedDifferences = new DetailedDiff(compareXML(control, test));
        final List<?> allDifferences = detailedDifferences.getAllDifferences();
        assertNotSame(detailedDifferences.toString(), 0, allDifferences.size());
    }

    public void testCompareToSkeletonXML() throws Exception {
    	final StringBuffer sb = new StringBuffer();
    	sb.append("<location>");
    	sb.append("<street-address>22 any street</street-address>");
    	sb.append("<postcode>XY00 99Z</postcode>");
    	sb.append("</location>");
    	final StringBuffer sb2 = new StringBuffer();
    	sb2.append("<location>");
    	sb2.append("<street-address>20 east cheap</street-address>");
    	sb2.append("<postcode>EC3M 1EB</postcode>");
    	sb2.append("</location>");
        final String control = sb.toString();
        final String test    = sb2.toString();
        printDifferences(control,test,"CompareToSkeletonXML");
 
        final DifferenceListener myDifferenceListener = new IgnoreTextAndAttributeValuesDifferenceListener();
        final Diff myDiff = new Diff(control, test);
        myDiff.overrideDifferenceListener(myDifferenceListener);
        assertTrue("test matches control skeleton " + myDiff, myDiff.similar());
    }

    public void testRepeatedChildElements() throws Exception {
    	final StringBuffer sb = new StringBuffer();
    	sb.append("<suite>");
    	sb.append("<test status=\"pass\">FirstTestCase</test>");
    	sb.append("<test status=\"pass\">SecondTestCase</test>");
    	sb.append("</suite>");
    	final StringBuffer sb2 = new StringBuffer();
    	sb2.append("<suite>");
    	sb2.append("<test status=\"pass\">SecondTestCase</test>");
    	sb2.append("<test status=\"pass\">FirstTestCase</test>");
    	sb2.append("</suite>");
        final String control = sb.toString();
        final String test    = sb2.toString();
        printDifferences(control,test,"RepeatedChildElements");
        
        assertXMLNotEqual("Repeated child elements in different sequence order are not equal by default", control, test);
        final Diff myDiff = new Diff(control, test);
        myDiff.overrideElementQualifier(new ElementNameAndTextQualifier());
        assertXMLEqual("But they are equal when an ElementQualifier controls which test element is compared with each control element",
            myDiff, true);
    }

    public void testXPaths() throws Exception {
    	final StringBuffer sb = new StringBuffer();
    	sb.append("<solar-system>");
    	sb.append("<planet name='Earth' position='3' supportsLife='yes'/>");
    	sb.append("<planet name='Venus' position='4'/>");
    	sb.append("</solar-system>");
        final String mySolarSystemXML = sb.toString();
        
        assertXpathExists("//planet[@name='Earth']", mySolarSystemXML);
        assertXpathNotExists("//star[@name='alpha centauri']", mySolarSystemXML);
        assertXpathsEqual("//planet[@name='Earth']", "//planet[@position='3']", mySolarSystemXML);
        assertXpathsNotEqual("//planet[@name='Venus']", "//planet[@supportsLife='yes']", mySolarSystemXML);
    }

    public void testXPathValues() throws Exception {
    	final StringBuffer sb = new StringBuffer();
    	sb.append("<java-flavours>");
    	sb.append("<jvm current='some platforms'>1.1.x</jvm>");
    	sb.append("<jvm current='no'>1.2.x</jvm><jvm current='yes'>1.3.x</jvm>");
    	sb.append("<jvm current='yes' latest='yes'>1.4.x</jvm>");
    	sb.append("</java-flavours>");
        final String myJavaFlavours = sb.toString();
        
        assertXpathEvaluatesTo("1.4.x", "//jvm[@latest='yes']", myJavaFlavours);
        assertXpathEvaluatesTo("2", "count(//jvm[@current='yes'])", myJavaFlavours);
        assertXpathValuesEqual("//jvm[4]/@latest", "//jvm[4]/@current", myJavaFlavours);
        assertXpathValuesNotEqual("//jvm[2]/@current", "//jvm[3]/@current", myJavaFlavours);
    }

    public void testXpathsInHTML() throws Exception {
    	final StringBuffer sb = new StringBuffer();
    	sb.append("<html>");
    	sb.append("<title>Ugh</title>");
    	sb.append("<body>");
    	sb.append("<h1>Heading");
    	sb.append("<ul>");
    	sb.append("<li id='1'>Item One<li id='2'>Item Two");
    	final String someBadlyFormedHTML = sb.toString();
    	
        final TolerantSaxDocumentBuilder tolerantSaxDocumentBuilder = new TolerantSaxDocumentBuilder(XMLUnit.newTestParser());
        final HTMLDocumentBuilder htmlDocumentBuilder = new HTMLDocumentBuilder(tolerantSaxDocumentBuilder);
        final Document wellFormedDocument = htmlDocumentBuilder.parse(someBadlyFormedHTML);
        assertXpathEvaluatesTo("Item One", "/html/body//li[@id='1']", wellFormedDocument);
        assertXpathEvaluatesTo("Item Two", "/html/body//li[@id='2']", wellFormedDocument);
    }

    /**
     * Fibonacci-test with non-detected errors (values are not tested)
     * @throws Exception
     */
    public void testCountingNodeTester() throws Exception {
    	final StringBuffer sb = new StringBuffer();
    	sb.append("<fibonacci>");
    	sb.append("<val>1</val>");
    	sb.append("<val>2</val>");
    	sb.append("<val>3</val>");
    	sb.append("<val>5</val>");
    	sb.append("<val>9</val>"); // error!
    	sb.append("</fibonacci>");
        final String test = sb.toString();
        
        final CountingNodeTester countingNodeTester = new CountingNodeTester(5);
        assertNodeTestPasses(test, countingNodeTester, Node.TEXT_NODE);
    }

    /**
     * real Fibonacci-test
     * @throws Exception
     */
    public void testCustomNodeTester() throws Exception {
    	final StringBuffer sb = new StringBuffer();
    	sb.append("<fibonacci>");
    	sb.append("<val>1</val>");
    	sb.append("<val>2</val>");
    	sb.append("<val>3</val>");
    	sb.append("<val>5</val>");
    	sb.append("<val>8</val>");
    	sb.append("</fibonacci>");
        final String test = sb.toString();
        
        final NodeTest nodeTest = new NodeTest(test);
        assertNodeTestPasses(nodeTest, new FibonacciNodeTester(), new short[] {Node.TEXT_NODE, Node.ELEMENT_NODE}, true);
    }
    
//    // As the document is parsed it is validated against its referenced DTD
//    @Ignore
//    public void testValidation() throws Exception {
//    	 XMLUnit.getTestDocumentBuilderFactory().setValidating(true);
//    	 final String xml = read("test.xml");
//    	 final String xmlError = read("test-error.xml");
////    	 printDifferences(xml,xmlBad,"Validation");
//    	 
//    	 log.debug("XML as string:\n" + xml);
////        final Document document = XMLUnit.buildTestDocument(xml);
//        final URL dtdUrl = path("test.dtd").toFile().toURI().toURL();
//        String systemId = "test.dtd"; // "SYSTEM";
//        log.debug("DTD: " + dtdUrl);
//       
////        final Validator validator = new Validator(xmlDocument, systemId, dtdUrl.toString());
//        final Validator validator = new Validator(xml, dtdUrl.toString());
//        assertTrue("test doesn't validate against unreferenced DTD", validator.isValid());
//        final Validator validatorError = new Validator(xmlError, systemId);
//        assertFalse("test-error validates against unreferenced DTD", validatorError.isValid());
//    }

//    public void testXSLTransformation() throws Exception {
//        String myInputXML = "...";
//        File myStylesheetFile = new File("...");
//        Transform myTransform = new Transform(myInputXML, myStylesheetFile);
//        String myExpectedOutputXML = "...";
//        Diff myDiff = new Diff(myExpectedOutputXML, myTransform);
//        assertTrue("XSL transformation worked as expected " + myDiff, myDiff.similar());
//    }

//    public void testAnotherXSLTransformation() throws Exception {
//        File myInputXMLFile = new File("...");
//        File myStylesheetFile = new File("...");
//        Transform myTransform = new Transform(new StreamSource(myInputXMLFile), new StreamSource(myStylesheetFile));
//        Document myExpectedOutputXML = XMLUnit.buildDocument(XMLUnit.newControlParser(), new FileReader("..."));
//        Diff myDiff = new Diff(myExpectedOutputXML, myTransform.getResultDocument());
//        assertTrue("XSL transformation worked as expected " + myDiff, myDiff.similar());
//    }

	private class FibonacciNodeTester extends AbstractNodeTester {
		private int nextVal = 1;
		private int lastVal = 1;
		@SuppressWarnings("unused")
		private int priorVal = 0;

		public void testText(Text text) throws NodeTestException {
			final int val = Integer.parseInt(text.getData());
			if (nextVal != val) {
				throw new NodeTestException("Incorrect sequence value", text);
			}
			nextVal = val + lastVal;
			priorVal = lastVal;
			lastVal = val;
		}

		public void testElement(Element element) throws NodeTestException {
			final String name = element.getLocalName();
			if ("fibonacci".equals(name) || "val".equals(name)) {
				return;
			}
			throw new NodeTestException("Unexpected element", element);
		}

		public void noMoreNodes(NodeTest nodeTest) throws NodeTestException {
		}
	}
	
	// HELPERS 
	
	private final void printDifferences(final String control, final String test, final String comment) throws Exception {
		 final DetailedDiff detailedDifferences = new DetailedDiff(compareXML(control, test));
	      log.debug(comment + ": " + lines(detailedDifferences.getAllDifferences()));	
	}
}