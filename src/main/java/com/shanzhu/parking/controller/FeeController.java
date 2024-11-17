package com.shanzhu.parking.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.shanzhu.parking.common.R;
import com.shanzhu.parking.entity.po.Fee;
import com.shanzhu.parking.entity.po.User;
import com.shanzhu.parking.entity.vo.MsgVo;
import com.shanzhu.parking.service.FeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Parking fee. Control layer
 *
 * @author: Cheng Zi
 * @date: 2024-09-21
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/fee")
public class FeeController {

    private final FeeService feeService;

    /**
     * Query parking space type and fee information
     *
     * @param carType Parking space type
     * @return Payment Record
     */
    @GetMapping("/list")
    public R<List<Fee>> listFee(String carType) {
        return R.success(feeService.list(Wrappers.<Fee>lambdaQuery().like(Fee::getCarType, carType)));
    }

    /**
     * Update parking fee
     *
     * @param fee Parking space information
     * @return result
     */
    @PostMapping("update")
    public R<MsgVo> updateFee(@RequestBody Fee fee) {
        return R.success(feeService.updateFee(fee));
    }


    /**
     * User balance recharge
     *
     * @param user User Information
     * @return result
     */
    @PostMapping("/userFee")
    public R<Object> feeUser(@RequestBody User user) {
        return R.success(feeService.addUserFee(user));
    }

}

