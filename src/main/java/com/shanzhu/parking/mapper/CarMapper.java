package com.shanzhu.parking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanzhu.parking.entity.po.Car;
import org.apache.ibatis.annotations.Mapper;

/**
 * 车辆 持久层
 *
 * @author: ShanZhu
 * @date: 2023-11-25
 */
@Mapper
public interface CarMapper extends BaseMapper<Car> {

}
