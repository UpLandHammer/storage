package br.com.uplandhammer.storage.usecase;

import br.com.uplandhammer.storage.http.domain.response.CreateBucketResponse;
import br.com.uplandhammer.storage.service.StorageContentFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ListAllBuckets {

    private final StorageContentFileService storageContentFileService;

    public List<CreateBucketResponse> execute() {

        log.info("Listando todo conteúdo.");
        return storageContentFileService.listAllBuckets();
    }
}
