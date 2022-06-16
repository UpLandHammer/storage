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
public class CreateBucketResponse {
    private String name;
    private OwnerResponse ownerResponse;
    private Date creationDate;
}
