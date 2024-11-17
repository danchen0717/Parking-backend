package com.shanzhu.parking.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanzhu.parking.entity.po.LoginInfo;
import com.shanzhu.parking.entity.query.LoginInfoQuery;
import com.shanzhu.parking.mapper.LoginInfoMapper;
import com.shanzhu.parking.service.LoginInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 登录信息 服务层实现类
 *
 * @author: ShanZhu
 * @date: 2023-12-02
 */
@Service
@RequiredArgsConstructor
public class LoginInfoServiceImpl extends ServiceImpl<LoginInfoMapper, LoginInfo> implements LoginInfoService {

    @Override
    public IPage<LoginInfo> getLoginInfoList(LoginInfoQuery loginInfoQuery) {
        //分页条件
        Page<LoginInfo> page = new Page<>(loginInfoQuery.getPagenum(), loginInfoQuery.getPageSize());

        //查询条件
        return lambdaQuery()
                //模糊查询用户名用户名
                .like(StrUtil.isNotBlank(loginInfoQuery.getPerson()),LoginInfo::getPerson, loginInfoQuery.getPerson())
                //模糊查询ip
                .like(StrUtil.isNotBlank(loginInfoQuery.getIp()),LoginInfo::getIp, loginInfoQuery.getIp())
                .page(page);
    }

}
