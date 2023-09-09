package com.heima.apis.wemedia;

import com.heima.model.common.dtos.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Date: 2023/7/29 22:56
 * @author: Qeem
 */
@FeignClient("leadnews-wemedia")
public interface IWemediaClient {
    @GetMapping("/api/v1/channel/list")
    public ResponseResult getChannels();
}
