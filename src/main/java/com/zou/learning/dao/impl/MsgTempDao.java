package com.zou.learning.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zou.learning.dao.IMsgTempDao;
import com.zou.learning.entity.MsgTempDO;
import com.zou.learning.mapper.MsgTempMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * @author zouyaowen
 * @date 2020-05
 */
@Repository
public class MsgTempDao implements IMsgTempDao {

    @Autowired
    private MsgTempMapper msgTempMapper;

    @Override
    public List<MsgTempDO> getByIdList(List<Long> msgIdList) {
        if (CollectionUtils.isEmpty(msgIdList)) {
            return Collections.emptyList();
        }
        QueryWrapper<MsgTempDO> query = new QueryWrapper<>();
        query.eq("valid", 1);
        query.in("id", msgIdList);
        return this.msgTempMapper.selectList(query);
    }
}
