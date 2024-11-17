package com.shanzhu.parking.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shanzhu.parking.common.R;
import com.shanzhu.parking.entity.po.Stall;
import com.shanzhu.parking.entity.po.StallRes;
import com.shanzhu.parking.entity.query.StallCarQuery;
import com.shanzhu.parking.entity.query.StallQuery;
import com.shanzhu.parking.entity.query.StallResQuery;
import com.shanzhu.parking.entity.vo.MsgVo;
import com.shanzhu.parking.service.StallService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Parking space   control layer
 *
 * @author: Cheng Zi
 * @date: 2024-09-21
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/stall")
public class StallController {

    private final StallService stallService;

    /**
     * Get user's parked vehicle information
     *
     * @param stallCarQuery Vehicle Information
     * @return Parked vehicle information
     */
    @PostMapping("/pageStallCar")
    public R<IPage<Stall>> pageStallCar(@RequestBody StallCarQuery stallCarQuery) {
        return R.success(stallService.carPage(stallCarQuery));
    }

    /**
     * Get parking space information list
     *
     * @param stallQuery Parking space information
     * @return Parking space information
     */
    @PostMapping("/pageStall")
    public R<IPage<Stall>> pageStall(@RequestBody StallQuery stallQuery) {
        return R.success(stallService.pageStall(stallQuery));
    }

    /**
     * Reserve a parking space
     *
     * @param uid User id
     * @param sid Parking Space id
     * @return Result
     */
    @GetMapping("/orderStall")
    public R<Boolean> orderStall(Integer uid, Integer sid) {
        return R.success(stallService.orderStall(uid, sid));
    }

    /**
     * Add parking space
     *
     * @param stall Parking space information
     * @return Result
     */
    @PostMapping("/add")
    public R<MsgVo> addStall(@RequestBody Stall stall) {
        return R.success(stallService.addStall(stall));
    }

    /**
     * Update parking space
     *
     * @param stall Parking space information
     * @return Result
     */
    @PostMapping("/update")
    public R<MsgVo> updateStall(@RequestBody Stall stall) {
        return R.success(stallService.updateStall(stall));
    }

    /**
     * Delete parking space (logical deletion)
     *
     * @param sid Parking space id
     * @return Delete Results
     */
    @GetMapping("/del")
    public R<Boolean> deleteStall(Integer sid) {
        Stall stall = new Stall();
        stall.setSid(sid);
        //Status set to tombstone
        stall.setStallLive("0");
        return R.success(stallService.updateById(stall));
    }

    /**
     * Get all parking records of the user
     *
     * @param person Username
     * @return Parking space record
     */
    @GetMapping("/listUserStallRes")
    public R<List<StallRes>> listUserStallRes(String person) {
        return R.success(stallService.listUserStallRes(person));
    }

    /**
     * Get all the user's unpaid records
     *
     * @param person Username
     * @return Unpaid records
     */
    @GetMapping("/allNoPay")
    public R<Object> allNoPay(String person) {
        return R.success(
                stallService.getAllNoPay(person)
                        .stream().filter(r -> r.getOverTime() == null)
        );
    }

    /**
     * Query all parking payment records
     *
     * @param stallResQuery Parking payment information
     * @return Parking payment record
     */
    @PostMapping("/allList")
    public R<IPage<StallRes>> listStallRes(@RequestBody StallResQuery stallResQuery) {
        return R.success(stallService.getAllListStallRes(stallResQuery));
    }

    /**
     * Parking Payment (Administrator)
     *
     * @param stallRes Parking Information
     * @return Result
     */
    @PostMapping("/payMoney")
    public R<Boolean> payMoney(@RequestBody StallRes stallRes) {
        return R.success(stallService.payMoneyManager(stallRes));
    }

    /**
     * Parking payment (car owner)
     *
     * @param stallRes Parking Information
     * @return Payment Results
     */
    @PostMapping("/payMoneyPerson")
    public R<MsgVo> payMoneyPerson(@RequestBody StallRes stallRes) {
        return R.success(stallService.payMoneyPerson(stallRes));
    }

}

