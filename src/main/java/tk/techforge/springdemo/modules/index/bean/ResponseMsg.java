package tk.techforge.springdemo.modules.index.bean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;


@Setter
@Getter
public class ResponseMsg<T> extends ResponseEntity<T> implements Serializable {

    public ResponseMsg(HttpStatus status) {
        super(status);
    }
}