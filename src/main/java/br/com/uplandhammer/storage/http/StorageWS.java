package br.com.uplandhammer.storage.http;

import br.com.uplandhammer.storage.http.domain.parser.BucketObjectSummaryResponseParser;
import br.com.uplandhammer.storage.http.domain.parser.PatternResponseParser;
import br.com.uplandhammer.storage.http.domain.response.BucketObjectSummaryResponse;
import br.com.uplandhammer.storage.http.domain.response.FileResponse;
import br.com.uplandhammer.storage.http.domain.response.PatternResponse;
import br.com.uplandhammer.storage.usecase.DeleteFileByNameAndBucketName;
import br.com.uplandhammer.storage.usecase.DownloadByName;
import br.com.uplandhammer.storage.usecase.FileStorage;
import br.com.uplandhammer.storage.usecase.ListAllObjectsByBucketName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static br.com.uplandhammer.storage.http.common.URLMapping.*;

@RestController
@RequestMapping(API)
@RequiredArgsConstructor
@Api(value = "StorageWS", tags = "Storage Files API")
public class StorageWS {

    private final FileStorage armazenarSenhaStorage;
    private final ListAllObjectsByBucketName listarObjetosBucket;
    private final DownloadByName fazerDownloadPorNome;
    private final DeleteFileByNameAndBucketName deleteFileByNameAndBucketName;

    @ApiOperation("Upload document.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = PatternResponse.class),
            @ApiResponse(code = 500, message = "Fail", response = PatternResponse.class)
    })
    @PostMapping(value = FILE_UPLOAD)
    public ResponseEntity<PatternResponse> storage(@RequestParam("file") MultipartFile file,
                                                   @PathVariable("bucketName") String bucketName) {
        armazenarSenhaStorage.executar(file, bucketName);
        return ResponseEntity.ok().body(PatternResponseParser.create());
    }

    @ApiOperation("Retrieve information from a bucket.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = List.class),
            @ApiResponse(code = 500, message = "Fail", response = PatternResponse.class)
    })
    @GetMapping(value = FILE_GET_INFO)
    public ResponseEntity<List<BucketObjectSummaryResponse>> getInformation(@PathVariable("bucketName") String bucketName) {
        return ResponseEntity.ok().body(BucketObjectSummaryResponseParser.create(listarObjetosBucket.executar(bucketName)));
    }

    @ApiOperation("Download document.")
    @ApiResponses(value = {

            @ApiResponse(code = 200, message = "Success", response = Resource.class),
            @ApiResponse(code = 500, message = "Fail", response = PatternResponse.class)
    })
    @GetMapping(value = FILE_GET_BY_BUCKET)
    public ResponseEntity<Resource> getFile(@PathVariable("fileName") String fileName,
                                            @PathVariable("bucketName") String bucketName ) throws IOException {
        FileResponse response = fazerDownloadPorNome.executar(fileName, bucketName);
        return ResponseEntity.ok()
                .contentLength(response.getResource().contentLength())
                .contentType(response.getMimeType())
                .body(response.getResource());
    }

    @ApiOperation("Remove document by bucket.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = PatternResponse.class),
            @ApiResponse(code = 500, message = "Fail", response = PatternResponse.class)
    })
    @DeleteMapping(value = FILE_REMOVE_FILE_BY_BUCKET)
    public ResponseEntity<PatternResponse> deleteFile(@PathVariable("bucketName") String bucketName,
                                                      @PathVariable("fileName") String fileName) {
        deleteFileByNameAndBucketName.execute(bucketName, fileName);
        return ResponseEntity.ok().body(PatternResponseParser.create());
    }

}