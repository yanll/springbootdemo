package tk.techforge.springdemo.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMsg<T> implements Serializable {

    Integer code;
    private String desc;
    private T data;
    private Date timestamp = new Date();

    public static <T> ResponseMsg INTERNAL_SERVER_ERROR(String desc) {
        ResponseMsg<T> msg = new ResponseMsg<T>();
        msg.code = 500;
        msg.desc = desc;
        return msg;
    }

    public static <T> ResponseMsg BAD_REQUEST(String desc) {
        ResponseMsg<T> msg = new ResponseMsg<T>();
        msg.code = 400;
        msg.desc = desc;
        return msg;
    }

    public static <T> ResponseMsg FORBIDDEN(String desc) {
        ResponseMsg<T> msg = new ResponseMsg<T>();
        msg.code = 403;
        msg.desc = desc;
        return msg;
    }

    public static <T> ResponseMsg UNAUTHORIZED(String desc) {
        ResponseMsg<T> msg = new ResponseMsg<T>();
        msg.code = 401;
        msg.desc = desc;
        return msg;
    }

    public static <T> ResponseMsg OK(String desc) {
        ResponseMsg<T> msg = new ResponseMsg<T>();
        msg.code = 200;
        msg.desc = desc;
        return msg;
    }

    public static <T> ResponseMsg OK() {
        ResponseMsg<T> msg = new ResponseMsg<T>();
        msg.code = 200;
        msg.desc = "OK";
        return msg;
    }


    public static <T> ResponseMsg instance(Integer code, String desc, T data) {
        ResponseMsg<T> msg = new ResponseMsg<T>();
        msg.code = code;
        msg.desc = desc;
        msg.data = data;
        return msg;
    }

    public static <T> ResponseMsg instance(Integer code, String desc) {
        ResponseMsg<T> msg = new ResponseMsg<T>();
        msg.code = code;
        msg.desc = desc;
        return msg;
    }

    public static <T> ResponseMsg instance(T data) {
        ResponseMsg<T> msg = new ResponseMsg<T>();
        msg.code = 200;
        msg.desc = "OK";
        msg.data = data;
        return msg;
    }


}