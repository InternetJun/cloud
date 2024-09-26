package org.example.common.core.problem;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: lejun
 * @project: cloud
 * @description: 用来维护starkLink的任务表，有唯一键的越约束
 * @time: 2023/6/18 20:23
 */
@Slf4j
public class UpdateTask {
    static Map<String, String> taskMap = new HashMap<>();
    static LinkedList task = new LinkedList<>();
    public void update(String bckType, List<String> tasks) {

        // 1. 查出数据库中是否存在
        if (ObjectUtil.isEmpty(tasks)) {
            // 插入新的任务
            System.out.println("a new task will be insert into db!");
            taskMap.put("bk", "a new bk task is on");
            task.add("bk");
        } else {
            /**
             * 1, bk --> bk
             * 2, (bk, ck) --> ck 是什么呢？
             * 3, bk --> ck 有需要更新bk任务 -->  更新时间，处理的状态。
             */
            List<String> notSameTask = tasks.stream().filter(m -> !m.equals(bckType)).collect(Collectors.toList());
//            String sameTask = tasks.stream().filter(m -> m.equals(bckType)).collect(Collectors.toList()).get(0);
//            // 相同的
//            if (ObjectUtil.isEmpty(sameTask)) {
//                // new task into db
//
//            }

            if (ObjectUtil.isNotEmpty(notSameTask) && tasks.size() == 1) {
                // insert
                taskMap.put("ck", "a new ck Task!");
                taskMap.put("bk", "a end bk task");
                task.add("ck");
            }
        }
    }

    public static void main(String[] args) {

        UpdateTask updateTask = new UpdateTask();
        updateTask.update("bk", null);
        log.info("{}\n{}", taskMap, task);
        updateTask.update("ck", Arrays.asList("bk"));
        log.info("{}\n{}", taskMap, task);
//        updateTask.update("ck", Arrays.asList("bk", "ck"));
//        log.info("{}\n{}", taskMap, task);

    }

}
