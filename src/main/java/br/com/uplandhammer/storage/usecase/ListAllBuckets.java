package br.com.uplandhammer.storage.usecase;

import br.com.uplandhammer.storage.http.domain.response.CreateBucketResponse;
import br.com.uplandhammer.storage.service.StorageContentFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ListAllBuckets {

    private final StorageContentFileService storageContentFileService;

    public List<CreateBucketResponse> execute() {
        return storageContentFileService.listAllBuckets();
    }
}
