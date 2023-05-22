package com.alame.lab6.common.network;

import java.util.List;

public class NetworkUtils {
    public static byte[] convertListFramesToByteArray(List<Frame> frames){
        List<byte[]> byteList = frames.stream().map(Frame::getData).toList();
        int length = 0;
        for(byte[] array: byteList){
            length+=array.length;
        }
        byte[] result = new byte[length];
        int i = 0;
        for(byte[] array:byteList){
            for(byte b:array){
                result[i++] = b;
            }
        }
        return result;
    }
}
