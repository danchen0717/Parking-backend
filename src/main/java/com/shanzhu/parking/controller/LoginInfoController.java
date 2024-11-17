package com.shanzhu.parking.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shanzhu.parking.common.R;
import com.shanzhu.parking.entity.po.LoginInfo;
import com.shanzhu.parking.entity.query.LoginInfoQuery;
import com.shanzhu.parking.service.LoginInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Login Information  Control Layer
 *
 * @author: Cheng Zi
 * @date: 2024-09-21
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/login-info")
public class LoginInfoController {

    private final LoginInfoService loginInfoService;

    /**
     * Get the list of logged in users
     *
     * @param loginInfoQuery User Information
     * @return User List
     */
    @PostMapping("/getLoginInfoList")
    public R<IPage<LoginInfo>> getStallList(@RequestBody LoginInfoQuery loginInfoQuery) {
        return R.success(loginInfoService.getLoginInfoList(loginInfoQuery));
    }
}

