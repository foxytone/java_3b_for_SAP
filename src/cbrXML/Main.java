package cbrXML;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        var comparator = new CurrenciesComparator();
        // Case-sensitive
        var hungarianForintCode = "HUF";
        var norwegianKrone = "NOK";
        System.out.println(comparator.getRatio(norwegianKrone, hungarianForintCode));
    }
}
