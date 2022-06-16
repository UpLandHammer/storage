package br.com.uplandhammer.storage.usecase;

import br.com.uplandhammer.storage.exception.StorageFileException;
import br.com.uplandhammer.storage.http.domain.response.CreateBucketResponse;
import br.com.uplandhammer.storage.service.StorageContentFileService;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ListAllObjectsByBucketName {

    private final StorageContentFileService storageContentFileService;

    public List<S3ObjectSummary> executar(String bucketName) {

        List<CreateBucketResponse> createBucketResponses = storageContentFileService.listAllBuckets();
        List<String> listOfBucketName = createBucketResponses.stream().map(CreateBucketResponse::getName).toList();

        if(!listOfBucketName.contains(bucketName)) {
            throw new StorageFileException(String.format("Bucket not found %s", bucketName));
        }
        return storageContentFileService.getBucketInformation(bucketName);
    }
}
