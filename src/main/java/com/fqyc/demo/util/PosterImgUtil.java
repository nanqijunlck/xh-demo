package com.fqyc.demo.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.List;

/**
 * @author lhs
 * @desc 画布工具类
 * @date 2021/8/9 10:46
 * @since 1.0
 */
@Slf4j
public final class PosterImgUtil {

//    public static void main(String[] args) throws IOException {
//        // 文字放到透明层 再放到背景图上
//        InputStream backGround = new FileInputStream(new File("C:\\Users\\lhs\\Desktop\\daily-poster-bg.png"));
//
//        BufferedImage b = ImageIO.read(new File("C:\\Users\\lhs\\Desktop\\daily-poster-bg.png"));
//        int i = b.getWidth() / 2;
//        int j = b.getWidth() / 3;
//
//        // 今日拉新值
//        BufferedImage bi = PosterImgUtil.writeTransparentFont(
//                new PosterImgUtil.FontStyle(0, -16, "params.getStr(p1)",
//                        Color.red, new Font("微软雅黑", Font.BOLD, 32), Boolean.FALSE), i);
//
//        ImageIO.write(bi, "png", new File("C:\\Users\\lhs\\Desktop\\1111.png"));
//    }

    public static BufferedImage createImage(String content, int qrCodeSize) throws Exception {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, qrCodeSize, qrCodeSize, hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        return image;
    }

