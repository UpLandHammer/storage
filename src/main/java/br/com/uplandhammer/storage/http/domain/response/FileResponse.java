package br.com.uplandhammer.storage.http.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileResponse {

    private MediaType mimeType;
    private Resource resource;

}
