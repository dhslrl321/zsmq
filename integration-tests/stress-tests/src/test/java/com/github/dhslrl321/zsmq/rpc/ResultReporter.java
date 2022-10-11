package com.github.dhslrl321.zsmq.rpc;

public class ResultReporter {

    private static final String TOOK_MS_MESSAGE = "test took (ms) : ";

    public static void reportToConsole(long begin, long end) {
        long diff = (begin - end);
        System.out.println(TOOK_MS_MESSAGE + diff + " (ms)");
    }
}
