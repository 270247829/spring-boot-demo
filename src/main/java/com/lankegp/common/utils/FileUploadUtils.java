package com.lankegp.common.utils;

import java.io.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.lankegp.common.utils.date.DateUtil;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * Created by liugongrui on 2017/12/23.
 */
public class FileUploadUtils {

    /**
     * 得到文件路径
     *
     * @param fileNameWithPath
     * @return
     */
    public static String getFilePath(String fileNameWithPath) {
        String filePath = "";
        fileNameWithPath = fileNameWithPath.replace("/", File.separator);
        int idx = fileNameWithPath.lastIndexOf(File.separator);
        if (idx != -1) {
            filePath = fileNameWithPath.substring(0, idx);
        }
        return filePath;
    }


    /**
     * 将内容写入文件
     *
     * @param context  内容
     * @param fileName 目标文件
     * @throws Exception
     */
    public static void writeFile(String context, String fileName) throws Exception {
        byte[] bytes = context.getBytes();
        File file = new File(fileName);
        FileOutputStream fop = new FileOutputStream(file);
        BufferedOutputStream out = new BufferedOutputStream(fop);
        if (!file.exists()) {
            file.mkdirs();
        }
        out.write(bytes);
        out.flush();
        out.close();
    }

    /**
     * 复制文件
     *
     * @param srcFileName  要复制的源文件
     * @param destFileName 目标文件
     * @throws Exception
     */
    public static void copyFile(String srcFileName, String destFileName)
            throws Exception {
        BufferedInputStream in = null;
        BufferedOutputStream out = null;
        try {
            in = new BufferedInputStream(new FileInputStream(new File(
                    srcFileName)));
            String destPath = getFilePath(destFileName);
            File pathFile = new File(destPath);
            if (!pathFile.exists()) {
                pathFile.mkdirs();
            }
            out = new BufferedOutputStream(new FileOutputStream(new File(
                    destFileName)));
            int len;
            byte[] b = new byte[1024];
            while ((len = in.read(b)) != -1) {
                out.write(b, 0, len);
            }
            out.flush();
        } catch (Exception e) {
            System.out.println("复制文件失败：" + e.getMessage());
            throw e;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception ex) {
            }
            try {
                if (out != null) {
                    out.close();
                }
            } catch (Exception ex) {
            }
        }
    }

    /**
     * 获取文件后缀
     *
     * @param originalFileName
     * @return
     */
    public static String getFileSuffix(String originalFileName) {
        int dot = originalFileName.lastIndexOf('.');
        if ((dot > -1) && (dot < originalFileName.length())) {
            return originalFileName.substring(dot);
        }
        return originalFileName;
    }

    /**
     * @param request
     * @return 文件修改后上传保存，如果源文件不是空，则覆盖源文件，如果源文件为空，则创建新文件
     * @author lgr
     */
    @SuppressWarnings("null")
    public static String upload(HttpServletRequest request, String storePath) {
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        Iterator iter = multiRequest.getFileNames();
        while (iter.hasNext()) {
            MultipartFile file = multiRequest.getFile((String) iter.next());
            if (file != null) {
                String originalFileName = file.getOriginalFilename();

                String path = storePath + DateUtil.DateToString(new Date(),"yyyyMMdd");
                //得到存储到本地的文件名
                String localFileName = UUID.randomUUID().toString().replaceAll("-","") +
                        getFileSuffix(originalFileName);
                //新建本地文件
                File localFile = new File(path, localFileName);
                //创建目录
                File fileDir = new File(path);
                if (!fileDir.isDirectory()) {
                    // 如果目录不存在，则创建目录
                    fileDir.mkdirs();
                }


                String src = path + "/" + localFileName;
                //写文件到本地
                try {
                    file.transferTo(localFile);
                    return src;
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


}
