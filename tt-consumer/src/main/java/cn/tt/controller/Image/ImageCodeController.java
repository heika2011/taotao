package cn.tt.controller.Image;


import cn.tt.common.util.ImageCodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码前端显示
 *
 * @谢鑫
 */
@Controller
public class ImageCodeController {

    /**
     * 获取图像验证码
     * @param response
     * @throws IOException
     */
    @GetMapping("/imageCode")
    @ResponseBody
    public void getImageCode(HttpServletResponse response,String time_stmp) throws IOException {
        if(time_stmp==null){
            return;
        }else{
            response.setContentType("image/jpeg");
            //禁止图像缓存
            response.setHeader("Pragma","no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            ImageCodeUtil image = new ImageCodeUtil();
            ImageCodeUtil.writeImageCode(image.getImageCode(),"png",response.getOutputStream());
            System.out.println(image.getText());
        }
    }
}
