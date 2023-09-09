package com.heima.apis.schedule;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.schedule.dtos.Task;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Date: 2023/7/23 1:04
 * @author: Qeem
 */
@FeignClient("leadnews-schedule")
public interface IScheduleClient {
    /**
     * 添加延时任务
     *
     * @param task 任务
     * @return long
     */
    @PostMapping("/api/v1/add")
    public ResponseResult addTask(@RequestBody Task task);

    /**
     * 取消任务
     *
     * @return boolean
     */
    @GetMapping("/api/v1/task/{taskId}")
    public ResponseResult cancelTask(@PathVariable("taskId") long taskId);

    /**
     * 按照类型和优先级来拉取任务
     *
     * @param type     类型
     * @param priority 优先级
     * @return {@link Task}
     */
    @GetMapping("/api/v1/task/{type}/{priority}")
    public ResponseResult poll(@PathVariable("type") int type,@PathVariable("priority") int priority);
}
