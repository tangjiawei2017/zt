package com.shop.servlet;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
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

	private static int interferingLineCount = 1;

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
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", 0);

		response.setContentType("image/jpeg");
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		Graphics g = image.getGraphics();

		Graphics2D g2d = (Graphics2D) g;

		Random random = new Random();
		Font mfont = new Font("����", Font.BOLD, 19);

		// g.setColor(getRandColor(200, 250));

		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);

		g.setFont(mfont);

		g.setColor(getRandColor(180, 200));

		for (int i = 0; i < interferingLineCount; i++) {
			int x = random.nextInt(width - 1);
			int y = random.nextInt(height - 1);
			int x1 = random.nextInt(6) + 1;
			int y1 = random.nextInt(12) + 1;
			BasicStroke bs = new BasicStroke(2f, BasicStroke.CAP_BUTT,
					BasicStroke.JOIN_BEVEL);

			Line2D line = new Line2D.Double(x, y, x + x1, y + y1);
			g2d.setStroke(bs);
			g2d.draw(line);

		}

		StringBuffer send = new StringBuffer("");
		String temp = "";
		String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

		for (int i = 0; i < length; i++) {
			switch (random.nextInt(2)) {
			case 0:
				temp = String.valueOf(random.nextInt(10));
				send.append(temp);
				break;
			case 1:
				temp = String.valueOf(str.charAt(random.nextInt(str.length())));
				send.append(temp);
				break;
			}
			Color color = new Color(20 + random.nextInt(110),
					20 + random.nextInt(110), random.nextInt(110));
			g.setColor(color);
			Graphics2D g2d_word = (Graphics2D) g;
			AffineTransform trans = new AffineTransform();
			trans.rotate((45) * 3.14 / 180, 15 * i + 8, 7);
			float scaleSize = random.nextFloat() + 0.8f;
			if (scaleSize > 1f)
				scaleSize = 1f;
			trans.scale(scaleSize, scaleSize);
			g2d_word.setTransform(trans);
			g.drawString(temp.toString(), 15 * i + 18, 14);
		}
		HttpSession session = request.getSession(true);
		session.setAttribute("verifyCode", send.toString());
		g.dispose();
		ImageIO.write(image, "JPEG", response.getOutputStream());
	}
}