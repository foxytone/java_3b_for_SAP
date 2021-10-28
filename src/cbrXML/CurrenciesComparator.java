package cbrXML;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;


public class CurrenciesComparator {
    String url = "http://www.cbr.ru/scripts/XML_daily.asp";
    Document doc;
    
    public CurrenciesComparator() throws IOException, ParserConfigurationException, SAXException {
        this.initializeDoc();
    }
    
    private void initializeDoc() throws IOException, ParserConfigurationException, SAXException {
        var factory = DocumentBuilderFactory.newInstance();
        this.doc = factory.newDocumentBuilder().parse(new URL(this.url).openStream());
    }
    
    public float getRatio(String firstCurrencyCode, String secondCurrencyCode) throws Exception {
        var firstCurrencyValue = getCurrencyValue(firstCurrencyCode);
        var secondCurrencyValue = getCurrencyValue(secondCurrencyCode);
        
        return firstCurrencyValue / secondCurrencyValue;
    }
    
    private float getCurrencyValue(String currencyCode) throws Exception {
        var nodes = doc.getDocumentElement().getElementsByTagName("Valute");
        
        for (int i = 0; i < nodes.getLength(); i++) {
            // get children of Valute tag
            var children = nodes.item(i).getChildNodes();
            // CharCode tag - second in the items list
            if (children.item(1).getTextContent().equals(currencyCode)) {
                // nominal is the third in the items list
                // value is the fifth in the items list
                // we need value / nominal to guarantee nominal value for 1 ruble
                var nominal = Float.parseFloat(children.item(2).getTextContent().replace(',', '.'));
                var value = Float.parseFloat(children.item(4).getTextContent().replace(',', '.'));
                return value / nominal;
            }
        }
        throw new Exception("Currency Code " + currencyCode + " not found");
    }
}


