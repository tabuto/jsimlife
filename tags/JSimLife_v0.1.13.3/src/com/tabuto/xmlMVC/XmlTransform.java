/**
* @author Francesco di Dio
* Date: 27/dic/2010 16.24.40
* Titolo: XmlTransform.java
* Versione: 0.1 Rev.a:
*/


/*
 * Copyright (c) 2010 Francesco di Dio.
 * tabuto83@gmail.com 
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

 /*
  * Inserisci qui breve descrizione in italiano della classe
  */

package com.tabuto.xmlMVC;


import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XmlTransform {
	
	
	public static void transformXMLtoHTML(File xmlFile) {
		
		File htmlFile = new File("print.html");

		URL xslURL = XmlTransform.class.getResource("/xsl/print.xsl");
		
		//System.out.println(xslURL);
		
		File xslFile = new File(xslURL.getFile());
		
		//InputStream xslInputStream = ClassLoader.getSystemResourceAsStream("/xsl/print.xsl");
		
		StreamSource xmlStream = new StreamSource(xmlFile);
		StreamSource xslStream = new StreamSource(xslFile);
		StreamResult htmlStream = new StreamResult(htmlFile);

		try {
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer(xslStream);
			transformer.transform(xmlStream, htmlStream);
		} catch (TransformerException ex) {
			ex.printStackTrace();
		}
		
		
		
		String htmlFileName = htmlFile.getName();
		
        File findMyDirectory = new File("");
        String currentDirectory = findMyDirectory.getAbsolutePath();
        
        String outputFilePath = "file://" + currentDirectory + "/" + htmlFileName;
        
        outputFilePath = outputFilePath.replaceAll(" ", "%20");
        
        // BareBonesBrowserLaunch.openURL(outputFilePath);
	}

}
