package com.shadesh.converter;

import nu.xom.Builder;
import nu.xom.Document;
import org.apache.camel.Converter;
import org.apache.camel.Exchange;
import org.apache.camel.converter.stream.InputStreamCache;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author humayun
 */
@Converter
public class DocumentConverter {
    
    @Converter
    public static Document getDocument(InputStream stream, Exchange exchange) throws Exception {
        System.err.println("we are in getDocument() method");
        return new Builder().build(stream);
    }

    @Converter
    public static InputStream getInputStream(Document document, Exchange exchange) throws Exception {
        System.err.println("we are in getInputStream() method");
        String s = document.toXML();
        System.out.println("string value is - " + s);
        return new ByteArrayInputStream(s.getBytes());
    }
}
