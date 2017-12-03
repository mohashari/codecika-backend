package com.codecika.usermanagement.controller;

import com.codecika.usermanagement.util.RestUtil;
import com.codecika.usermanagement.exception.UserManagementException;
import com.codecika.usermanagement.vo.ResultPageVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

/**
 * Created by yukibuwana on 1/24/17.
 */

public abstract class AbstractRequestPageHandler {

    private static final Logger log = LoggerFactory.getLogger(AbstractRequestPageHandler.class);

    public ResponseEntity<ResultPageVO> getResult() {
        ResultPageVO result = new ResultPageVO();
        try {
            return processRequest();
        } catch (UserManagementException e) {
            result.setMessage(e.getCode().name());
            result.setResult(e.getMessage());

            log.error("ERROR", e);
        }
        return RestUtil.getJsonResponse(result);
    }

    public abstract ResponseEntity<ResultPageVO> processRequest();
}
