package com.zou.learning.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zouyaowen
 * @date 2020-05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MsgParamDTO {
    private String appCode;
    private String mobile;
    /**
     * 短信平台模板编号
     */
    private String tempCode;
    private JSONObject tempParam;
}
