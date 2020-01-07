package cn.tt.controller.Index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页常见请求
 *
 * @谢鑫
 */
@Controller
@RequestMapping("/")
public class PageController {

    /**
     * 默认访问首页
     * @return
     */
    @RequestMapping({"/","index"})
    public String doIndex(){
        return "index";
    }
}
