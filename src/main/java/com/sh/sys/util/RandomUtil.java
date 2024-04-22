package com.sh.sys.util;

import java.util.Random;

public class RandomUtil {

    public static String generateRamdamCode(){
        Random r = new Random();
        StringBuilder code = new StringBuilder();
        for(int i=0;i<6;i++){
            code.append(r.nextInt(10));
        }
        return code.toString();
    }
}
