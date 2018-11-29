package com.steganography.service;


import com.steganography.EncodeInfo;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class SteganographyService {

    private final int START = 0;
    private final int OFFSET = 0;

    public SteganographyService() {
    }

    public boolean encode(EncodeInfo encodeInfo) throws IOException {
        File fileToHide = new File(encodeInfo.getFileToHide());
        if (!fileToHide.exists()) {
            System.out.println("Error! File to hide do not exists!");
            return false;
        }
        File coverFile = new File(encodeInfo.getCoverFile());
        if (!coverFile.exists()) {
            System.out.println("Error! Cover file do not exists!");
            return false;
        }
        String textToDecode = readText(new FileInputStream(fileToHide));
        byte[] image = imageToByte(coverFile);
        byte[] messageLength = bitConversion(textToDecode.getBytes().length);

        image = appendBytes(image, messageLength, START);
        image = appendBytes(image, textToDecode.getBytes(), OFFSET);

        //writing to file
        BufferedImage imageFile = ImageIO.read(new ByteArrayInputStream(image));
        saveFile(encodeInfo.getOutputFile(), imageFile, encodeInfo.getFileExtension());
        return true;
    }

    public String decode(File outputFile) throws IOException {
        byte[] image = imageToByte(outputFile);
        int offset = OFFSET;
        for (int i = 0; i < 32; ++i) {
            offset = (offset << 1) | (image[i] & 1);
        }
        byte[] result = new byte[offset];
        for (int b = 0; b < result.length; ++b) {
            for (int i = 0; i < 8; ++i, ++offset) {
                result[b] = (byte) ((result[b] << 1) | (image[offset] & 1));
            }
        }
        return new String(result);

    }

    private String readText(FileInputStream is) throws IOException {
        StringBuilder sb = new StringBuilder(512);
        try {
            Reader streamReader = new InputStreamReader(is, "UTF-8");
            int character = 0;
            while ((character = streamReader.read()) != -1) {
                sb.append((char) character);
            }
            streamReader.close();
        } catch (IOException e) {
            System.out.println("Error while reading file");
        } finally {
            is.close();
        }
        return sb.toString();
    }

    private byte[] imageToByte(File image) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(image);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", stream);
        stream.flush();
        byte[] result = stream.toByteArray();
        stream.close();
        return result;
    }

    private byte[] appendBytes(byte[] image, byte[] text, int offset) {
        if (text.length + offset > image.length) {
            System.out.println("File not long enough!");
        }
        for (int i = 0; i < text.length; ++i) {
            int add = text[i];
            for (int bit = 7; bit >= 0; --bit, ++offset) {
                int b = (add >>> bit) & 1;
                image[offset] = (byte) ((image[offset] & 0xFE) | b);
            }
        }
        return image;
    }

    private byte[] bitConversion(int i) {
        byte byte3 = (byte) ((i & 0xFF000000) >>> 24);
        byte byte2 = (byte) ((i & 0x00FF0000) >>> 16);
        byte byte1 = (byte) ((i & 0x0000FF00) >>> 8);
        byte byte0 = (byte) ((i & 0x000000FF));
        return (new byte[]{byte3, byte2, byte1, byte0});
    }

    private void saveFile(String fileName, BufferedImage imageFile, String extension) throws IOException {
        File outputFile = new File("C:/" + fileName + extension);
        ImageIO.write(imageFile, extension, outputFile);
    }

}