    private static InputStream bufferedImageToInputStream(BufferedImage image) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "PNG", os);
        } catch (IOException e) {
            log.error("流转换失败");
            throw new RuntimeException("海报生成失败");
        }
        return new ByteArrayInputStream(os.toByteArray());
    }

    public static BufferedImage watermark(ImgStyle imgStyle) throws IOException {
        long start = System.currentTimeMillis();
        // 获取底图
        BufferedImage buffImg = imgStyle.getBuffImg();
        if (null == buffImg) {
            buffImg = ImageIO.read(imgStyle.getSourceImg());
        }
        if (imgStyle.getInsertImg() == null) {
            return buffImg;
        }
        // 获取层图
        BufferedImage waterImg = ImageIO.read(imgStyle.getInsertImg());
        // 设置层图圆角
        if (imgStyle.getRadius() > 0) {
            waterImg = makeCircularImg(waterImg, imgStyle.getWidth(), imgStyle.getRadius());
        }
        // 创建Graphics2D对象，用在底图对象上绘图
        Graphics2D g2d = buffImg.createGraphics();
        //消除画图锯
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // 在图形和图像中实现混合和透明效果
        // g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 1.0f));
        // 绘制
        g2d.drawImage(waterImg, imgStyle.getStartX(), imgStyle.getStartY(), imgStyle.getWidth(), imgStyle.getHeight(), null);
        g2d.dispose();// 释放图形上下文使用的系统资源
        log.info("画图耗时 {}", System.currentTimeMillis()-start);
        return buffImg;
    }

    /***
     *
     * @param bufferedImage 源图片
     * @param targetSize 文件的边长，单位：像素，最终得到的是一张正方形的图，所以要求targetSize<=源文件的最小边长
     * @param cornerRadius 圆角半径，单位：像素。如果=targetSize那么得到的是圆形图
     * @return 文件的保存路径
     * @throws IOException
     */
    public static BufferedImage makeCircularImg(BufferedImage bufferedImage, int targetSize, int cornerRadius) throws IOException {
        bufferedImage = resizeImg(bufferedImage, targetSize, 1);
        BufferedImage circularBufferImage = roundImage(bufferedImage, targetSize, cornerRadius);
        return circularBufferImage;
    }

    private static BufferedImage roundImage(BufferedImage image, int targetSize, int cornerRadius) {
        BufferedImage outputImage = new BufferedImage(targetSize, targetSize, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = outputImage.createGraphics();
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Float(0, 0, targetSize, targetSize, cornerRadius, cornerRadius));
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
        return outputImage;
    }

    /**
     * @param originalFile 原文件
     * @param newWidth     压缩后的图片宽度
     * @param quality      压缩质量（0到1之间，越高质量越好）
     * @throws IOException
     */
    public static BufferedImage resizeImg(BufferedImage originalFile, int newWidth, float quality) throws IOException {
        if (quality > 1) {
            throw new IllegalArgumentException("Quality has to be between 0 and 1");
        }

        ImageIcon ii = new ImageIcon(originalFile);
        Image i = ii.getImage();
        Image resizedImage = null;

        int iWidth = i.getWidth(null);
        int iHeight = i.getHeight(null);
        if (iWidth > iHeight) {
            resizedImage = i.getScaledInstance(newWidth, (newWidth * iHeight) / iWidth, Image.SCALE_SMOOTH);
        } else {
            resizedImage = i.getScaledInstance((newWidth * iWidth) / iHeight, newWidth, Image.SCALE_SMOOTH);
        }

        // 获取图片中的所有像素
        Image temp = new ImageIcon(resizedImage).getImage();

        // 创建缓冲
        BufferedImage bufferedImage = new BufferedImage(temp.getWidth(null), temp.getHeight(null), BufferedImage.TYPE_INT_RGB);

        // 复制图片到缓冲流中
        Graphics2D g = bufferedImage.createGraphics();

        // 清除背景并开始画图
        g.setColor(Color.white);
        g.fillRect(0, 0, temp.getWidth(null), temp.getHeight(null));
        g.drawImage(temp, 0, 0, null);
        g.dispose();

        // 柔和图片.
        float softenFactor = 0.05f;
        float[] softenArray = {0, softenFactor, 0, softenFactor, 1 - (softenFactor * 4), softenFactor, 0, softenFactor, 0};
        Kernel kernel = new Kernel(3, 3, softenArray);
        ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
        bufferedImage = cOp.filter(bufferedImage, null);
        bufferedImage.flush();

        return bufferedImage;
    }

    /**
     * 背景图添加文字
     *
     * @param buffImg
     * @param fontStyle
     */
    public static BufferedImage writeBackgroundFontStyle(BufferedImage buffImg, List<FontStyle> fontStyle) {
        long start = System.currentTimeMillis();
        int buffImgWidth = buffImg.getWidth();
        int buffImgHeight = buffImg.getHeight();
        BufferedImage bufImg = new BufferedImage(buffImgWidth, buffImgHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufImg.createGraphics();
        g2d.drawImage(buffImg, 0, 0, buffImgWidth, buffImgHeight, null);
        //消除画图锯
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //消除文字锯齿
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);

        //画文字
        fontStyle.forEach((codeFont) -> {
            Font font = codeFont.getFont();
            // font.deriveFont(3.0f);
            //获取字体的宽和高
            FontMetrics metrics = g2d.getFontMetrics(font);
            // 文字宽度
            int fontWidth = metrics.stringWidth(codeFont.getText());
            // 文字高度
            int fontHeight = metrics.getHeight();
            //基线高度
            // int ascentHeight = metrics.getAscent();
            int ascentHeight = 0;
            //设置画笔的颜色
            g2d.setColor(codeFont.getColor());
            //设置字体及字号
            g2d.setFont(font);
            if (codeFont.center) {
                int width = bufImg.getWidth();
                codeFont.setStartX((width - fontWidth) / 2);
            }

            //设置水印的坐标
            int watermarkLength = fontWidth, x = codeFont.getStartX(), y = codeFont.getStartY();
            String s = "";
            if (watermarkLength > 480) {
                char[] chars = codeFont.getText().toCharArray();
                int tempy = y;
                int length = 0;
                for (char c : chars) {
                    s += String.valueOf(c);
                    length = getWatermarkLength(s, g2d);
                    if (length >= buffImgWidth - x) {
                        g2d.drawString(s, x, tempy);  //画出水印
                        tempy += font.getSize();
                        s = "";
                        x = 0;
                        length = 0;
                    }
                }
                if (length > 0) {
                    g2d.drawString(s, x, tempy);  //画出水印
                }
            } else {
                g2d.drawString(codeFont.getText(), x, y);  //画出水印
            }

        });
        //销毁图形界面资源
        g2d.dispose();
        log.info("画文字耗时 {}", System.currentTimeMillis()-start);
        return bufImg;
    }

    public static BufferedImage writeBackgroundFontStyle1(BufferedImage buffImg, List<FontStyle> fontStyle) {
        int buffImgWidth = buffImg.getWidth();
        int buffImgHeight = buffImg.getHeight();
        BufferedImage bufImg = new BufferedImage(buffImgWidth, buffImgHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufImg.createGraphics();
        g2d.drawImage(buffImg, 0, 0, buffImgWidth, buffImgHeight, null);
        //消除画图锯
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //消除文字锯齿
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);

        //画文字
        fontStyle.forEach((codeFont) -> {
            Font font = codeFont.getFont();
            // font.deriveFont(3.0f);
            //获取字体的宽和高
            FontMetrics metrics = g2d.getFontMetrics(font);
            // 文字宽度
            int fontWidth = metrics.stringWidth(codeFont.getText());
            // 文字高度
            int fontHeight = metrics.getHeight();
            //基线高度
            // int ascentHeight = metrics.getAscent();
            int ascentHeight = 0;
            //设置画笔的颜色
            g2d.setColor(codeFont.getColor());
            //设置字体及字号
            g2d.setFont(font);
            if (codeFont.center) {
                int width = bufImg.getWidth();
                codeFont.setStartX((width - fontWidth) / 2);
            }

            //设置水印的坐标
            int watermarkLength = fontWidth, x = codeFont.getStartX(), y = codeFont.getStartY();
            String s = "";
            if (watermarkLength > 480) {
                char[] chars = codeFont.getText().toCharArray();
                int tempy = y;
                int length = 0;
                for (char c : chars) {
                    s += String.valueOf(c);
                    length = getWatermarkLength(s, g2d);
                    if (length >= buffImgWidth - x-32) {
                        g2d.drawString(s, x, tempy);  //画出水印
                        tempy += font.getSize();
                        s = "";
                        // x = 0;
                        length = 0;
                    }
                }
                if (length > 0) {
                    g2d.drawString(s, x, tempy);  //画出水印
                }
            } else {
                g2d.drawString(codeFont.getText(), x, y);  //画出水印
            }

        });
        //销毁图形界面资源
        g2d.dispose();
        return bufImg;
    }

    public static int getWatermarkLength(String waterMarkContent, Graphics2D g) {
        int a = g.getFontMetrics(g.getFont()).charsWidth(waterMarkContent.toCharArray(), 0, waterMarkContent.length());
        return a;
    }

    /**
     * 透明背景追加文字，再画到指定图上
     *
     * @param codeFont
     */
    public static BufferedImage writeTransparentFont(FontStyle codeFont, int width) throws IOException {
        // 创建BufferedImage对象
        BufferedImage buffImg = new BufferedImage(width, codeFont.getFont().getSize() + 4, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = buffImg.createGraphics();

        //消除画图锯
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //消除文字锯齿
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);

        //画文字
        Font font = codeFont.getFont();
        //获取字体的宽和高
        FontMetrics metrics = g2d.getFontMetrics(font);
        // 文字宽度
        int fontWidth = metrics.stringWidth(codeFont.getText());
        // 文字高度
        int fontHeight = metrics.getHeight();
        //设置画笔的颜色
        g2d.setColor(codeFont.getColor());
        //设置字体及字号
        g2d.setFont(font);
        codeFont.setStartX((width - fontWidth) / 2);
        //写字时,y坐标为基线y坐标，不是绘画的起始y坐标，所以需要加上基线坐标ascentHeight，这样起始坐标就为我们设置的startY坐标了
        g2d.drawString(codeFont.getText(), codeFont.getStartX(), codeFont.getStartY() + fontHeight);

        //销毁图形界面资源
        g2d.dispose();

        return buffImg;
    }

    @Data
    public static class FontStyle {
        private int startX;//文字显示x坐标
        private int startY;//文字显示y坐标
        private String text;//文字内容
        private Color color; //文字显示颜色，这里先默认黑色
        private Font font;//字体样式
        private boolean center;//是否居中

        public FontStyle(int startX, int startY, String text, Color color, Font font, boolean center) {
            this.startX = startX;
            this.startY = startY;
            this.text = text;
            this.color = color;
            // this.font = new Font(null, font.getStyle(), font.getSize() + 5);
            this.font = font;
            this.center = center;
        }
    }

    @Data
    public static class ImgStyle {
        // x坐标
        private int startX;
        // y坐标
        private int startY;
        // 设置图片宽高
        private int width;
        private int height;
        // 设置图片圆角
        private int radius;
        // 原始图
        private InputStream sourceImg;
        // 待插入图
        private InputStream insertImg;
        // 上次转换的数据
        private BufferedImage buffImg;

        public ImgStyle(int startX, int startY, int width, int height, int radius, InputStream sourceImg,
                        InputStream insertImg, BufferedImage buffImg) {
            this.startX = startX;
            this.startY = startY;
            this.width = width;
            this.height = height;
            this.radius = radius;
            this.sourceImg = sourceImg;
            this.insertImg = insertImg;
            this.buffImg = buffImg;
        }
    }

}
