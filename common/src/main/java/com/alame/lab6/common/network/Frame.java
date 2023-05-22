package com.alame.lab6.common.network;

import java.io.Serializable;

public class Frame implements Serializable {
    private byte[] data;
    private boolean isLast;
    public Frame(byte[] data, boolean isLast){
        this.data = data;
        this.isLast = isLast;
    }

    public byte[] getData() {
        return data;
    }

    public boolean isLast() {
        return isLast;
    }
}
