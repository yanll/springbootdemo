package tk.techforge.springdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author: YANLL
 * @version:
 * @since: 2019/05/31
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HomeVO implements Serializable {

    private Long id;

    private String name;

    private String url;
}
