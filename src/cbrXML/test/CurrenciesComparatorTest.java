package cbrXML.test;

import cbrXML.CurrenciesComparator;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;


class CurrenciesComparatorTest {
    CurrenciesComparator comparator = new CurrenciesComparator();
    
    void setDocument() throws ParserConfigurationException, IOException, SAXException, NoSuchFieldException, IllegalAccessException {
        var factory = DocumentBuilderFactory.newInstance();
        var doc = factory.newDocumentBuilder().parse(System.getProperty("user.dir") + "\\src\\cbrXML\\test\\XML_daily.asp");
        
        var docField = this.comparator.getClass().getDeclaredField("doc");
        docField.setAccessible(true);
        docField.set(comparator, doc);
    }
    
    CurrenciesComparatorTest() throws IOException, ParserConfigurationException, SAXException, NoSuchFieldException, IllegalAccessException {
        setDocument();
    }
    
    @org.junit.jupiter.api.Test
    void correctRatio() throws Exception {
        assertEquals(1.27645, comparator.getRatio("AUD", "AZN"), 0.0002);
        assertEquals(0.19837, comparator.getRatio("KZT", "KGS"), 0.0002);
    }
}