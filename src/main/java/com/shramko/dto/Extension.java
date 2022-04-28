package com.shramko.dto;

public enum Extension {
    XML("xml"), UNKNOWN("");

    private final String extension;

    Extension(String extension) {
        this.extension = extension;
    }

    public static Extension getFileExtension(final String filename){
        final String extension = filename.substring(filename.lastIndexOf(".") + 1);
        for (Extension value : Extension.values()) {
            if (value.extension.equalsIgnoreCase(extension)) {
                return value;
            }
        }
        return UNKNOWN;
    }
}
