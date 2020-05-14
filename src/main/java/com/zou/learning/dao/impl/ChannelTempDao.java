package com.zou.learning.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zou.learning.dao.IChannelTempDao;
import com.zou.learning.entity.ChannelTempDO;
import com.zou.learning.mapper.ChannelTempMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zouyaowen
 * @date 2020-05
 */
@Repository
public class ChannelTempDao implements IChannelTempDao {

    @Autowired
    private ChannelTempMapper channelTempMapper;

    @Override
    public List<ChannelTempDO> selectAll() {
        QueryWrapper<ChannelTempDO> query = new QueryWrapper<>();
        query.eq("valid",1);
        query.eq("msg_channel_id",2);
        // query.gt("id",796);
        // query.lt("id",942);
        ArrayList<Long> idList = new ArrayList<>();
        // idList.add(875L);
        // idList.add(908L);
        // idList.add(935L);
        query.eq("id",935L);
        return channelTempMapper.selectList(query);
    }
}
