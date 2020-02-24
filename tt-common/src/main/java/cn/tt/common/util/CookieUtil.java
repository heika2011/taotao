package cn.tt.common.util;

import org.springframework.util.DigestUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

public class CookieUtil {
    public static void buydata(String aa, HttpServletResponse httpServletResponse){
        String a = DigestUtils.md5DigestAsHex(aa.getBytes());
        Cookie cookie = new Cookie("SSID",a);
        cookie.setPath("/");
        cookie.setMaxAge(60*5);
        httpServletResponse.addCookie(cookie);
    }

    public static Boolean getData(String aa,Cookie[] cookies) {
        if(aa.isEmpty()){
            throw new IllegalArgumentException("验证码为空");
        }
        for (Cookie s : cookies) {
            if ("SSID".equals(s.getName())) {
                if (DigestUtils.md5DigestAsHex(aa.getBytes()).equals(s.getValue())) {
                    return true;
                } else {
                    throw new IllegalArgumentException("验证码错误");

                }

            }

        }
        return  false;
    }
}
