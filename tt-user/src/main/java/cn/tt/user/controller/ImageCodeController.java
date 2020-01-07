package cn.tt.user.controller;


import cn.tt.user.entity.User;
import cn.tt.user.josn.JsonResult;
import cn.tt.user.service.ImageCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @梁梓云
 */
@Controller
public class ImageCodeController {

    protected static final Integer SUCCESS=200;
    protected static final Integer FALSE=201;

    @Autowired
    ImageCodeService ics;

    /**
     *前端获取验证码图片
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/getImageCode")
    @ResponseBody
    public void  getImageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {

        BufferedImage image = ics.getImageCode();//获取验证码图片

        request.getSession().setAttribute("text",ics.getText());//将验证码文本内容放如session中
        ics.writeImageCode(image,response.getOutputStream());


    }

    /**
     * 获取用户输入验证码内容
     *
     * @return
     */
    @RequestMapping("/?????????????")
    @ResponseBody
    public JsonResult<Void> getCodeText(User user, HttpSession session)  {
       String userCode = user.getCode().toString();
        String Code =session.getAttribute("text").toString();
        if (!userCode.equals(Code)){
            return  new JsonResult<>(FALSE);
        }
        return  new JsonResult<>(SUCCESS);
    }


    }
