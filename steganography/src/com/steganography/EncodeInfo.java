package com.steganography;

public class EncodeInfo {
    private String fileToHide;
    private String coverFile;
    private String outputFile;
    private String compressLevel;
    private String encryptionType;

    public String getFileToHide() {
        return fileToHide;
    }

    public void setFileToHide(String fileToHide) {
        this.fileToHide = fileToHide;
    }

    public String getCoverFile() {
        return coverFile;
    }

    public void setCoverFile(String coverFile) {
        this.coverFile = coverFile;
    }

    public String getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }

    public String getCompressLevel() {
        return compressLevel;
    }

    public void setCompressLevel(String compressLevel) {
        this.compressLevel = compressLevel;
    }

    public String getEncryptionType() {
        return encryptionType;
    }

    public void setEncryptionType(String encryptionType) {
        this.encryptionType = encryptionType;
    }

    public String getFileExtension() {
        String extension = "";
        int i = fileToHide.lastIndexOf('.');
        if (i > 0) {
            extension = fileToHide.substring(i + 1);
        }
        return extension;
    }
}
