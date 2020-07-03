package tk.techforge.springdemo.commons;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * Created by YANLL on 2017/2/27.
 */
public class PaginationUtil {

    public static final long DEFAULT_PAGE = 1L;
    public static final long DEFAULT_LIMIT = 20L;

    public static Page toPage(Pagination pagination) {
        Long page = pagination == null ? DEFAULT_PAGE : pagination.getPage();
        Long limit = pagination == null ? DEFAULT_LIMIT : pagination.getLimit();
        Page rs = new Page(page == null ? DEFAULT_PAGE : max(page), limit == null ? DEFAULT_LIMIT : max(limit));
        rs.setSearchCount(true);
        return rs;
    }

    public static Page toPage(Long page, Long limit) {
        Page rs = new Page(page == null ? DEFAULT_PAGE : max(page), limit == null ? DEFAULT_LIMIT : max(limit));
        rs.setSearchCount(true);
        return rs;
    }

    public static Page toPage(Long page, Long limit, Boolean isSearchCount) {
        Page rs = new Page(page == null ? DEFAULT_PAGE : max(page), limit == null ? DEFAULT_LIMIT : max(limit));
        rs.setSearchCount(isSearchCount);
        return rs;
    }


    private static Long max(Long n) {
        return Math.max(n.intValue(), 1L);
    }

}
