package com.yyq.meinian;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.yyq.meinian.utils.QiniuUtils;

/**
 * @title: QiniuTest
 * @Author yyq
 * @Date: 2021/10/31 8:23
 * @Version 1.0
 */
public class QiniuTest {

    public void testUpload(){
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());//华南
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = "30mRa-4QnbWgfkTzXNX-vKEsY2SigK189oQExldk";
        String secretKey = "M5gveg-0ixhDeN5VYLt1wtVpmPsK5WS7v962L1NT";
        String bucket = "meinian-yyq";
        //如果是Windows情况下，格式是 D:\\qiniu\\test.png，可支持中文
        String localFilePath = "G:\\Picture\\maid.jpg";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);// FspfyEyKfuHZ0kcbXRIc5T9YhCax
            System.out.println(putRet.hash);// FspfyEyKfuHZ0kcbXRIc5T9YhCax
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }

    }


    public void deleteFile(){
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        //...其他参数参考类注释
        String accessKey = "30mRa-4QnbWgfkTzXNX-vKEsY2SigK189oQExldk";
        String secretKey = "M5gveg-0ixhDeN5VYLt1wtVpmPsK5WS7v962L1NT";
        String bucket = "meinian-yyq";//空间
        String key = "FjuE5Z-lRJ_8EoAsg2BSdCUVQNZL";//文件名称
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, key);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
    }

    public void testQiniuUtils(){
        //QiniuUtils.upload2Qiniu("G:\\Picture\\maid.jpg","maid.jpg");
        QiniuUtils.deleteFileFromQiniu("maid.jpg");
    }

}
