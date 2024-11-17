package com.shanzhu.parking.controller;


import com.shanzhu.parking.common.R;
import com.shanzhu.parking.entity.po.StallType;
import com.shanzhu.parking.service.StallTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Parking Space Type  Control Level
 *
 * @author: Cheng Zi
 * @date: 2024-09-24
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/stallType")
public class StallTypeController {

    private final StallTypeService stallTypeService;

    /**
     * Query parking space type
     *
     * @return Parking space type
     */
    @GetMapping("/list")
    public R<List<StallType>> getList() {
        return R.success(stallTypeService.list());
    }

}

