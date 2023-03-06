package cloud.zfwproject.springboottemplate.controller;

import cloud.zfwproject.springboottemplate.service.PostService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 46029
 * @version 1.0
 * @description TODO
 * @date 2023/3/6 14:56
 */
@RestController
public class PostController {

    @Resource
    private PostService postService;

}
