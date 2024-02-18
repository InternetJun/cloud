package com.example.stock.basic;

import lombok.extern.slf4j.Slf4j;
import org.example.common.core.util.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/1/7 19:26
 */
@Slf4j
public class CompleteFutureTest {
    public static String path = "C:\\Users\\lejun\\Desktop\\world\\test";

    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        // step1:异步执行
//        Runnable runnable = () -> {
//            System.out.println("执行无返回结果的异步任务-开始");
//            try {
//                TimeUnit.SECONDS.sleep(5);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("执行无返回结果的异步任务-结束");
//
//        };
//        CompletableFuture.runAsync(runnable);
//
//        // step2:异步执行 阻塞获取结果
//        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
//            System.out.println("执行有返回值的异步任务");
//            try {
//                TimeUnit.SECONDS.sleep(5);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return "Hello World";
//        });
//        String result = future.get();
//        System.out.println(result);
//
//        // step3:异步执行 阻塞获取结果后对结果进行运算处理 不改变最终结果
//        String result2 = future.whenComplete(new BiConsumer<String, Throwable>() {
//            @Override
//            public void accept(String t, Throwable action) {
//                t = t + 1;
//                System.out.println("任务执行后结果处理");
//            }
//        }).exceptionally(new Function<Throwable, String>() {
//            @Override
//            public String apply(Throwable t) {
//                System.out.println("任务执行后结果额外处理-如果有异常进入此处");
//                return "异常结果";
//            }
//        }).get();
//
//        System.out.println("最终结果" + result2);

        // step4:
        // thenCombine会将两个任务的执行结果作为所提供函数的参数，且该方法有返回值；
        // thenAcceptBoth同样将两个任务的执行结果作为方法入参，但是无返回值；
        // runAfterBoth没有入参，也没有返回值。注意两个任务中只要有一个执行异常，则将该异常信息作为指定任务的执行结果

        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + " cf1 do something....");
            return 1;
        });

        CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + " cf2 do something....");
            return 2;
        });

        CompletableFuture<Integer> cf3 = cf1.thenCombine(cf2, (a, b) -> {
            System.out.println(Thread.currentThread() + " cf3 do something....");
            return a + b;
        });

        System.out.println("cf3结果->" + cf3.get());

        CompletableFuture<Integer> cf4 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + " cf4 do something....");
            return 1;
        });

        CompletableFuture<Integer> cf5 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + " cf5 do something....");
            return 2;
        });

        CompletableFuture<Void> cf6 = cf4.thenAcceptBoth(cf5, (a, b) -> {
            System.out.println(Thread.currentThread() + " cf6 do something....");
            System.out.println("处理结果不返回：" + (a + b));
        });

        System.out.println("cf3结果->" + cf6.get());


        // step5:
        //allOf:
        // CompletableFuture是多个任务都执行完成后才会执行，只有有一个任务执行异常，则返回的CompletableFuture执行get方法时会抛出异常，如果都是正常执行，则get返回null。
        //anyOf:
        // CompletableFuture是多个任务只要有一个任务执行完成，则返回的CompletableFuture执行get方法时会抛出异常，如果都是正常执行，则get返回执行完成任务的结果。
