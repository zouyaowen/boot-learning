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
@AllArgsConstructor
@NoArgsConstructor
@TableName("mh_channel_temp")
public class ChannelTempDO {
    /**
     * 系统ID
     */
    private Long id;
    /**
     * 模板消息系统ID
     */
    private Long msgTempId;
    /**
     * 消息通道唯一标识
     */
    private Integer msgChannelId;
    /**
     * 渠道模板编号
     */
    private String msgChannelTempCode;
    /**
     * 模板消息对应的渠道模板消息
     */
    private String msgChannelTempContent;
    /**
     * 消息模板参数
     */
    private String msgChannelTempParams;
    /**
     * 审核状态：0.未审核  1.审核通过  2.审核不通过
     */
    private Integer verifyStatus;
    /**
     * 审核备注
     */
    private String verifyRemark;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 是否有效 0 无效  1有效
     */
    private Integer valid;
}
