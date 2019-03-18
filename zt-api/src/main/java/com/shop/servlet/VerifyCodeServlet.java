package com.shop.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 验证码生成
 */
public class VerifyCodeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	// 验证码宽度

	private static int width = 80;
	// 验证码高度

	private static int height = 30;
	// 长度

	private static int length = 4;

	public void destroy() {
		super.destroy();
	}

	public void init() throws ServletException {
		super.init();
	}

	public Color getRandColor(int s, int e) {
		Random random = new Random();
		if (s > 255)
			s = 255;
		if (e > 255)
			e = 255;
		int r, g, b;
		r = s + random.nextInt(e - s);

		g = s + random.nextInt(e - s);

		b = s + random.nextInt(e - s);

		return new Color(r, g, b);
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", 0);

		response.setContentType("image/jpeg");
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		Graphics g = image.getGraphics();

		Random random = new Random();
		Font mfont = new Font("宋体", Font.BOLD, 22);

		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);

		g.setFont(mfont);

		g.setColor(getRandColor(180, 200));

		StringBuffer send = new StringBuffer("");
		String temp = "";
		String str = "ABDEFHJKMNPQRSTUVWXYZ";

		for (int i = 0; i < length; i++) {
			temp = String.valueOf(str.charAt(random.nextInt(str.length())));
			send.append(temp);
			Color color = new Color(20 + random.nextInt(110), 20 + random.nextInt(110), random.nextInt(110));
			g.setColor(color);
			g.drawString(temp.toString(), 15 * i + 18, 20);
		}
		HttpSession session = request.getSession(true);
		session.setAttribute("verifyCode", send.toString());
		g.dispose();
		ImageIO.write(image, "JPEG", response.getOutputStream());
	}
}