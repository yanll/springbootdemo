package tk.techforge.springdemo.commons;

import lombok.Data;

/**
 * Created by YANLL on 2017/2/28.
 */
@Data
public class Pagination {
    private static final long serialVersionUID = -2429124473690465105L;
    private Long limit;
    private Long page;
    private Long total;

    public Pagination() {
    }

    public Pagination(Long page, Long limit) {
        this.page = page;
        this.limit = limit;
    }

}
