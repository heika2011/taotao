package cn.tt.common.util;



import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * 获取用户的真实IP
 * @梁梓云
 */
/**
 * 一般来说ip = request.getRemoteAddr();就能获得大部分用户的IP地址，但是如果客户端与服务器之间存在代理服务器
 * 例如Apache，Squid,nginx反向代理软件，服务器就不能获得真实的用户ip地址
 * 所以我们需要判断是否有反向代理
 */
public class GetUserIPUtil {

    /**
     * 判断是否有反向代理
     * 由于每款代理的软件的请求头都不一样所以多次判断
     *
     * @param request
     * @return
     */
    public  String getUserIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");//squid请求头
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");//Apache
        }
        //unknown这个属性是squid里的一个配置文件改的
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL- Proxy-Client-IP");//weblogic
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");//不知名代理
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");//NGINX
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();//没有中间代理或负载均衡
        }

        if (ip.contains(",")) {//判断是否为多级代理
            return ip.split(",")[0];//真实地址一般放在ip属性的最左边，当用户请求是代理会将请求ip放在最右边
        } else {
            return ip;
        }


    }

    /**
     *
     *通过ip获取起地址
     */

    public  String[] getIpInfo(String ip) throws Exception {

        //创建httpclient对象
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();

        //创建HttpPost对象,设置url访问地址
        HttpPost httpPost = new HttpPost("http://ip.taobao.com/service/getIpInfo2.php");
        //游览器请求头
        httpPost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36");

        //声明List集合,封装表单中的参数
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        //添加post参数
        params.add(new BasicNameValuePair("ip", ip));
        //创建表单中的Entity对象，第一个参数就是封装好的表单数据，第二个参数就是编码
        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(params, "utf8");
        //设置表单的Entity对象到Post请求中
        httpPost.setEntity(urlEncodedFormEntity);

        //发起post请求,并拿到response数据

        CloseableHttpResponse response = closeableHttpClient.execute(httpPost);
        //返回请求数据
        String context = EntityUtils.toString(response.getEntity());
        String[] info = new String[6];
        //正则匹配
        info[0]=getRE(context,"\"ip\":\"(.*?)\",");//ip
        info[1]=getRE(context,"\"country\":\"(.*?)\",");//国家
        info[2]=getRE(context,"\"region\":\"(.*?)\",");//省份
        info[3]=getRE(context,"\"city\":\"(.*?)\",");//城市
        info[4]=getRE(context,"\"county\":\"(.*?)\",");//县
        info[5]=getRE(context,"\"isp\":\"(.*?)\",");//运营商

        return info;
    }

    /**
     *  正则表达式方法
     * @param context 请求返回的数据
     * @param re   正则
     * @return 匹配结果
     */
    private String getRE(String context,String re){
        Pattern p= Pattern.compile(re);//匹配规则
        Matcher m=p.matcher(context);//匹配结果
        m.find();//判断是否存在

        return  m.group();//返回结果

    }

}