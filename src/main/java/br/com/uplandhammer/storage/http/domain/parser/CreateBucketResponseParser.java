package br.com.uplandhammer.storage.http.domain.parser;

import br.com.uplandhammer.storage.http.domain.response.CreateBucketResponse;
import com.amazonaws.services.s3.model.Bucket;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class CreateBucketResponseParser {

    public static CreateBucketResponse from(Bucket bucket) {
        return CreateBucketResponse.builder()
                .creationDate(bucket.getCreationDate())
                .name(bucket.getName())
                .ownerResponse(bucket.getOwner() != null ? OwnerResponseParser.from(bucket.getOwner()) : null)
                .build();
    }

    public static List<CreateBucketResponse> from(List<Bucket> bucketList) {
        return bucketList.stream().map(CreateBucketResponseParser::from).toList();
    }
}
