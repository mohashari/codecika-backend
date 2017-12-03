package com.codecika.usermanagement.util;

import com.codecika.usermanagement.vo.ResultVO;
import com.codecika.usermanagement.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class CallService {
    private static final Logger log = LoggerFactory.getLogger(CallService.class);

    public static ResponseEntity<ResultVO> getCheckService(String url) {
        // set content type
        List<MediaType> list = new ArrayList<>();
        list.add(MediaType.APPLICATION_JSON);

        // set header type
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(list);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<UserVO> requestEntity = new HttpEntity<>(null, headers);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        log.info("URL: " + url);

        return restTemplate.exchange(url, HttpMethod.GET, requestEntity, ResultVO.class);
    }
}
