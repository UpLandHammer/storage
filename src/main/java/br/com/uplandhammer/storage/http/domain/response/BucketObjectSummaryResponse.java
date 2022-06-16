package br.com.uplandhammer.storage.http.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BucketObjectSummaryResponse {

    private String bucketName;
    private String key;
    private String eTag;
    private long size;
    private Date lastModified;
    private String storageClass;
    private String owner;


}
