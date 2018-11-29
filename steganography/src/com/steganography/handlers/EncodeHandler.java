package com.steganography.handlers;

import com.steganography.EncodeInfo;
import com.steganography.service.SteganographyService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EncodeHandler implements SteganographyHandler {

    @Override
    public void handle() throws IOException {
        EncodeInfo encodeInfo = new EncodeInfo();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter path to file you want to hide: ");
        encodeInfo.setFileToHide(br.readLine());
        System.out.println("Please enter path to file in want to hide: ");
        encodeInfo.setCoverFile(br.readLine());
        System.out.println("Please enter output file name: ");
        encodeInfo.setOutputFile(br.readLine());
        System.out.println("Please enter comprehensionLevel: ");
        encodeInfo.setCompressLevel(br.readLine());
        System.out.println("Please enter encryption type: ");
        encodeInfo.setEncryptionType(br.readLine());
        br.close();
        new SteganographyService().encode(encodeInfo);
    }
}
