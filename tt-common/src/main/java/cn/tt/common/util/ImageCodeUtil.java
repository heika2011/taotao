package cn.tt.common.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * 图片验证码
 *基本就是java图形化生成一个图片的过程，字体，大小，背景，最后将图片的路径转入前端，前端显示，输入数据返回后端控制台作对比
 *
 * @梁梓云
 */
public class ImageCodeUtil {

    private int weight=100;
    private int height=40;
    //图片的宽和高（自定义）
    private String text;
    //保存验证码的文本内容
    private Random r = new Random();
    //随机获取数据
    private String [] fontNames ={"Georgia"};
    //显示的字体
    private String codes = "23456789abcdefghjkmnopqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ";
    //需要抽取字的数组
    /**
     * 获取验证码文本的方法
     *
     * @return
     */
    public String getText() {
        return text;
    }



    /**
     * 验证码图片的字体颜色
     * 借助AWT包的Color类
     * 获取随机的颜色
     *
     * @return 图片字体颜色
     */
    private Color randomColor() {
        int r = this.r.nextInt(225);
        //这里为什么是225，因为当r，g，b都为255时，即为白色，为了好辨认，需要颜色深一点。
        int g = this.r.nextInt(225);
        int b = this.r.nextInt(225);
        return new Color(r, g, b);
        //返回一个随机颜色
    }
    /**
     * 验证码图片的字体
     * 不过我只放了一种字体
     * 获取随机字体
     *
     * @return 字体
     */
    private Font randomFont() {
        int index = r.nextInt(fontNames.length);
        //获取随机的字体
        String fontName = fontNames[index];
        int style = r.nextInt(4);
        //随机获取字体的样式，0是无样式，1是加粗，2是斜体，3是加粗加斜体
        int size = r.nextInt(10) + 24;
        //随机获取字体的大小
        return new Font(fontName, style, size);
        //返回一个随机的字体
    }
    /**
     * 获取随机字符
     *
     * @return 字符
     */
    private char randomChar() {
        int index = r.nextInt(codes.length());
        return codes.charAt(index);
    }
    /**
     * 给验证码上线，主要为了防自动识别。
     *
     * @param image
     */
    private void drawLine(BufferedImage image) {
        //BufferedImage是image的实现类，是一个缓冲图形区，可在这个缓冲区内对图片进行修改

        int num = r.nextInt(10);
        //画num条线
        Graphics2D g = (Graphics2D) image.getGraphics();
        //Graphics2D   相当于画笔
        //
        for (int i = 0; i < num; i++) {
            int x1 = r.nextInt(weight);
            int y1 = r.nextInt(height);
            int x2 = r.nextInt(weight);
            int y2 = r.nextInt(height);
            g.setColor(randomColor());
            g.drawLine(x1, y1, x2, y2);
        }
    }

    /**
     * 创建图片
     * @return 图片
     */
    private BufferedImage createImage(){
        BufferedImage image = new BufferedImage(weight, height, BufferedImage.TYPE_INT_RGB);
        //创建图片缓冲区  BufferedImage.TYPE_INT_RGB 图片的类型
        Graphics2D g = (Graphics2D) image.getGraphics();
        //获取画笔
        g.setColor(new Color(255, 255, r.nextInt(245) + 10));
        g.fillRect(0,0,weight,height);//被修改颜色范围
        //设置图片背景颜色
        return  image;
    }

    /**
     * 获取验证码图片
     * @return
     */
    public BufferedImage getImageCode(){
        //获取照片
        BufferedImage image = createImage();
        //获取画笔
        Graphics2D g = (Graphics2D) image.getGraphics();
        StringBuilder sb = new StringBuilder();//不安全，速度快，不占内存
        for (int i=0; i<4; i++){
            String s=randomChar()+"";//获取随机字符，但是没有字符的写入方式，所以改成字符串；
            sb.append(s);
            float x = i * 1.0F * weight / 4;   //定义字符的x坐标
            g.setFont(randomFont());           //设置字体，随机
            g.setColor(randomColor());         //设置颜色，随机
            g.drawString(s, x, height - 5);

        }
        this.text=sb.toString();
        drawLine(image);

        return  image;
    }
    //将验证码图片写出的方法
    public static void writeImageCode(BufferedImage image, OutputStream out) throws IOException {
        ImageIO.write(image, "JPEG", out);


    }


}
