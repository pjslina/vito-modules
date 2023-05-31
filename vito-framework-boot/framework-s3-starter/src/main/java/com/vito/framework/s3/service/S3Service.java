package com.vito.framework.s3.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.vito.framework.s3.model.ObjectInfo;
import com.vito.framework.s3.properties.S3Properties;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author panjin
 */
@ConditionalOnClass(AmazonS3.class)
@Slf4j
public class S3Service {

    private static final String EMPTY = "";
    private static final String DEF_CONTEXT_TYPE = "application/octet-stream";
    private static final String SPLIT = "/";
    private final AmazonS3 s3client;
    @Autowired
    private S3Properties s3Properties;

    public S3Service(S3InitService s3InitService) {
        this.s3client = s3InitService.getS3client();
    }

    /**
     * 判断桶中对象是否存在
     *
     * @param objectName 桶中对象名
     * @return true 存在 false 不存在
     */
    public boolean doesObjectExist(String objectName) {
        return s3client.doesObjectExist(s3Properties.getBucketName(), objectName);
    }

    /**
     * 删除桶中所有对象
     */
    public void deleteObjects() {
        ObjectListing objectListing = s3client.listObjects(s3Properties.getBucketName());
        DeleteObjectsRequest deleteObjectsRequest = new DeleteObjectsRequest(s3Properties.getBucketName());
        List<String> keys = new ArrayList<>();
        while (true) {
            objectListing.getObjectSummaries().forEach(f -> keys.add(f.getKey()));
            if (objectListing.isTruncated()) {
                objectListing = s3client.listNextBatchOfObjects(objectListing);
            } else {
                break;
            }
        }
        if (!keys.isEmpty()) {
            log.info("deleteObjects:{}", keys);
            deleteObjectsRequest.withKeys(keys.toArray(new String[0]));
            s3client.deleteObjects(deleteObjectsRequest);
        }
    }

    /**
     * copying an object
     *
     * @param sourceBucketName      源bucket名称
     * @param sourceKey             源key
     * @param destinationBucketName 目标bucket名称
     * @param destinationKey        目标key
     * @return CopyObjectResult
     */
    public CopyObjectResult copyObject(
            String sourceBucketName,
            String sourceKey,
            String destinationBucketName,
            String destinationKey
    ) {
        return s3client.copyObject(
                sourceBucketName,
                sourceKey,
                destinationBucketName,
                destinationKey
        );
    }

    @SneakyThrows
    public ObjectInfo upload(String fileName, InputStream is) {
        return upload(s3Properties.getBucketName(), fileName, is, is.available(), DEF_CONTEXT_TYPE);
    }

    @SneakyThrows
    public ObjectInfo upload(MultipartFile file) {
        return upload(s3Properties.getBucketName(), file.getOriginalFilename(), file.getInputStream()
                , ((Long) file.getSize()).intValue(), file.getContentType());
    }

    @SneakyThrows
    public ObjectInfo upload(String path, MultipartFile file) {
        createDir(path);
        String objectName = getDirPathWithValid(path) + file.getOriginalFilename();
        return upload(s3Properties.getBucketName(), objectName, file.getInputStream()
                , ((Long) file.getSize()).intValue(), file.getContentType());
    }

    @SneakyThrows
    public ObjectInfo upload(String bucketName, String fileName, InputStream is) {
        return upload(bucketName, fileName, is, is.available(), DEF_CONTEXT_TYPE);
    }

