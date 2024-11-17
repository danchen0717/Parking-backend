package com.shanzhu.parking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanzhu.parking.entity.po.Fee;
import org.apache.ibatis.annotations.Mapper;

/**
 * 车辆收费信息 持久层
 *
 * @author: ShanZhu
 * @date: 2023-11-25
 */
@Mapper
public interface FeeMapper extends BaseMapper<Fee> {

}
