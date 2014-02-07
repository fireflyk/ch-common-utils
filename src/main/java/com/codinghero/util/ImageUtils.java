package com.codinghero.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import net.sf.json.JSONObject;

/**
 * no test, but will use in future
 * 
 * @author liutong628@gmail.com
 * 
 */
@Deprecated
public final class ImageUtils {
	
	public static boolean exists(String url){
		try {
			ImageIO.read(new URL(url));
			return true;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static String getSize(String url){
		try {
			BufferedImage img = ImageIO.read(new URL(url));
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("width", img.getWidth());
			map.put("height", img.getHeight());
			return JSONObject.fromObject(map).toString();
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException();
		} catch (IOException e) {
			throw new IllegalArgumentException();
		}
	}
	
	public static Map<String, Integer> getSize(File file){
		try {
			BufferedImage img = ImageIO.read(file);
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("width", img.getWidth());
			map.put("height", img.getHeight());
			return map;
		} catch (MalformedURLException e) {
			System.out.println(file.getPath());
			throw new IllegalArgumentException();
		} catch (IOException e) {
			System.out.println(file.getPath());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}
	}
	
	public static boolean scale(String origPath, String destPath, int scale) {
		try {
			// read orig image
			BufferedImage origImg = ImageIO.read(new File(origPath));
			// get orig width & height
			int width = origImg.getWidth();
			int height = origImg.getHeight();
			scale(origPath, destPath, width * scale, height * scale);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean scaleByWidth(String origPath, String destPath, int width) {
		try {
			BufferedImage origImg = ImageIO.read(new File(origPath));
			scale(origPath, destPath, width, origImg.getHeight() * width / origImg.getWidth());
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean scaleByHeight(String origPath, String destPath, int height) {
		try {
			BufferedImage origImg = ImageIO.read(new File(origPath));
			scale(origPath, destPath, origImg.getWidth() * height / origImg.getHeight(), height);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean scale(String origPath, String destPath, int width, int height) {
		try {
			// read orig file
			BufferedImage origImg = ImageIO.read(new File(origPath));
			Image image = origImg.getScaledInstance(width, height,
					Image.SCALE_AREA_AVERAGING);
			BufferedImage bufferedImage = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			return draw(origPath, destPath, image, bufferedImage);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean clipUp(String origPath, String destPath, int subHeight) {
		try {
			BufferedImage origImage = ImageIO.read(new File(origPath));
			return clip(origPath, destPath, 0, subHeight, origImage.getWidth(), origImage.getHeight() - subHeight);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean clipDown(String origPath, String destPath, int subHeight) {
		try {
			BufferedImage origImage = ImageIO.read(new File(origPath));
			return clip(origPath, destPath, 0, 0, origImage.getWidth(), origImage.getHeight() - subHeight);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean clip(String origPath, String destPath, int x, int y, int width, int height) {
		try {
			// read orig file
			BufferedImage origImg = ImageIO.read(new File(origPath));
			Image image = origImg.getSubimage(x, y, width, height);
			BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			return draw(origPath, destPath, image, bufferedImage);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean addWatermark(String origPath, String destPath, String text) {
		try {
			Image origImage = ImageIO.read(new File(origPath));

			BufferedImage bufferedImage = new BufferedImage(origImage.getWidth(null),
					origImage.getHeight(null), BufferedImage.TYPE_INT_RGB);

			// 得到画笔对象
			Graphics2D g = bufferedImage.createGraphics();

			// 设置对线段的锯齿状边缘处理
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
					RenderingHints.VALUE_INTERPOLATION_BILINEAR);

			g.drawImage(origImage.getScaledInstance(origImage.getWidth(null), origImage
					.getHeight(null), Image.SCALE_SMOOTH), 0, 0, null);

//			if (null != degree) {
//				// 设置水印旋转
//				g.rotate(Math.toRadians(degree),
//						(double) buffImg.getWidth() / 2, (double) buffImg
//								.getHeight() / 2);
//			}

			g.setColor(Color.WHITE);
			g.setFont(new Font("微软雅黑", Font.PLAIN, origImage.getWidth(null)/1024 * 25));
			final float alpha = 0.5f;
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
			g.drawString(text, origImage.getWidth(null) - origImage.getWidth(null)/1024*150, origImage.getHeight(null) - origImage.getHeight(null)/768*20);
			//g.drawString(text, 30, origImage.getHeight(null) - 15);
			g.dispose();

			// 生成图片
			ImageIO.write(bufferedImage, FileUtils.getFileType(origPath), new File(destPath));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private static boolean draw(String origPath, String destPath, Image image, BufferedImage bufferedImage) {
		try {
			Graphics g = bufferedImage.getGraphics();
			g.drawImage(image, 0, 0, null);
			g.dispose();
			FileUtils.newFolder(destPath);
			ImageIO.write(bufferedImage, FileUtils.getFileType(origPath), new File(destPath));
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static void main(String[] args) {
		String origPath = "F:\\crawl\\image\\b93429335a08bc706b31aef27356aa1e.jpg";
		String destPath = "F:\\crawl\\clip\\b93429335a08bc706b31aef27356aa1e.jpg";
		
		try {
			// read orig file
			BufferedImage origImg = ImageIO.read(new File(origPath));
			Image image = origImg.getSubimage(0, 0, 633, 9);
			BufferedImage bImage = new BufferedImage(633, 917,
					BufferedImage.TYPE_INT_RGB);
			Graphics g = bImage.getGraphics();
			// draw
			g.drawImage(image, 0, 0, null);
			g.dispose();
			FileUtils.newFolder(destPath);
			ImageIO.write(bImage, FileUtils.getFileType(origPath), new File(destPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
