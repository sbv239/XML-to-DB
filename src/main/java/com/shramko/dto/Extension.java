package com.shramko.dto;

public enum Extension {
    XML("xml"), UNKNOWN("");

    private final String ext;

    Extension(String extension) {
        this.ext = extension;
    }

    public static Extension getFileExtension(final String filename){
        final String extension = filename.substring(filename.lastIndexOf(".") + 1);
        for (Extension value : Extension.values()) {
            if (value.ext.equalsIgnoreCase(extension)) {
                return value;
            }
        }
        return UNKNOWN;
    }
}
