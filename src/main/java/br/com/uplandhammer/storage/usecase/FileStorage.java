package br.com.uplandhammer.storage.usecase;

import br.com.uplandhammer.storage.exception.StorageFileException;
import br.com.uplandhammer.storage.service.StorageContentFileService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Component
@RequiredArgsConstructor
public class FileStorage {

    private final StorageContentFileService storageContentFileService;

    public void executar(MultipartFile file, String bucketName) {
        try {
            if(file == null || bucketName == null)
                throw new StorageFileException(String.format("File %s or bucketName %s.", file, bucketName));

            String name = file.getOriginalFilename();

            if(name == null)
                throw new StorageFileException(String.format("Name for file %s not found.", file));

            String extension = FilenameUtils.getExtension(name);
            File tempFile = File.createTempFile(name, extension);
            file.transferTo(tempFile);
            storageContentFileService.uploadContentFile(name, tempFile, bucketName);
        } catch (Exception e) {
            throw new StorageFileException("Fail to storage content");
        }


    }
}
