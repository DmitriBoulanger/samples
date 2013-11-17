package de.dbo.samples.xml0;

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

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;


public class XmlUnitExampleTest  {
	
	private static XmlUnitExample x;
	
    @BeforeClass
    public static void init() {
    	x = new XmlUnitExample("XmlUnitExample Test");
    }
    
    @Ignore
    @Test
    public void testForEquality() throws Exception {
        x.testForEquality();
    }

    @Ignore
    @Test
    public void testIdentical() throws Exception {
       x.testIdentical();
    }

    @Ignore
    @Test
    public void testAllDifferences() throws Exception {
      x.testAllDifferences();
    }

    @Test
    public void testCompareToSkeletonXML() throws Exception {
       x.testCompareToSkeletonXML();
    }

    @Test
    public void testRepeatedChildElements() throws Exception {
        x.testRepeatedChildElements();
    }

    //TODO
    @Ignore
    @Test
    public void testXSLTransformation() throws Exception {
       x.testXSLTransformation();
    }

    //TODO we need files ...
    @Ignore
    @Test
    public void testAnotherXSLTransformation() throws Exception {
        x.testAnotherXSLTransformation();
    }

    //TODO we need files ...
    @Ignore
    @Test
    public void testValidation() throws Exception {
       x.testValidation();
    }

    @Test
    public void testXPaths() throws Exception {
      x.testXPaths();
    }

    @Test
    public void testXPathValues() throws Exception {
        x.testXPathValues();
    }

    @Test
    public void testXpathsInHTML() throws Exception {
        x.testXpathsInHTML();
    }

    @Ignore
    @Test
    public void testCountingNodeTester() throws Exception {
        x.testCountingNodeTester();
    }

    @Ignore
    @Test
    public void testCustomNodeTester() throws Exception {
        x.testCustomNodeTester();
    }
}


