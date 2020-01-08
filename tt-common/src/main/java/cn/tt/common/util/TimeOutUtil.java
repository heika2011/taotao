package cn.tt.common.util;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 无恙
 * @判断cookie是否存在
 */
public class TimeOutUtil {
    //判读cookie是否存在

    /**
     *
     * @param request
     * @param cookieName  cookie名字
     * @param time   存活时间
     * @return
     */
    public static boolean findCookie(HttpServletRequest request ,String cookieName,Long time){
        //获取cookie
        Cookie[] cookies = request.getCookies();

        for (Cookie s:cookies ) {
            //cookie是否为空
            if(StringUtils.isEmpty(s)){
                return false;
            }else{
                //当前cookie是否存在
                if(StringUtils.isEmpty(cookieName.equals(s.getName()))){
                    return false;
                }else {
                    if(cookieName.equals(s.getName())){
                        //获取值
                        String value = s.getValue();
                        //类型转换
                        Long start = Long.valueOf(value);
                        //当前时间戳
                        long ent = System.currentTimeMillis();
                        //时间戳是否小于5分钟
                        if((ent-start)<time){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }


}
