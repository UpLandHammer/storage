package br.com.uplandhammer.storage.usecase;

import br.com.uplandhammer.storage.exception.StorageFileException;
import br.com.uplandhammer.storage.http.domain.response.FileResponse;
import br.com.uplandhammer.storage.service.StorageContentFileService;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DownloadByName {

    private final StorageContentFileService storageContentFileService;
    private final ListFilesByBucket listFileByBucket;

    public FileResponse executar(String fileName, String bucketName) {
        try {

            S3ObjectSummary s3ObjectSummary = checkExistsFile(fileName, bucketName);

            byte[] fileContent = storageContentFileService.download(s3ObjectSummary.getKey(), bucketName);

            ByteArrayResource byteArrayResource = new ByteArrayResource(fileContent);
            String extension = FilenameUtils.getExtension(fileName);
            MediaType type;

            if (extension.equals("pdf")) {
                type = MediaType.APPLICATION_PDF;
            } else if (extension.equals("zip")) {
                type = MediaType.MULTIPART_FORM_DATA;
            } else if (extension.equals("png")) {
                type = MediaType.valueOf(MediaType.IMAGE_PNG_VALUE);
            } else if (extension.equals("gif")) {
                type = MediaType.valueOf(MediaType.IMAGE_GIF_VALUE);
            } else if (extension.equals("jpg")) {
                type = MediaType.valueOf(MediaType.IMAGE_JPEG_VALUE);
            }  else {
                type = MediaType.APPLICATION_OCTET_STREAM;
            }
            return FileResponse.builder().mimeType(type).resource(byteArrayResource).build();
        } catch (Exception e) {
            log.error("Fail to download content", e);
            throw new StorageFileException("Fail to download content", e);
        }
    }

    private S3ObjectSummary checkExistsFile(String fileName, String bucketName) {
        return listFileByBucket.execute(bucketName).stream()
                .filter(item -> fileName.equals(item.getKey()))
                .findFirst()
                .orElseThrow(() -> new StorageFileException(String.format("File %s not found", fileName)));
    }
}
