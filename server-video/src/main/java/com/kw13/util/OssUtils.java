package com.kw13.util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.DeleteObjectsResult;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Desc OSS工具类
 * @Author yejx
 * @Date 2019/3/8
 */
@Component
public class OssUtils {

//    @Value("${oss.endpoint}")
    private String endpoint;
//    @Value("${oss.bucket}")
    private String bucket;
//    @Value("${oss.accessKeyId}")
    private String accessKeyId;
//    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;
//    @Value("${oss.baseFolderName}")
    private String baseFolderName;
//    @Value("${oss.dns}")
    private String dns;

    private final static int DEFAULT_HOURS = 2;

    /**
     * 保存文件
     *
     * @param filename 文件名
     * @param file 二进制文件
     * @return 文件名
     */
    public void save(String filename, byte[] file) {
        if (file == null || file.length == 0) {
            return ;
        }
        OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        String fullName = baseFolderName + "/" + filename;
        client.putObject(bucket, fullName, new ByteArrayInputStream(file));
        client.shutdown();
    }

    /**
     * 删除oss文件
     *
     * @param filename 文件名
     */
    public void delete(String filename) {
        OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        client.deleteObject(bucket, filename);
        client.shutdown();
    }

    /**
     * 评论删除oss文件
     *
     * @param filenames 文件名List
     */
    public void batchDelete(List<String> filenames) {
        List fullList = filenames.stream().map(str -> baseFolderName + "/" + str).collect(Collectors.toList());
        OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        DeleteObjectsResult deleteObjectsResult = client.deleteObjects(new DeleteObjectsRequest(bucket).withKeys(fullList));
        List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();
        client.shutdown();
    }

    /**
     * 获取文件访问url(默认2小时过期)
     * @param filename 文件名
     * @return 文件访问url
     */
    public String url(String filename) {
        if (Objects.nonNull(filename)) {
            OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            //判断文件是否不存在 不存在返回空串 如果不处理 会返回一个访问不到的url
            String fullName = baseFolderName + "/" + filename;
            if (!client.doesObjectExist(bucket, fullName)) {
                return null;
            }
            String retUrl =  client.generatePresignedUrl(bucket, fullName, DateUtils.addHours(new Date(), DEFAULT_HOURS)).toString();
            return dns + baseFolderName + retUrl.split(baseFolderName)[1];
        }
        return null;
    }

    /**
     * 获取文件访问url(默认2小时过期)
     * @param filename 文件名
     * @param folderName 文件夹路径
     * @return 文件访问url
     */
    public String url(String filename, String folderName) {
        if (Objects.nonNull(filename)) {
            OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            String fullName = baseFolderName + "/" + folderName + "/" + filename;
            if (!client.doesObjectExist(bucket, fullName)) {
                return null;
            }
            return client.generatePresignedUrl(bucket, fullName,
                    DateUtils.addHours(new Date(), DEFAULT_HOURS)).toString();
        }
        return null;
    }
}
