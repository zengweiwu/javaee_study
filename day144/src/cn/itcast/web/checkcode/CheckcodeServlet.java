package cn.itcast.web.checkcode;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/checkcodeServlet")
public class CheckcodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1 获取取图片对象
        int width = 250;
        int height = 50;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //2 美化图片
        //2.1获取画笔
        Graphics graphics = image.getGraphics();
        graphics.setColor(Color.pink);
        //2.2填充颜色
        graphics.fillRect(0,0,width,height);
        //2.3设置边框
        graphics.setColor(Color.green);
        graphics.drawRect(0,0,width-1,height-1);
        //2.4写字母数字
        String charandnums = "ABCDEFGHIJKLMNOPQRSTUVWSYZabcdefghijklmnopqrstuvwxyz0123456789";
        graphics.setColor(Color.blue);
        Random random = new Random();
        for (int i = 1; i <= 4; i++) {
            int index = random.nextInt(charandnums.length());
            char ch = charandnums.charAt(index);
            graphics.drawString(ch + "",width/5*i,height/2);
        }
        //2.5画线
        graphics.setColor(Color.green);
        for (int i = 0; i < 10; i++) {
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int x2 = random.nextInt(width);
            int y2 = random.nextInt(height);
            graphics.drawLine(x1,y1,x2,y2);
        }

        //3 发送图片
        ImageIO.write(image,"jpg",response.getOutputStream());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
