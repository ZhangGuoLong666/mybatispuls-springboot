package com.zgl.feign.service;

import com.zgl.feign.entity.MailEntity;
import com.zgl.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;



@FeignClient(name = "zgl-message")
public interface FeignMessageService {
    @PostMapping("/mail/sendmail")
    R sendMail(MailEntity mailEntity);

}
