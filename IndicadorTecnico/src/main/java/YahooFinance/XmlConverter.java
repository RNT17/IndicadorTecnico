package YahooFinance;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

public class XmlConverter {

	public XmlConverter(){}
	
	public Node Converter(String strXml) throws Exception 
	{		
		strXml = strXml.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "");
		//strXml = strXml.replace("Symbol=\"GOOG\"", "");
		strXml = strXml.toLowerCase();
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		Document doc = dbf.newDocumentBuilder().parse(new InputSource(new StringReader(strXml)));

		XPath xpath = XPathFactory.newInstance().newXPath();
		Node result = (Node) xpath.evaluate("query/results", doc, XPathConstants.NODE);
		
		//System.out.println(nodeToString(result));
		
		return result;
	}

	@SuppressWarnings("unused")
	private static String nodeToString(Node node) throws TransformerException 
	{
		StringWriter buf = new StringWriter();
		Transformer xform = TransformerFactory.newInstance().newTransformer();
		xform.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		xform.setOutputProperty(OutputKeys.INDENT, "yes");
		xform.transform(new DOMSource(node), new StreamResult(buf));
		return (buf.toString());
	}
}
