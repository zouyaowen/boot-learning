package com.zou.learning.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author zouyaowen
 * @date 2020-05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("mh_msg_temp")
public class MsgTempDO {
    /**
     * 系统ID
     */
    private Long id;
    /**
     * 标签系统ID
     */
    private String msgSignId;
    /**
     * 模版编号
     */
    private String tempCode;
    /**
     * 模版名称
     */
    private String tempName;
    /**
     * 消息类型: 1.验证码 2.短信通知 3.推广短信4.钉钉消息
     */
    private Integer tempType;
    /**
     * 模板内容
     */
    private String tempContent;
    /**
     * 模板参数
     */
    private String tempParams;
    /**
     * 审核状态: 0.未审核 1.审核通过 2.审核未通过
     */
    private String verifyStatus;
    /**
     * 审核备注
     */
    private String verifyRemark;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
    /**
     * 是否有效: 0.无效 1.有效
     */
    private Integer valid;
}
