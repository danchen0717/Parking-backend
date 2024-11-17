package com.shanzhu.parking.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanzhu.parking.entity.dto.DateType;
import com.shanzhu.parking.entity.po.LoginInfo;
import com.shanzhu.parking.entity.po.User;
import com.shanzhu.parking.entity.query.UserQuery;
import com.shanzhu.parking.entity.vo.UserInfoVo;
import com.shanzhu.parking.mapper.LoginInfoMapper;
import com.shanzhu.parking.mapper.UserMapper;
import com.shanzhu.parking.service.UserService;
import com.shanzhu.parking.utils.IpUtils;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;


/**
 * 用户信息 服务层实现类
 *
 * @author: ChengZi
 * @date: 2024-10-02
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;

    private final LoginInfoMapper loginInfoMapper;

    /*   @Override
    public UserInfoVo login(User user) {
        if (user != null && StrUtil.isNotBlank(user.getUsername()) && StrUtil.isNotBlank(user.getPassword())) {
            //查询用户信息
            User existUser = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername,
                    user.getUsername()));

            //用户能查到
            if (existUser != null) {
                //密码匹配成
                if (user.getPassword().equals(existUser.getPassword())) {
                    //记录登录日志
                    recommendLoginInfo(existUser.getUsername(), IpUtils.getRequest());
                    return new UserInfoVo(existUser.getUid(), existUser.getRole(), true, "验证成功");
                } else {
                    return new UserInfoVo().setState(false).setMsg("密码错误");
                }
            } else {
                return new UserInfoVo().setState(false).setMsg("用户名不存在");
            }
        }

        return new UserInfoVo().setState(false).setMsg("用户名和密码不能为空");
    }
    */
    @Override
    public UserInfoVo login(User user) {
        if (user != null && StrUtil.isNotBlank(user.getUsername()) && StrUtil.isNotBlank(user.getPassword())) {
            // 查询用户信息
            User existUser = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername,
                    user.getUsername()));
    
            // 用户能查到
            if (existUser != null) {
                // 将输入的密码进行加密后，与数据库中的密码进行比较
                String hashedPassword = hashPassword(user.getPassword());
                if (hashedPassword.equals(existUser.getPassword())) {
                    // 记录登录日志
                    recommendLoginInfo(existUser.getUsername(), IpUtils.getRequest());
                    return new UserInfoVo(existUser.getUid(), existUser.getRole(), true, "验证成功");
                } else {
                    return new UserInfoVo().setState(false).setMsg("密码错误");
                }
            } else {
                return new UserInfoVo().setState(false).setMsg("用户名不存在");
            }
        }
    
        return new UserInfoVo().setState(false).setMsg("用户名和密码不能为空");
    }
    


    /**
     * 记录登录日志
     */
    public void recommendLoginInfo(String username, HttpServletRequest request) {
        LoginInfo loginInfo = new LoginInfo();
        // 获取客户端IP
        String ip = IpUtils.getIpAddress(request);
        // 获取客户端操作信息
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));

        loginInfo.setIp(ip);
        loginInfo.setLoginTime(LocalDateTime.now());
        loginInfo.setBrowser(userAgent.getBrowser().getName());
        loginInfo.setOs(userAgent.getOperatingSystem().getName());
        loginInfo.setPerson(username);
        loginInfoMapper.insert(loginInfo);
    }

    @Override
    public Boolean register(User user) {
        //没有用户信息
        if (user == null) {
            return false;
        }
        user.setCreateTime(LocalDateTime.now());

        //注册的用户不存在
        User existUser = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()));
        if (existUser == null) {
            //保存用户
            return this.save(user);
        }

        return false;
    }

    @Override
    public Boolean add(User user) {
        if (user != null && StrUtil.isNotBlank(user.getUsername())) {

            //查询用户不存在
            User existUser = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername,
                    user.getUsername()));
            if (existUser == null) {
                //保存用户信息
                return this.save(user);
            }
        }
        return false;
    }

    @Override
    public Boolean delUser(Integer uid) {
        return this.removeById(uid);
    }

    @Override
    public Boolean updateUser(User user) {
        return this.updateByUser(user);
    }

    @Override
    public Boolean updateByUser(User user) {
        return this.updateById(user);
    }

    @Override
    public boolean resetPassword(Integer uid) {
        //重置密码为123456
        User user = new User();
        user.setUid(uid);
        user.setPassword("123456");

        return this.updateById(user);
    }

    @Override
    public IPage<User> getUsersPage(UserQuery userQuery) {
        //分页信息
        Page<User> page = new Page<>(userQuery.getPagenum(), userQuery.getPageSize());

        return lambdaQuery()
                //模糊查询用户名
                .like(StrUtil.isNotBlank(userQuery.getUsername()), User::getUsername, userQuery.getUsername())
                //模糊查询姓名
                .like(StrUtil.isNotBlank(userQuery.getNike()), User::getNike, userQuery.getNike())
                //模糊查询车牌号
                .like(StrUtil.isNotBlank(userQuery.getCard()), User::getCard, userQuery.getCard())
                //分页查询
                .page(page);

    }

    @Override
    public Boolean userPay(User user) {
        User existUser = userMapper.selectById(user.getUid());
        if (existUser.getMoney() != null) {
            user.setMoney(existUser.getMoney() + user.getMoney());
        }
        return this.updateById(user);
    }

    @Override
    public List<DateType> getUserDataType(String person) {
        return userMapper.getUserDataType(person);
    }

    @Override
    public List<DateType> getUserDataMoney(String person) {
        return userMapper.getUserDataMoney(person);
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : messageDigest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error in hashing password", e);
        }
    }
    
    /**
     * 批量加密数据库中用户的明文密码
     */
    public void encryptExistingPasswords() {
        // 查询所有用户
        List<User> users = userMapper.selectList(null);

        for (User user : users) {
            // 如果密码不是加密状态，进行加密
            if (user.getPassword() != null && !user.getPassword().matches("^[a-fA-F0-9]{64}$")) { // 仅示例正则
                String hashedPassword = hashPassword(user.getPassword());
                user.setPassword(hashedPassword);
                // 更新到数据库
                userMapper.updateById(user);
            }
        }
        System.out.println("所有用户密码已加密！");
    }
}
