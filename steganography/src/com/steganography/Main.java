package com.steganography;

import com.steganography.handlers.DecodeHandler;
import com.steganography.handlers.EncodeHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Do you want to decode or encode?");
        String mode = br.readLine();
        if (mode.equals("decode")) {
            new DecodeHandler().handle();
        } else if (mode.equals("encode")) {
            new EncodeHandler().handle();
        } else {
            System.out.println("Not supported operation");
        }
    }
}
