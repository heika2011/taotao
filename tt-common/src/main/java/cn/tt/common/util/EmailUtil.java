package cn.tt.common.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

/**
 *  邮箱发送
 * @吴洋
 */
public class EmailUtil {
    /**
     *
     * @param contents 文本内容
     * @param send  发送者
     * @param inbox 邮件接受者
     * @param title  邮件标题
     * @return  true 成功 false失败
     * @throws Exception
     */
    public static boolean Email(String contents,String send,String inbox,String title){
        Properties props=CreateProperties();
        //1.基于上述参数创建Session对象
        Session session = Session.getInstance(props, new Authenticator() {
            @Override //定义真实发送者的信息，密码是：客户端授权码
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("18782793944@163.com", "wuyang1");
            }
        });
        //2.创建Message对象
        Message message = new MimeMessage(session);
        //2.1设置发件人地址
        try {
            message.setFrom(new InternetAddress(send));
            //2.2设置收件人
            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(inbox));
            //TO：发送给谁，CC：抄送给谁，BCC：密送给谁
            //2.2.1 增加收件人
            message.addRecipient(Message.RecipientType.CC, new InternetAddress(send));
//2.3设置邮件主题
            message.setSubject(title);
            //2.4设置邮件正文
            String content = contents;
            message.setContent(content, "text/html;charset=utf-8");
            //2.5 设置附件
            //3.发送邮件
            Transport.send(message);
            System.out.println(123);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }

    }

    private static Properties CreateProperties() {
        Properties props = new Properties();
        //表明使用smtp协议
        props.setProperty("mail.transport.protocol", "smtp");
        //设置SMTP服务器地址
        props.setProperty("mail.smtp.host", "smtp.163.com");
        //设置端口(可忽略)
        props.put("mail.smtp.port", 25);
        //开启验证
        props.setProperty("mail.smtp.auth", "true");
        return props;
    }

    private static String CreateRandom() {
        String s="";
        {
            Random random = new Random();

            for (int i=0;i<6;i++){
                int i1 = random.nextInt(10);
                s += Integer.toString(i1);
            }
        }
        return null;
    }

    public static boolean EmailCode(String send,String inbox,String title){
        String s = CreateRandom();
        //网易邮箱为例：
        Properties props=CreateProperties();

        //1.基于上述参数创建Session对象
        Session session = Session.getInstance(props, new Authenticator() {
            @Override //定义真实发送者的信息，密码是：客户端授权码
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("18782793944@163.com", "wuyang1");
            }
        });
        //2.创建Message对象
        Message message = new MimeMessage(session);
        //2.1设置发件人地址
        try {
            message.setFrom(new InternetAddress(send));
            //2.2设置收件人
            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(inbox));
            //TO：发送给谁，CC：抄送给谁，BCC：密送给谁
            //2.2.1 增加收件人
            message.addRecipient(Message.RecipientType.CC, new InternetAddress(send));
//2.3设置邮件主题
            message.setSubject(title);
            //2.4设置邮件正文
            String content = "验证码:"+s;
            message.setContent(content, "text/html;charset=utf-8");
            //2.5 设置附件
            //3.发送邮件
            Transport.send(message);
            System.out.println(123);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }

    }


    public static void main(String[] args) {
      //  Email("yanzma","18782793944@163.com","964070059@qq.com","渣男");
        EmailCode("18782793944@163.com","964070059@qq.com","渣男");
    }
}


