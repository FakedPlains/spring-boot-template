package cloud.zfwproject.springboottemplate.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cloud.zfwproject.springboottemplate.model.po.Post;
import cloud.zfwproject.springboottemplate.service.PostService;
import cloud.zfwproject.springboottemplate.mapper.PostMapper;
import org.springframework.stereotype.Service;

/**
* @author 46029
* @description 针对表【post(帖子)】的数据库操作Service实现
* @createDate 2023-03-05 22:13:09
*/
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post>
    implements PostService{

}




