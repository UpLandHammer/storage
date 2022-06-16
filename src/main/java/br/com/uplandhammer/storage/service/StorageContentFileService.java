package br.com.uplandhammer.storage.service;

import br.com.uplandhammer.storage.exception.StorageFileException;
import br.com.uplandhammer.storage.http.domain.parser.CreateBucketResponseParser;
import br.com.uplandhammer.storage.http.domain.response.CreateBucketResponse;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class StorageContentFileService {

    private final AmazonS3 amazonS3;

    public void uploadContentFile(String fileName, File file, String bucketName) {
        try {
            PutObjectResult bkcreatedwithtf = amazonS3.putObject(bucketName, fileName, file);
            log.info("retorno: {} ", bkcreatedwithtf);
        } catch (Exception e) {
            log.error("Fail to upload file", e);
            throw new StorageFileException("Fail to upload file", e);
        }
    }

    public List<S3ObjectSummary> getBucketInformation(String bucketName) {
        try {
            ObjectListing s3Object = amazonS3.listObjects(bucketName);
            log.info("bucket: {}", s3Object.getBucketName());
            return s3Object.getObjectSummaries();
        } catch (Exception e) {
            log.error("Fail to get information", e);
            throw new StorageFileException("Fail to get information", e);
        }
    }

    public byte[] download(String fileName, String bucketName) {
        final S3Object object = amazonS3.getObject(bucketName, fileName);
        try (object; S3ObjectInputStream objectContent = object.getObjectContent()) {
            return IOUtils.toByteArray(objectContent);
        } catch (IOException e) {
            log.error("Fail to download file", e);
            throw new StorageFileException("Fail to download file", e);
        }
    }

    public List<CreateBucketResponse> listAllBuckets() {
        List<Bucket> buckets = amazonS3.listBuckets();
        return CreateBucketResponseParser.from(buckets);
    }

    public CreateBucketResponse createBucket(String bucketName) {
        try {
            CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
            Bucket bucket = amazonS3.createBucket(createBucketRequest);
            return CreateBucketResponseParser.from(bucket);

        } catch (Exception e) {
            log.error("Fail to create bucket", e);
            throw new StorageFileException("Fail to create bucket", e);
        }
    }

    public void removeFileByBucket(String bucketName, String fileName) {
        try {
            amazonS3.deleteObject(bucketName, fileName);
        } catch (Exception e) {
            log.error("Fail to remove file {} and bucketName {}", fileName, bucketName);
            throw new StorageFileException(String.format("Fail to remove file %s and bucketName %s", fileName, bucketName), e);
        }
    }

    public void removeBucket(String bucketName) {
        try {
            amazonS3.deleteBucket(bucketName);
        } catch (Exception e) {
            log.error("Fail to remove bucketName {}", bucketName);
            throw new StorageFileException(String.format("Fail to remove bucketName %s", bucketName), e);
        }
    }
}
