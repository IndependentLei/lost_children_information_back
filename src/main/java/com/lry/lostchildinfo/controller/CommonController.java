package com.lry.lostchildinfo.controller;

import com.lry.lostchildinfo.common.Result;
import com.lry.lostchildinfo.entity.FtpProperties;
import com.lry.lostchildinfo.utils.FtpUtil;
import com.lry.lostchildinfo.utils.IdUtil;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author : jdl
 * @description : 通用视图层
 * @ClassName : CommonController
 * @create : 2022-01-25 0:12
 */
@RestController
@RequestMapping("/common")
public class CommonController {
    @Autowired
    FtpProperties ftpProperties;

    @PostMapping("/uploadPic")
    public Result uploadPic(MultipartFile file){
        try{
            String oldName = file.getOriginalFilename();
            String newName = IdUtil.genImageName();
            newName = newName +oldName.substring(oldName.lastIndexOf("."));
            String filePath = new DateTime().toString("/yyyy/MM/dd");
            // 存储路径
            String url = ftpProperties.getImageBaseUrl()+filePath+"/"+newName;
            InputStream is = file.getInputStream();
            boolean result = FtpUtil.uploadFile(ftpProperties.getAddress()
                    ,ftpProperties.getPort()
                    ,ftpProperties.getUsername()
                    ,ftpProperties.getPassword()
                    ,ftpProperties.getBasePath()
                    ,filePath
                    ,newName
                    ,is);
            if ( result ){
                return Result.success("上传成功",url);
            }else{
                return Result.error(false);
            }
        }catch (IOException e){
            return Result.error();
        }
    }

}