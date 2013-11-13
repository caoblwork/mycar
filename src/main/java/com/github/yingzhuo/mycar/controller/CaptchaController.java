/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.yingzhuo.mycar.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.yingzhuo.mycar.annotation.Comment;

//@Controller
@Deprecated
@Comment("参考为目的保留使用jcaptcha.jar")
public class CaptchaController {

	public static final String CAPTCHA_SESSION_ATTR_NAME = CaptchaController.class.getName() + "#CAPTCHA_SESSION_ATTR_NAME";

	private static final HttpHeaders HTTP_HEADERS;
	private static final Random RANDOM;

	static {
		HTTP_HEADERS = new HttpHeaders();
		HTTP_HEADERS.set("Pragma", "No-cache");
		HTTP_HEADERS.set("Cache-Control", "No-cache");
		HTTP_HEADERS.setDate("Expires", 0);
		HTTP_HEADERS.setContentType(MediaType.IMAGE_JPEG);
		RANDOM = new Random();
	}

	private int width;
	private int height;

	@RequestMapping(value = "/images/captcha.jpeg")
	public ResponseEntity<byte[]> captcha(HttpSession session) throws IOException {

		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		g.setColor(getRandColor(200, 250));
		g.fillRect(1, 1, width - 1, height - 1);
		g.setColor(new Color(102, 102, 102));
		g.drawRect(0, 0, width - 1, height - 1);
		g.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		g.setColor(getRandColor(160, 200));

		// 画随机线
		for (int i = 0; i < 155; i++) {
			int x = RANDOM.nextInt(width - 1);
			int y = RANDOM.nextInt(height - 1);
			int xl = RANDOM.nextInt(6) + 1;
			int yl = RANDOM.nextInt(12) + 1;
			g.drawLine(x, y, x + xl, y + yl);
		}

		// 从另一方向画随机线
		for (int i = 0; i < 70; i++) {
			int x = RANDOM.nextInt(width - 1);
			int y = RANDOM.nextInt(height - 1);
			int xl = RANDOM.nextInt(12) + 1;
			int yl = RANDOM.nextInt(6) + 1;
			g.drawLine(x, y, x - xl, y - yl);
		}
		
		// 生成随机数,并将随机数字转换为字母
		String captchaStr = "";
		for (int i = 0; i < 6; i++) {
			int itmp = RANDOM.nextInt(26) + 65;
			char ctmp = (char) itmp;
			captchaStr += String.valueOf(ctmp);
			g.setColor(new Color(20 + RANDOM.nextInt(110), 20 + RANDOM.nextInt(110), 20 + RANDOM.nextInt(110)));
			g.drawString(String.valueOf(ctmp), 15 * i + 10, 16);
		}
		g.dispose();
		
		session.setAttribute(CAPTCHA_SESSION_ATTR_NAME, captchaStr);
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ImageIO.write(image, "JPEG", out);

		try {
			return new ResponseEntity<byte[]>(out.toByteArray(), HTTP_HEADERS, HttpStatus.OK);
		} finally {
			IOUtils.closeQuietly(out);
		}
	}

	private Color getRandColor(int fc, int bc) {
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + RANDOM.nextInt(bc - fc);
		int g = fc + RANDOM.nextInt(bc - fc);
		int b = fc + RANDOM.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	// access method
	// ----------------------------------------------------------------------------------------------------------------------

	@Value("100")
	public void setWidth(int width) {
		this.width = width;
	}

	@Value("18")
	public void setHeight(int height) {
		this.height = height;
	}
	
}

