package com.shanzhu.parking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanzhu.parking.entity.po.StallType;
import org.apache.ibatis.annotations.Mapper;

/**
 * 车位类型 持久层
 *
 * @author: ShanZhu
 * @date: 2023-11-25
 */
@Mapper
public interface StallTypeMapper extends BaseMapper<StallType> {

}
