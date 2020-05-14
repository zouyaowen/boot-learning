package com.zou.learning.dao;

import com.zou.learning.entity.ChannelTempDO;

import java.util.List;

/**
 * @author zouyaowen
 * @date 2020-05
 */
public interface IChannelTempDao {

    List<ChannelTempDO> selectAll();
}
