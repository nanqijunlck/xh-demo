package com.fqyc.demo.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author lck
 * @Date 2023/3/18 16:58
 * @Version 1.0
 * @Desc
 */
@Slf4j
public class PdfUtil {
    private static final String FILEPATH = "C://pdf//";

    /**
     * @param fileName   生成pdf文件
     * @param imagesPath 需要转换的图片路径的数组
     * @param response
     */
    public static void imagesToPdfByOs(String fileName, String imagesPath, HttpServletResponse response) {
        File file = new File(fileName + ".pdf");
        file.setExecutable(true);
        try {
            // 第一步：创建一个document对象。
            Document document = new Document();
            document.setMargins(0, 0, 0, 0);
            // 第二步：
            // 创建一个PdfWriter实例，
            PdfWriter.getInstance(document, new FileOutputStream(file));
            // 第三步：打开文档。
            document.open();
            // 第四步：在文档中增加图片。
            File files = new File(imagesPath);
            String[] images = files.list();
            Arrays.sort(images);
            int len = images.length;
            Map<String, String> map = new HashMap<>();
            for (int i = 0; i < len; i++) {
                String[] split = images[i].split("-");
                String s = split[split.length - 1];
                int num = Integer.valueOf(s.substring(0, s.length() - 4));
                map.put(split[0] + "-" + num, images[i]);
            }
            int tempCount = 0;
            for (int i = 0; i < len; i++) {
                if (images[i].toLowerCase().endsWith(".bmp")
                        || images[i].toLowerCase().endsWith(".jpg")
                        || images[i].toLowerCase().endsWith(".jpeg")
                        || images[i].toLowerCase().endsWith(".gif")
                        || images[i].toLowerCase().endsWith(".png")) {
                    String[] split = images[i].split("-");
                    String tempImage = map.get(split[0] + "-" + (i + 1 - tempCount));
                    if (StringUtils.isEmpty(tempImage)) {
                        tempCount = i;
                        tempImage = map.get(split[0] + "-" + (i + 1 - tempCount));
                    }
                    String temp = imagesPath + "//" + tempImage;
                    Image img = Image.getInstance(temp);
                    img.setAlignment(Image.ALIGN_CENTER);
                    img.scalePercent(100);
                    // 根据图片大小设置页面，一定要先设置页面，再newPage（），否则无效
                    document.setPageSize(new Rectangle(img.getWidth(), img.getHeight()));
                    document.newPage();
                    document.add(img);
                }
            }
            //输出 下载的响应头，如果下载的文件是中文名，文件名需要经过url编码
            response.setContentType("text/html;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file.getName(), "UTF-8"));
            response.setHeader("Cache-Control", "no-cache");
            response.addHeader("Content-Length", "" + file.length());
            ServletOutputStream outputStream = response.getOutputStream();
            FileInputStream inputStream = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            while ((len = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, len);
            }
            outputStream.flush();
            inputStream.close();
            outputStream.close();
            // 第五步：关闭文档。
            document.close();
        } catch (Exception e) {
            log.error("error creating", e);
        } finally {
            file.delete();
        }
    }

    public static void imagesToPdfByFile(String fileName, String imagesPath) throws Exception {
        File pdfFile = null;
        File file = null;
            pdfFile = new File(FILEPATH);
            if (!pdfFile.exists() || !pdfFile.isDirectory()) {
                pdfFile.mkdirs();
            }
            //当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
            fileName = FILEPATH + fileName + ".pdf";
            file = new File(fileName);
            // 第一步：创建一个document对象。
            Document document = new Document();
            document.setMargins(0, 0, 0, 0);
            // 第二步：
            // 创建一个PdfWriter实例，
            PdfWriter.getInstance(document, new FileOutputStream(file));
            // 第三步：打开文档。
            document.open();
            // 第四步：在文档中增加图片。

            // 第五步：关闭文档。
            document.close();

    }
}


