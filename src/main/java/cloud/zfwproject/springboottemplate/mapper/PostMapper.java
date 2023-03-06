package cloud.zfwproject.springboottemplate.mapper;

import cloud.zfwproject.springboottemplate.model.po.Post;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 46029
* @description 针对表【post(帖子)】的数据库操作Mapper
* @createDate 2023-03-05 22:13:09
* @Entity cloud.zfwproject.springboottemplate.model.po.Post
*/
@Mapper
public interface PostMapper extends BaseMapper<Post> {

}




