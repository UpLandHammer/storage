package br.com.uplandhammer.storage.usecase;

import com.amazonaws.services.s3.model.S3ObjectSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ListFilesByBucket {

    private final ListAllObjectsByBucketName listAllObjectsByBucketName;

    public List<S3ObjectSummary> execute(String bucketName) {
        List<S3ObjectSummary> s3ObjectSummaries = listAllObjectsByBucketName.executar(bucketName);
        return s3ObjectSummaries.stream()
                .filter(item -> bucketName.equals(item.getBucketName()))
                .toList();
    }
}
