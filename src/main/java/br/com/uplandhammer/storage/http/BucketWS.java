package br.com.uplandhammer.storage.http;

import br.com.uplandhammer.storage.http.domain.parser.PatternResponseParser;
import br.com.uplandhammer.storage.http.domain.request.CreateMyOwnBucketRequest;
import br.com.uplandhammer.storage.http.domain.response.CreateBucketResponse;
import br.com.uplandhammer.storage.http.domain.response.PatternResponse;
import br.com.uplandhammer.storage.usecase.CreateBucketByName;
import br.com.uplandhammer.storage.usecase.ListAllBuckets;
import br.com.uplandhammer.storage.usecase.RemoveBucket;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.uplandhammer.storage.http.common.URLMapping.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(API)
@Api(value = "BucketWS", tags = "Bucket API")
public class BucketWS {

    private final CreateBucketByName createBucketByName;
    private final ListAllBuckets listAllBuckets;
    private final RemoveBucket removeBucket;

    @ApiOperation("Create a bucket.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = CreateBucketResponse.class),
            @ApiResponse(code = 500, message = "Fail", response = PatternResponse.class)
    })
    @PostMapping(value = BUCKET_CREATE)
    public ResponseEntity<CreateBucketResponse> create(@RequestBody CreateMyOwnBucketRequest createMyOwnBucketRequest) {
        return ResponseEntity.ok().body(createBucketByName.execute(createMyOwnBucketRequest));
    }

    @ApiOperation("List all buckets.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = List.class),
            @ApiResponse(code = 500, message = "Fail", response = PatternResponse.class)
    })
    @GetMapping(value = BUCKET_LIST_ALL)
    public ResponseEntity<List<CreateBucketResponse>> getAll() {
        return ResponseEntity.ok().body(listAllBuckets.execute());
    }

    @ApiOperation("Remove bucket.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = PatternResponse.class),
            @ApiResponse(code = 500, message = "Fail", response = PatternResponse.class)
    })
    @DeleteMapping(value = BUCKET_REMOVE)
    public ResponseEntity<PatternResponse> remove(@PathVariable("bucketName") String bucketName) {
        removeBucket.execute(bucketName);
        return ResponseEntity.ok().body(PatternResponseParser.create());
    }
}
