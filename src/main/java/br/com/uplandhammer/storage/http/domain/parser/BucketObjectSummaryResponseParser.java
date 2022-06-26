package br.com.uplandhammer.storage.http.domain.parser;

import br.com.uplandhammer.storage.http.domain.response.BucketObjectSummaryResponse;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class BucketObjectSummaryResponseParser {

    public static BucketObjectSummaryResponse create(S3ObjectSummary s3ObjectSummary) {
        return BucketObjectSummaryResponse
                .builder()
                .bucketName(s3ObjectSummary.getBucketName())
                .owner(s3ObjectSummary.getOwner().toString())
                .eTag(s3ObjectSummary.getETag())
                .key(s3ObjectSummary.getKey())
                .lastModified(s3ObjectSummary.getLastModified())
                .size(s3ObjectSummary.getSize())
                .storageClass(s3ObjectSummary.getStorageClass())
                .size(s3ObjectSummary.getSize())
                .build();
    }

    public static List<BucketObjectSummaryResponse> create(List<S3ObjectSummary> objectSummaries){
        return objectSummaries.stream().map(BucketObjectSummaryResponseParser::create).collect(Collectors.toList());
    }
}
