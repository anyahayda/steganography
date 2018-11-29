package com.steganography.handlers;

import com.steganography.service.SteganographyService;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class DecodeHandler implements SteganographyHandler {
    @Override
    public void handle() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter path to file you want to encode: ");
        new SteganographyService().decode(new File(br.readLine()));
        br.close();
    }
}
