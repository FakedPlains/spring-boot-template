package cloud.zfwproject.springboottemplate.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cloud.zfwproject.springboottemplate.model.po.User;
import cloud.zfwproject.springboottemplate.service.UserService;
import cloud.zfwproject.springboottemplate.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 46029
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2023-03-05 22:12:53
*/
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




