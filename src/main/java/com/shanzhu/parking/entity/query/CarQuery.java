package com.shanzhu.parking.entity.query;


import lombok.Data;

/**
 * 车辆信息 查询对象
 *
 * @author: ShanZhu
 * @date: 2023-12-02
 */
@Data
public class CarQuery {

    /**
     * 人员
     */
    private String person;

    /**
     * 车辆类型
     */
    private String carType;

    /**
     * 证件号码
     */
    private String card;

    /**
     * 分页页码
     */
    private Integer pagenum;

    /**
     * 分页大小
     */
    private Integer pageSize;

}
