package com.sword1024.test.company170807.sum;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main {

    public static void main(String[] args) throws IOException {
        long sumLo = 0L;
        long sumHi = 0L;
        try (InputStream fi = new BufferedInputStream(new FileInputStream(args[0]), 1024 * 1024)) {
            byte[] buf = new byte[4];
            long num;
            while (fi.read(buf) == buf.length) {
                num = 0L;
                for (int bi = buf.length - 1; bi >= 0; bi--) {
                    num <<= 8;
                    num |= buf[bi];
                }
                sumLo += num;
                sumHi += sumLo >> 32;
                sumLo &= 0xFFFFFFFFL;
            }
        }

        StringBuilder numText = new StringBuilder();
        long targetBaseRadix = 10L;
        while (sumLo != 0L || sumHi != 0L) {
            sumLo += sumHi % targetBaseRadix << 32;
            sumHi /= targetBaseRadix;
            numText.append(sumLo % 10L);
            sumLo /= targetBaseRadix;
        }
        System.out.println(numText.reverse().toString());
    }
}