    /**
     * 上传对象
     *
     * @param bucketName  bucket名称
     * @param objectName  对象名
     * @param is          对象流
     * @param size        大小
     * @param contentType 类型
     */
    private ObjectInfo upload(String bucketName, String objectName, InputStream is, int size, String contentType) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(size);
        objectMetadata.setContentType(contentType);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, is, objectMetadata);
        putObjectRequest.getRequestClientOptions().setReadLimit(size + 1);
        s3client.putObject(putObjectRequest);

        ObjectInfo obj = new ObjectInfo();
        obj.setObjectPath(bucketName + SPLIT + objectName);
        obj.setObjectUrl(s3Properties.getEndpoint() + SPLIT + obj.getObjectPath());
        return obj;
    }

    public ObjectInfo upload(String path, String name, String content) {
        createDir(path);
        String objectName = getDirPathWithValid(path) + name;
        s3client.putObject(s3Properties.getBucketName(), objectName, content);
        ObjectInfo obj = new ObjectInfo();
        obj.setObjectPath(s3Properties.getBucketName() + SPLIT + objectName);
        obj.setObjectUrl(s3Properties.getEndpoint() + SPLIT + obj.getObjectPath());
        return obj;
    }

    public void delete(String objectName) {
        delete(s3Properties.getBucketName(), objectName);
    }

    public void delete(String bucketName, String objectName) {
        s3client.deleteObject(bucketName, objectName);
    }

    /**
     * 获取预览地址
     *
     * @param bucketName bucket名称
     * @param objectName 对象名
     * @param expires    有效时间(分钟)，最大7天有效
     * @return 预览地址
     */
    public String getViewUrl(String bucketName, String objectName, int expires) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, expires);
        URL url = s3client.generatePresignedUrl(bucketName, objectName, cal.getTime());
        return url.toString();
    }

    public String out(String objectName) {
        ByteArrayOutputStream bass = new ByteArrayOutputStream();
        out(objectName, bass);
        return bass.toString();
    }

    public void out(String objectName, OutputStream os) {
        out(s3Properties.getBucketName(), objectName, os);
    }

    /**
     * 输出对象
     *
     * @param bucketName bucket名称
     * @param objectName 对象名
     * @param os         输出流
     */
    @SneakyThrows
    public void out(String bucketName, String objectName, OutputStream os) {
        S3Object s3Object = s3client.getObject(bucketName, objectName);
        try (
                S3ObjectInputStream s3is = s3Object.getObjectContent();
        ) {
            IOUtils.copy(s3is, os);
        }
    }

    public List<String> getAllKeys() {
        return getAllKeys(s3Properties.getBucketName());
    }

    public List<String> getAllKeys(String bucketName) {
        final ListObjectsV2Request req = new ListObjectsV2Request().withBucketName(bucketName);
        List<String> keys = new ArrayList<>();
        ListObjectsV2Result result;
        do {
            result = s3client.listObjectsV2(req);
            for (S3ObjectSummary objectSummary : result.getObjectSummaries()) {
                keys.add(objectSummary.getKey());
            }
            req.setContinuationToken(result.getNextContinuationToken());
        } while (result.isTruncated());

        return keys;
    }

    public void createDir(String path) {
        path = getDirPathWithValid(path);
        if (existDir(path)) {
            return;
        }
        loopCreateDir(path);
    }

    public void deleteDir(String path) {
        if (!existDir(path)) {
            return;
        }
        List<S3ObjectSummary> objectSummaries = getS3ObjectSummaries(path, s3Properties.getBucketName());
        if (objectSummaries.isEmpty()) {
            log.warn("删除{}路径下目录及子目录，但该目录不存在", path);
            return;
        }
        if (2 <= objectSummaries.size()) {
            log.warn("{}路径下存在文件或者子目录", path);
            return;
        }
        log.info("删除目录{}", path);
        s3client.deleteObject(s3Properties.getBucketName(), objectSummaries.get(0).getKey());
    }

    public void deleteDirAndFiles(String path) {
        if (!existDir(path)) {
            return;
        }
        List<S3ObjectSummary> objectSummaries = getS3ObjectSummaries(path, s3Properties.getBucketName());
        if (objectSummaries.isEmpty()) {
            log.warn("删除{}路径下目录及子目录，但该目录不存在", path);
            return;
        }
        List<String> keys = new ArrayList<>();
        objectSummaries.forEach(f -> keys.add(f.getKey()));
        log.info("删除目录{}及子目录", path);
        DeleteObjectsRequest objectsRequest = new DeleteObjectsRequest(s3Properties.getBucketName())
                .withKeys(keys.toArray(new String[0]));
        s3client.deleteObjects(objectsRequest);
    }

    public boolean existDir(String path) {
        path = getDirPathWithValid(path);
        return s3client.doesObjectExist(s3Properties.getBucketName(), path);
    }

    private List<S3ObjectSummary> getS3ObjectSummaries(String path, String bucketName) {
        path = getDirPathWithValid(path);
        ObjectListing dirObjs = s3client.listObjects(bucketName, path);
        return dirObjs.getObjectSummaries();
    }

    private void loopCreateDir(String dirPath) {
        String[] itemDir = dirPath.split(SPLIT);
        for (int i = 0; i < itemDir.length; i++) {
            StringBuilder item = new StringBuilder(EMPTY);
            for (int j = 0; j < i; j++) {
                item.append(itemDir[j]).append(SPLIT);
            }
            item.append(itemDir[i]).append(SPLIT);
            if (!doesObjectExist(item.toString())) {
                log.info("创建目录:{}", item);
                s3client.putObject(s3Properties.getBucketName(), item.toString(), EMPTY);
            }
        }
    }

    /**
     * 如果path是以“/”开头的，则删除“/”
     *
     * @param path 路径
     * @return 如果path是以“/”开头的，则删除“/”
     */
    private String getDirPathWithValid(String path) {
        path = getFilePathWithValid(path);
        if (!path.endsWith(SPLIT)) {
            path += SPLIT;
        }
        return path;
    }

    /**
     * 如果path是以“/”开头的，则删除“/”
     *
     * @param path 路径
     * @return 如果path是以“/”开头的，则删除“/”
     */
    private String getFilePathWithValid(String path) {
        path = path.replaceAll("^" + SPLIT, EMPTY);
        return path;
    }

    /**
     * is bucket exist
     *
     * @param bucketName bucket name
     * @return true if exist
     */
    public boolean doesBucketExist(String bucketName) {
        return s3client.doesBucketExistV2(bucketName);
    }

    public boolean doesBucketExist() {
        return doesBucketExist(s3Properties.getBucketName());
    }

    /**
     * create a bucket
     *
     * @param bucketName bucket name
     * @return bucket
     */
    public Bucket createBucket(String bucketName) {
        return s3client.createBucket(bucketName);
    }

    public Bucket createBucket() {
        return createBucket(s3Properties.getBucketName());
    }

    /**
     * list all buckets
     *
     * @return  buckets
     */
    public List<Bucket> listBuckets() {
        return s3client.listBuckets();
    }

    /**
     * delete a bucket
     * 如果桶中还有对象，抛出AmazonS3Exception异常
     *
     * @param bucketName bucket name
     */
    public void deleteBucket(String bucketName) {
        s3client.deleteBucket(bucketName);
    }

    public void deleteBucket() {
        deleteBucket(s3Properties.getBucketName());
    }
}
