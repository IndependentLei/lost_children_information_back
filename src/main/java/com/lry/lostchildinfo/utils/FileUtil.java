package com.lry.lostchildinfo.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author : jdl
 * @description : 文件工具类
 * @ClassName : FileUtil
 * @create : 2022-01-26 14:11
 */
public class FileUtil {

    /**
     * 校验文件类型是否符合要求
     * @param file
     * @param types
     * @return
     */
    public static boolean checkFileName(MultipartFile file, String ...types){
        String fileName = file.getOriginalFilename();
        if (StringUtils.isBlank(fileName)){
            return false;
        }
        // 获取文件类型
        String fileType = fileName.substring(fileName.indexOf(".")+1);
        for(String type : types){
            if (type.equals(fileType))
                return true;
        }
        return false;
    }
}
