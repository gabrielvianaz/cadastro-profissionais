package tech.gviana.cadastroprofissionais.core.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorSchema {

    private LocalDateTime timestamp;
    private Integer status;
    private String message;
    private String path;

}