//        CompletableFuture<String> cf7 = CompletableFuture.supplyAsync(() -> {
//            try {
//                System.out.println(Thread.currentThread() + " cf7 do something....");
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("cf7 任务完成");
//            return "cf7 任务完成";
//        });
//
//        CompletableFuture<String> cf8 = CompletableFuture.supplyAsync(() -> {
//            try {
//                System.out.println(Thread.currentThread() + " cf8 do something....");
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("cf8 任务完成");
//            return "cf8 任务完成";
//        });
//
//        CompletableFuture<String> cf9 = CompletableFuture.supplyAsync(() -> {
//            try {
//                System.out.println(Thread.currentThread() + " cf9 do something....");
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("cf9 任务完成");
//            return "cf9 任务完成";
//        });
//        /*
//        CompletableFuture<Void> cfAll = CompletableFuture.allOf(cf7, cf8, cf9);
//        System.out.println("cfAll结果->" + cfAll.get());
//        */
//        CompletableFuture<Object> cfAny = CompletableFuture.anyOf(cf7, cf8, cf9);
//        System.out.println("cfAny结果->" + cfAny.get());
    }

    @Test
    public void main() throws ExecutionException, InterruptedException, IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Map<String, List<String>>> futureList = new ArrayList<>();
        CompletableFuture[] futures = new CompletableFuture[2];
        CompletableFuture<Map<String, List<String>>> future1 = CompletableFuture.supplyAsync(() -> {
            Map<String, List<String>> result = new HashMap<>();
            try {
                result = dealMessage();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        });
        futures[0] = future1;
        CompletableFuture<Map<String, List<String>>> future2 = CompletableFuture.supplyAsync(() -> {
            return dealVoice();
        });
        futures[1] = future2;
        CompletableFuture<Void> allFuture = CompletableFuture.allOf(futures);

        List<Map<String, List<String>>> maps = allFuture.thenApply(v -> Arrays.stream(futures)
                .map(CompletableFuture<Map<String, List<String>>>::join).collect(Collectors.toList())).get();
        executorService.shutdown();
        // 对delete的目录删除
        for (Map<String, List<String>> map : maps) {
            List<String> delete = map.get("delete");
            for (String s : delete) {
                File file = Paths.get(s).toFile();
                if (file.isDirectory()) {
                    List<String> strings = FileUtils.readFileName(s);
                    log.info("{}文件夹下如下文件{}", s, strings);
                    strings.forEach(p->{
                        new File(p).delete();
                    });
                }
                file.delete();
                log.info("{}已被删除", s);
            }
        }

        /**
         * ExecutorService executorService = Executors.newFixedThreadPool(5);
         *List<String> list = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
         *List<CompletableFuture<String>> futureList = list.stream()
         .map(str -> printStr(str, executorService)).collect(Collectors.toList());
         *CompletableFuture<Void> allFuture = CompletableFuture.allOf(futureList.toArray(new CompletableFuture[futureList.size()]));
         *CompletableFuture<List<String>> resultFuture =
         *log.info("result:{}", resultFuture.get());
         *executorService.shutdown();
         *
         */

    }

    public Map<String, List<String>> dealVoice() {
        Map<String, List<String>> map = new HashMap<>();
        String file = "C:\\Users\\lejun\\Desktop\\world\\test\\voice";
        String target = "C:\\Users\\lejun\\Desktop\\world\\test\\语音\\voice.zip";
        FileUtils.zip(file, target);
        map.put("delete", Arrays.asList(file));
        map.put("file", Arrays.asList("C:\\Users\\lejun\\Desktop\\world\\test\\语音"));
        log.info("已完成语音处理！");
        return map;
    }

    public Map<String, List<String>> dealMessage() throws IOException {
        Map<String, List<String>> map = new HashMap<>();
        String file = "C:\\Users\\lejun\\Desktop\\world\\test\\message";
        String target = "C:\\Users\\lejun\\Desktop\\world\\test\\短信\\message.zip";
        FileUtils.zip(file, target);
        map.put("delete", Arrays.asList(file));
        for (String s : Arrays.asList(file)) {
            File deleteFile = Paths.get(s).toFile();
            if (deleteFile.isDirectory()) {
                List<String> strings = FileUtils.readFileName(s);
                log.info("{}文件夹下如下文件{}", s, strings);
                strings.forEach(p->{
                    new File(p).delete();
                });
            }
            deleteFile.delete();
            log.info("{}已被删除", s);
        }
        map.put("file", Arrays.asList("C:\\Users\\lejun\\Desktop\\world\\test\\短信"));
        log.info("已完成短信处理！");
        return map;
    }

    @Test
    public void zipTs() {
        dealVoice();
    }


}
