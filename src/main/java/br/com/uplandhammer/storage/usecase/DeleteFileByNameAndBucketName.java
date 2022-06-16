package br.com.uplandhammer.storage.usecase;

import br.com.uplandhammer.storage.service.StorageContentFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteFileByNameAndBucketName {

    private final StorageContentFileService storageContentFileService;

    public void execute(String bucketName, String fileName) {
        storageContentFileService.removeFileByBucket(bucketName, fileName);
    }
}
