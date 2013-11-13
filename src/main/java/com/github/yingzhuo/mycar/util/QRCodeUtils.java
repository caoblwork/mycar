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
package com.github.yingzhuo.mycar.util;

import java.awt.image.BufferedImage;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Hashtable;
import java.util.List;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;


public class QRCodeUtils {

	/** 私有构造方法 */
	private QRCodeUtils() { super(); }
	
	private static final Hashtable<EncodeHintType, Object> ZXING_HINTS;
	
	static {
		ZXING_HINTS = new Hashtable<EncodeHintType, Object>();
		ZXING_HINTS.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		ZXING_HINTS.put(EncodeHintType.CHARACTER_SET, "UTF-8");
	}
	
	/**
	 * 将二维码写入数据流
	 * 
	 * @param out 数据流
	 * @param lines 数据
	 * @param width 二维码宽度
	 * @param height 二维码高度
	 */
	public static void writeToOutputStream(OutputStream out, List<String> lines, int width, int height) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < lines.size(); i ++) {
			sb.append(lines.get(i));
			if (i != lines.size() - 1) {
				sb.append("\n");
			}
		}
		writeToOutputStream(out, sb.toString(), width, height);
	}
	
	/**
	 * 将二维码写入数据流
	 * 
	 * @param out 数据流
	 * @param contents 数据
	 * @param width 二维码宽度
	 * @param height 二维码高度
	 */
	public static void writeToOutputStream(OutputStream out, String contents, int width, int height) {
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height, ZXING_HINTS);
			MatrixToImageWriter.writeToStream(bitMatrix, "png", out);
		} catch (WriterException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	/**
	 * 将二维码写入文件
	 * 
	 * @param file 文件
	 * @param lines 数据
	 * @param width 二维码宽度
	 * @param height 二维码高度
	 */
	public static void writeToFile(File file, List<String> lines, int width, int height) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < lines.size(); i ++) {
			sb.append(lines.get(i));
			if (i != lines.size() - 1) {
				sb.append("\n");
			}
		}
		writeToFile(file, sb.toString(), width, height);
	}
	
	/**
	 * 将二维码写入文件
	 * 
	 * @param file 文件
	 * @param contents 数据
	 * @param width 二维码宽度
	 * @param height 二维码高度
	 */
	public static void writeToFile(File file, String contents, int width, int height) {
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height, ZXING_HINTS);
			MatrixToImageWriter.writeToFile(bitMatrix, "png", file);
		} catch (WriterException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * 解析二维码
	 * 
	 * @param image 图片
	 * @return 信息
	 */
	public static String decode(BufferedImage image) {
		Result result = null;
		LuminanceSource source = new BufferedImageLuminanceSource(image);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		try {
			result = new MultiFormatReader().decode(bitmap, ZXING_HINTS);
		} catch (NotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return result.getText();
	}
	
	/**
	 * 解析二维码
	 * 
	 * @param file 图片
	 * @return 信息
	 */
	public static String decode(File file) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return decode(image);
	}
	
	/**
	 * 解析二维码
	 * 
	 * @param input 图片
	 * @return 信息
	 */
	public static String decode(InputStream input) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(input);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return decode(image);
	}
	
	/**
	 * 解析二维码
	 * 
	 * @param url 图片
	 * @return 信息
	 */
	public static String decode(URL url) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(url);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return decode(image);
	}
	
	/**
	 * 关闭
	 * 
	 * @param closeable 关闭对象
	 */
	public static void closeQuietly(Closeable closeable) {
		if (closeable == null) return;
		try { closeable.close(); } catch (Exception e) {}
	}

}
