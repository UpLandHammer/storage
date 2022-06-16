package br.com.uplandhammer.storage.http.domain.parser;

import br.com.uplandhammer.storage.http.domain.response.OwnerResponse;
import com.amazonaws.services.s3.model.Owner;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OwnerResponseParser {

    public static OwnerResponse from(Owner owner) {
        return OwnerResponse.builder()
                .displayName(owner.getDisplayName())
                .id(owner.getId())
                .build();
    }
}
