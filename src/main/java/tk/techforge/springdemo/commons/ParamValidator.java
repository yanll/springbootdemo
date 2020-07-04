package tk.techforge.springdemo.commons;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author: YANLL
 * @version:
 * @since: 2020/7/3
 */
//@FunctionalInterface
public class ParamValidator<T> {

//    public ResponseMsg validate(T t);
//
//    public Function<String, Boolean> checkIsValid(  ) {
//        return buildCheck(s);
//    }


    public static <T> Function<T, T> buildCheck(Consumer<T> checkConsumer) {
        return (checkContext) -> {
            checkConsumer.accept(checkContext);
            return checkContext;
        };
    }
}
