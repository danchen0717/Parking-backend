package com.shanzhu.parking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanzhu.parking.entity.po.Recharge;
import org.apache.ibatis.annotations.Mapper;

/**
 * 充值 持久层
 *
 * @author: ShanZhu
 * @date: 2023-11-25
 */
@Mapper
public interface RechargeMapper extends BaseMapper<Recharge> {

}
