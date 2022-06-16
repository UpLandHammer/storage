package br.com.uplandhammer.storage.usecase;

import br.com.uplandhammer.storage.http.domain.request.CreateMyOwnBucketRequest;
import br.com.uplandhammer.storage.http.domain.response.CreateBucketResponse;
import br.com.uplandhammer.storage.service.StorageContentFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateBucketByName {

    private final StorageContentFileService storageContentFileService;

    public CreateBucketResponse execute(CreateMyOwnBucketRequest createMyOwnBucketRequest) {
        return storageContentFileService.createBucket(createMyOwnBucketRequest.getBucketName());
    }
}
