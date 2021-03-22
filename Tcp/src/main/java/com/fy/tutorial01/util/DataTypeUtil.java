package com.fy.tutorial01.util;

public class DataTypeUtil {

    public static String bytesToHex(byte[] bytes){
        StringBuilder stringBuilder = new StringBuilder("");
        for(byte b : bytes){
            stringBuilder.append(String.format("%x ", b));
        }
        return stringBuilder.toString();
    }
}
