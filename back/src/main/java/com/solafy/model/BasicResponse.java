package com.solafy.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class BasicResponse {
    @ApiModelProperty(value = "응답 성공 여부", position = 1)
    public String status;
    @ApiModelProperty(value = "응답 메시지", position = 2)
    public String message;
    @ApiModelProperty(value = "응답 데이터", position = 3)
    public Object data;
}