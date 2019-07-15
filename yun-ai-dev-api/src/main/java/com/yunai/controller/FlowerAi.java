package com.yunai.controller;

import com.yunai.pojo.Image;
import com.yunai.pojo.UserImage;
import com.yunai.service.ImageService;
import com.yunai.service.UserService;
import com.yunai.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URLEncoder;

@RestController
@Api(value="百度AI识别的接口",tags={"百度AI识别的controller"})
public class FlowerAi extends BasicController {
    @Autowired
    private ImageService imageService;
    @Autowired
    private UserService userService;
    /**
     * 重要提示代码中所需工具类
     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * 下载
     */
    @CrossOrigin
    @ApiOperation(value ="识别图片并上传到数据库",notes="识别图片并上传到数据库的接口")
    @PostMapping(value = "/plant",consumes = "multipart/*",headers = "content-type=multipart/form-data")

    public IMoocJSONResult plant( @RequestParam("file") @ApiParam(value = "上传的图片",required = true) MultipartFile[] files) throws  Exception {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/image-classify/v1/plant";

            // 本地文件路径
            String fileSpace = "C:/baiduai";
            String uploadPathDB = "/pictrue";
            String finalFacePath=null;
            FileOutputStream fileOutputStream = null;
            InputStream inputStream = null;
            try {
                if (files != null && files.length > 0) {

                    String fileName = files[0].getOriginalFilename();
                    if (StringUtils.isNotBlank(fileName)) {
                        //文件上传最终为绝对路径
                        finalFacePath = fileSpace + uploadPathDB + "/" + fileName;
                        //设置数据库保存的路径
                        uploadPathDB += ("/" + fileName);
                        File outFile = new File(finalFacePath);
                        if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
                            //创建父文件夹
                            outFile.getParentFile().mkdirs();
                        }

                        fileOutputStream = new FileOutputStream(outFile);
                        inputStream = files[0].getInputStream();
                        IOUtils.copy(inputStream, fileOutputStream);
                    }
                } else {
                    return IMoocJSONResult.errorMsg("上传出错");
                }
            } catch (Exception e) {
                e.printStackTrace();
                return IMoocJSONResult.errorMsg("上传出错");
            } finally {
                if (fileOutputStream != null) {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                }
            }

            String filePath =finalFacePath;
        try {

            byte[] imgData = FileUtil.readFileByBytes(filePath);

            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "image=" + imgParam;

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = "24.d07ffaf7709f6fad0962b26bcd239c15.2592000.1562557239.282335-16198880";

            String result = HttpUtil.post(url, accessToken, param);
//            System.out.println(result);
            Image image = new Image();
            image.setId(AcquireId.getUUID());
            image.setImage(uploadPathDB);
            image.setImageId(result);
            UserImage userImage=new UserImage();
            userImage.setuserId(image.getId());
            userImage.setuserLike(0);
            userImage.setDislike(0);

            userService.updateUserImageInfo(userImage);

            imageService.updateImageInfo(image);
            return IMoocJSONResult.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return IMoocJSONResult.ok();
    }

    @CrossOrigin
    @ApiOperation(value ="图片分页查询并显示",notes="图片分页查询并显示的接口")
    @ApiImplicitParam(name = "page",value = "页数",required = true,dataType = "Integer",paramType = "query")
    @GetMapping("/showImageInfo")
    public IMoocJSONResult showImageInfo(Integer page) throws  Exception{
        if(page == null ){
            page = 1;
        }
        PagedResult pagedResult = imageService.showImageInfo(page,PAGE_SIZE);
        return IMoocJSONResult.ok(pagedResult);
    }
}
