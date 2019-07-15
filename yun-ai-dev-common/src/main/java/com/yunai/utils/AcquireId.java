package com.yunai.utils;

public class AcquireId {
    public static String getUUID(){
        String uuid = String.valueOf(java.util.UUID.randomUUID());
        return uuid.replace("-","");
    }
}
