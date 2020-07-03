package tk.techforge.springdemo.modules.index.bean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;


@Setter
@Getter
public class ResponseMsg<T> extends ResponseEntity<T> implements Serializable {

    private String message = "";

    public ResponseMsg(HttpStatus status) {
        super(status);
    }

    public ResponseMsg(HttpStatus status, String message) {
        super(status);
        this.message = message;
    }

    public ResponseMsg(HttpStatus status, T data) {
        super(data, status);
    }

    public ResponseMsg(HttpStatus status, String message, T data) {
        super(data, status);
        this.message = message;
    }
}