package com.codecika.usermanagement.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Created by yukibuwana on 1/24/17.
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultPageVO extends ResultVO {

    private String pages;
    private String elements;

    public ResultPageVO() {
    }

    public ResultPageVO(String pages, String elements) {
        this.pages = pages;
        this.elements = elements;
    }
}
