package br.com.uplandhammer.storage.http.domain.parser;

import br.com.uplandhammer.storage.http.domain.response.PatternResponse;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;

@UtilityClass
public class PatternResponseParser {

    public static PatternResponse create() {
        return PatternResponse
                .builder()
                .status(HttpStatus.OK)
                .message("Executed with success!")
                .build();
    }
}
