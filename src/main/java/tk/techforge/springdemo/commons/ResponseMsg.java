package tk.techforge.springdemo.commons;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@Builder
public class ResponseMsg<T> implements Serializable {

    private final int code;
    private String message;
    private LocalDateTime timestamp;
    private T data;


    public static ResponseMsg ok() {
        return ResponseMsg.builder().code(HttpStatus.OK.value()).build();
    }

    public static ResponseMsg ok(String message) {
        return ResponseMsg.builder().code(HttpStatus.OK.value()).message(message).build();
    }

    public static <T> ResponseMsg ok(String message, T data) {
        return ResponseMsg.builder().code(HttpStatus.OK.value()).message(message).data(data).build();
    }

    public static <T> ResponseMsg data(T data) {
        return ResponseMsg.builder().code(HttpStatus.OK.value()).data(data).build();
    }

    public static ResponseMsg badRequest(String message) {
        return ResponseMsg.builder().code(HttpStatus.BAD_REQUEST.value()).message(message).build();
    }

    public static ResponseMsg internalServerError(String message) {
        return ResponseMsg.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.value()).message(message).build();
    }

}