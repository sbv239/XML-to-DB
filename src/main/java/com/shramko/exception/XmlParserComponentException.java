package com.shramko.exception;

public class XmlParserComponentException extends RuntimeException {
    private static final String PREFIX = "XML-parser application: ";

    public XmlParserComponentException(String message) {
        super(PREFIX + message);
    }
}
