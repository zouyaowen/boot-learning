package com.zou.learning.dao;

import com.zou.learning.entity.MsgTempDO;

import java.util.List;

/**
 * @author zouyaowen
 * @date 2020-05
 */
public interface IMsgTempDao {

    List<MsgTempDO> getByIdList(List<Long> msgIdList);
}
