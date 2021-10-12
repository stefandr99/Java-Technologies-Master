package com.fii.laboratory_2.business;

import javax.imageio.ImageIO;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

@WebServlet("/captcha")
public class CaptchaServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String captcha = generateCaptcha(6);

        BufferedImage image = new BufferedImage(150, 50, BufferedImage.OPAQUE);
        Graphics graphics = image.createGraphics();
        graphics.setFont(new Font("Arial", Font.BOLD, 15));
        graphics.setColor(Color.BLACK);
        graphics.drawString(captcha, 20, 20);

        HttpSession session = request.getSession();
        session.setAttribute("captcha", captcha);

        OutputStream outputStream = response.getOutputStream();
        ImageIO.write(image, "jpeg", outputStream);
        outputStream.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    private String generateCaptcha(int length) {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = upper.toLowerCase();
        String digits = "0123456789";

        String alphanum = upper + lower + digits;

        Random random = new Random();

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < length; i++) {
            int index = (random.nextInt() * alphanum.length());
            sb.append(alphanum.charAt(index));
        }

        return sb.toString();
    }
}
