package com.shramko.exception;

public class XmlParserServiceException  extends RuntimeException {
    private static final String PREFIX = "XML-parser application: ";

    public XmlParserServiceException(String message) {
        super(PREFIX + message);
    }
}
