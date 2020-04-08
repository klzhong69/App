package com.example.hz52.app.cofig;

import android.content.Context;
import android.util.Log;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.GetObjectRequest;
import com.alibaba.sdk.android.oss.model.GetObjectResult;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class OSSSet {


    private static OSSClient oss;
    private String testBucket;
    private String testObject;
    private String uploadFilePath;

    public static void OSSClient(Context context,String AccessKeyId,String SecretKeyId,String SecurityToken,String region,String bucket){

        String endpoint = "http://"+region+".aliyuncs.com";
        OSSCredentialProvider credentialProvider = new OSSStsTokenCredentialProvider(AccessKeyId, SecretKeyId, SecurityToken);

        //该配置类如果不设置，会有默认配置，具体可看该类
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(5); // 最大并发请求数，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
        OSSLog.enableLog(); //这个开启会支持写入手机sd卡中的一份日志文件位置在SDCard_path\OSSLog\logs.csv
        oss = new OSSClient(context.getApplicationContext(), endpoint, credentialProvider, conf);


    }



    public static String Upload(String testBucket,String testObject,String uploadFilePath){

        // 构造上传请求。
        PutObjectRequest put = new PutObjectRequest(testBucket, testObject, uploadFilePath);


        try {
            PutObjectResult putResult = oss.putObject(put);

            Log.d("PutObject", "UploadSuccess");
            Log.d("ETag", putResult.getETag());
            Log.d("RequestId", putResult.getRequestId());

            return "UploadSuccess";
        } catch (ClientException e) {
            // 本地异常，如网络异常等。
            e.printStackTrace();
        } catch (ServiceException e) {
            // 服务异常。
            Log.e("RequestId", e.getRequestId());
            Log.e("ErrorCode", e.getErrorCode());
            Log.e("HostId", e.getHostId());
            Log.e("RawMessage", e.getRawMessage());
        }

        return "UploadError";
    }


    public static String Download(String filePath,String testBucket,String testObject){

        final String[] succer = {"Error"};
        // 构造下载文件请求
        GetObjectRequest get = new GetObjectRequest(testBucket, testObject);

        oss.asyncGetObject(get, new OSSCompletedCallback<GetObjectRequest, GetObjectResult>() {
            @Override
            public void onSuccess(GetObjectRequest request, GetObjectResult result) {
                //开始读取数据。
                long length = result.getContentLength();
                byte[] buffer = new byte[(int) length];
                int readCount = 0;
                while (readCount < length) {
                    try{
                        readCount += result.getObjectContent().read(buffer, readCount, (int) length - readCount);
                    }catch (Exception e){
                        OSSLog.logInfo(e.toString());
                    }
                }
                //将下载后的文件存放在指定的本地路径。
                try {
                    FileOutputStream fout = new FileOutputStream(filePath);
                    fout.write(buffer);
                    fout.close();
                    succer[0] ="Success";
                } catch (Exception e) {
                    OSSLog.logInfo(e.toString());
                }

            }

            @Override
            public void onFailure(GetObjectRequest request, ClientException clientException,
                                  ServiceException serviceException)  {
                // 请求异常
                if (clientException != null) {
                    // 本地异常如网络异常等
                    clientException.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常
                    Log.e("ErrorCode", serviceException.getErrorCode());
                    Log.e("RequestId", serviceException.getRequestId());
                    Log.e("HostId", serviceException.getHostId());
                    Log.e("RawMessage", serviceException.getRawMessage());
                }
            }
        });


        return succer[0];

    }



    public static void Callback(String testBucket,String testObject,String uploadFilePath,String userId,String token){

        PutObjectRequest put = new PutObjectRequest(testBucket, testObject, uploadFilePath);

        put.setCallbackParam(new HashMap<String, String>() {
            {
                put("callbackUrl", "http://pan.weiyunhezi.com:8360/app/user/editAvatar?token="+token);
                put("callbackBodyType", "application/json");
                put("callbackBody", "{\"userId\":${x:userId},\"avatarUrl\":${x:avatarUrl}}");
            }
        });
        put.setCallbackVars(new HashMap<String, String>() {
            {
                put("x:userId", userId);
                put("x:avatarUrl", testObject);
            }
        });

        OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                Log.d("PutObject", "UploadSuccess");

                // 只有设置了servercallback，这个值才有数据
                String serverCallbackReturnJson = result.getServerCallbackReturnBody();

                Log.d("servercallback", serverCallbackReturnJson);

            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                // 异常处理
                // 请求异常
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常
                    Log.e("ErrorCode", serviceException.getErrorCode());
                    Log.e("RequestId", serviceException.getRequestId());
                    Log.e("HostId", serviceException.getHostId());
                    Log.e("RawMessage", serviceException.getRawMessage());
                }
            }
        });
    }



}
