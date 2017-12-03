package com.codecika.usermanagement.controller;

import com.codecika.usermanagement.exception.UserManagementException;
import com.codecika.usermanagement.util.Constants;
import com.codecika.usermanagement.util.RestUtil;
import com.codecika.usermanagement.vo.ResultVO;
import com.codecika.usermanagement.enums.StatusCode;
import com.codecika.usermanagement.vo.ResultPageVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.Map;

/**
 * Created by yukibuwana on 1/24/17.
 */

public abstract class AbstractRequestHandler {

    private static final Logger log = LoggerFactory.getLogger(AbstractRequestHandler.class);

    public ResponseEntity<ResultVO> getResult() {
        ResultVO result = new ResultVO();
        try {
            Object obj = processRequest();
            if (obj != null) {
                result.setMessage(StatusCode.OK.name());
                result.setResult(obj);
            }else {
                result.setMessage(StatusCode.OK.name());
                result.setResult(null);
            }
        } catch (UserManagementException e) {
            result.setMessage(e.getCode().name());
            result.setResult(e.getMessage());

            log.error("ERROR", e);
        }
        return RestUtil.getJsonResponse(result);
    }


    public abstract Object processRequest();

    public static ResponseEntity<ResultPageVO> constructListResult(Map<String, Object> pageMap) {
        ResultPageVO result = new ResultPageVO();
        try {
            Collection list = constructPageResult(pageMap, result);
            result.setResult(list);
        } catch (Exception e) {
            result.setMessage(e.getMessage());

            log.error("ERROR", e);
        }
        return RestUtil.getJsonResponse(result);
    }

    public static Collection constructPageResult(Map<String, Object> map, ResultPageVO result) {
        if (map == null) {
            result.setPages("0");
            result.setElements("0");
            result.setMessage(StatusCode.DATA_NOT_FOUND.name());
            return null;
        } else {
            Collection vos = (Collection) map.get(Constants.PageParameter.LIST_DATA);
            result.setPages(String.valueOf(map.get(Constants.PageParameter.TOTAL_PAGES)));
            result.setElements(String.valueOf(map.get(Constants.PageParameter.TOTAL_ELEMENTS)));
            result.setMessage(StatusCode.OK.name());
            return vos;
        }
    }
}
