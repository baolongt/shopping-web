package nashtech.longtran.shoppingweb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ResponseDTO {
    private LocalDateTime time = LocalDateTime.now();
    private String errorCode;
    private Object data;
    private String successCode;

    public ResponseDTO(String errorCode, Object data, String successCode) {
        this.errorCode = errorCode;
        this.data = data;
        this.successCode = successCode;
    }
}
