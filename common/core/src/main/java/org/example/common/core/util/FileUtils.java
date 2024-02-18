package org.example.common.core.util;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ZipUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author:lejun
 * @project:cloud
 * @descript:对文件的基本操作：{下载zip，合成zip等操作}
 * @time: 2023/1/1 15:17
 */
@Slf4j
public class FileUtils {

    /**
     * 文件的压缩包生成
     *
     * @param filesNames
     * @param filePath
     * @param targetPath
     * @param name 新生成的zip文件名字
     * @param delete 是否删除原有的文件
     */
    public void buildZip(List<String> filesNames, Path filePath, Path targetPath,
                           String name, boolean delete) {
        try {
            List<File> fileList = new ArrayList<>();
            for (String fileName : filesNames) {
                Path resolve = filePath.resolve(fileName);
                File file = new File(resolve.toString());
                if (ObjectUtil.isNotEmpty(file)) {
                    fileList.add(file);
                }
            }
            File[] files = fileList.stream().toArray(File[]::new);
            Path composeName = targetPath.resolve(name);
            File zipFile = new File(composeName.toString());
            ZipUtil.zip(zipFile, true, files);
            log.info("文件{}已经压缩了！", name);
            if (delete) {
                for (File file : fileList) {
                    file.delete();
                }
            }
        } catch (Exception e) {
            log.error("压缩文件出错！", e) ;
        }
    }

    /**
     * 对压缩文件进行解压处理
     *
     * @param path
     * @param name
     */
    public void upZip(String path, String name) {

    }

    public static void main(String[] args) throws IOException {
        // C:\Users\lejun\Desktop\world\test
        String pathDir = "C:\\Users\\lejun\\Desktop\\world\\test";

        FileUtils fileUtils = new FileUtils();
        List<String> strings = readFileName(pathDir);
        log.info("================{}", strings);

//        List<String> strings = Arrays.asList("dir.zip", "语音.zip", "短信.zip");
//        Path path = Paths.get(pathDir);
//        fileUtils.buildZip(strings, path, path, "合成文件.zip", true);
    }



    /**
     * 只是粗略的读取所有的file的绝对路径！
     * @param path
     * @return
     * @throws IOException
     */
    public static List<String> readFileName(String path) throws IOException {
        Path startingDir = Paths.get(path);
        List<Path> result = new LinkedList();
        Files.walkFileTree(startingDir, new FindJavaVisitor(result));
        ArrayList<String> paths = new ArrayList<>();
        result.stream().forEach(Path-> {
            paths.add(Path.toString());
        });
        return paths;
    }

    /**
     * 只需要唯一的内容，不重复的内容！
     * @param path
     * @throws IOException
     */
    public static List<String> readOneFileName(String path) throws IOException {
        Path startingDir = Paths.get(path);
        List<Path> result = new LinkedList();
        Files.walkFileTree(startingDir, new FindJavaVisitor(result));
        // 去重。***.mp3和***(live).mp3
        Set<String> lives = new HashSet<>();
        Set<String> music = new HashSet<>();
        Set<String> distinctMusicName = new HashSet<>();
        distinctMusicName.addAll(music);
        List<Path> collectLive = result.stream().filter(s ->
                s.toString().contains("(live)")).collect(Collectors.toList());
        List<Path> collectMusic = result.stream()
                .filter(s->!collectLive.contains(s.toString()))
                .collect(Collectors.toList());
        for (String live : lives) {
            String realName = live.substring(live.lastIndexOf("("), live.lastIndexOf(")") + 1);
            if (!distinctMusicName.contains(realName)) {
                distinctMusicName.add(live);
            }
        }
        ArrayList<String> paths = new ArrayList<>();
        distinctMusicName.stream().forEach(Path-> {
            paths.add(Path);
        });
        return paths;
    }

    private static class FindJavaVisitor extends SimpleFileVisitor<Path> {
        private List result;

        public FindJavaVisitor(List result){
            this.result = result;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            //这里是需要对文件的名字记录
            result.add(file);
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            //访问到目录的时候 就会触发该方法 就可以对遍历的目录进行操作
            System.out.println("正在操作的目录是："+dir);

            return FileVisitResult.CONTINUE;
        }

        @Override
//       做完后呢
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            return super.postVisitDirectory(dir, exc);
        }
    }
}
