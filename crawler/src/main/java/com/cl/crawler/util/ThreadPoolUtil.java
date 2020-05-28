package com.cl.crawler.util;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池
 * by cl at 2020/5/13 0013
 */
public class ThreadPoolUtil {
    private static ExecutorService executorService;

    public static synchronized void newFixedThreadPool(int nThreads) {
        if(executorService == null) {
            executorService = Executors.newFixedThreadPool(nThreads);
        }
    }

    public static void submit(Runnable runnable) {
        executorService.submit(runnable);
    }
}
