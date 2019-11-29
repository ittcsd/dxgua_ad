package com.dxgua.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author caoshudong
 * @date 2019/11/19
 */
@Data
@NoArgsConstructor // 无参构造函数
@AllArgsConstructor // 全参构造函数
public class CommonResponse<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;

    public CommonResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
