package org.example.common.core.util.fileDispose;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: lejun
 * @project: cloud
 * @description: 对文件中不合适的注释位置进行处理
 * @time: 2023/11/23 14:23
 */
@Slf4j
public class FileCommentUtil {
    public static void processFile(String code) {
        String pattern = "^(.*?)(\\s*//.*)$";

        Pattern r = Pattern.compile(pattern, Pattern.MULTILINE);
        Matcher m = r.matcher(code);

        StringBuffer result = new StringBuffer();

        while (m.find()) {
            String codeSegment = m.group(1).trim();
            String comment = m.group(2).trim();
            m.appendReplacement(result, comment + "\n" + codeSegment);
        }
        m.appendTail(result);
        System.out.println(result);
    }

    private static void processFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder codeBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                codeBuilder.append(line).append("\n");
            }

            String code = codeBuilder.toString();
            String pattern = "^(.*?)(\\s*//.*)$";

            Pattern r = Pattern.compile(pattern, Pattern.MULTILINE);
            Matcher m = r.matcher(code);

            StringBuffer result = new StringBuffer();

            while (m.find()) {
                String codeSegment = m.group(1).trim();
                String comment = m.group(2).trim();
                m.appendReplacement(result, comment + "\n" + codeSegment);
            }
            m.appendTail(result);

            // 将处理后的内容写回文件
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(result.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void dealFileComment(String folderPath) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        if (files != null) {
            // 创建一个固定大小的线程池
            ThreadFactory namedThreadFactory = new CustomThreadFactory("file dispose");
            ExecutorService executorService = new ThreadPoolExecutor(5, 10,
                    0L, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());


            // 使用Future来保存任务的执行结果
            List<Future<Void>> futures = new ArrayList<>();

            for (File file : files) {
                if (file.isFile()) {
                    // 提交任务到线程池，并保存Future
                    Future<Void> future = executorService.submit(() -> {
                        processFile(file);
                        return null;
                    });
                    futures.add(future);
                }
            }

            // 等待所有任务完成
            for (Future<Void> future : futures) {
                try {
                    future.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }

            // 关闭线程池
            executorService.shutdown();
        }
    }

    // 自定义线程工厂
    private static class CustomThreadFactory implements ThreadFactory {
        private final String threadName;

        public CustomThreadFactory(String threadName) {
            this.threadName = threadName;
        }

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, threadName);
        }
    }
}
