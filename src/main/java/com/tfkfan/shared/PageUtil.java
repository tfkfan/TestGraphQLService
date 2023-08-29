package com.tfkfan.shared;

import com.tfkfan.graphql.PageInfo;
import com.tfkfan.graphql.PageSettings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Objects;

/**
 * @author Baltser Artem tfkfan
 */
public class PageUtil {
    private static final Long DEFAULT_PAGE_NUM = 0L;
    private static final Long DEFAULT_PAGE_SIZE = 50L;
    private static final Sort.Direction DEFAULT_DIRECTION = Sort.Direction.DESC;

    public static Pageable pageable(PageSettings pageSettings) {
        Long pageNum = Objects.isNull(pageSettings) ? DEFAULT_PAGE_NUM :
            (Objects.isNull(pageSettings.getPageNum()) ? DEFAULT_PAGE_NUM : pageSettings.getPageNum());
        Long pageSize = Objects.isNull(pageSettings) ? DEFAULT_PAGE_SIZE :
            (Objects.isNull(pageSettings.getPageSize()) ? DEFAULT_PAGE_SIZE : pageSettings.getPageSize());
        Sort.Direction direction = Objects.isNull(pageSettings) ? DEFAULT_DIRECTION:
            (Objects.isNull(pageSettings.getDescending()) ? DEFAULT_DIRECTION : toDirection(pageSettings.getDescending()));
        Sort sort = Objects.nonNull(pageSettings) && Objects.nonNull(pageSettings.getSortField()) ? Sort.by(direction, pageSettings.getSortField()) : Sort.unsorted();

        return PageRequest.of(pageNum.intValue(),
            pageSize.intValue(),
            sort);
    }

    private static Sort.Direction toDirection(Boolean isDescending){
        return isDescending ? Sort.Direction.DESC : Sort.Direction.ASC;
    }

    public static <E> PageInfo pageInfo(Page<E> page) {
        PageInfo info = new PageInfo();
        info.setPageNum(page.getPageable().getPageNumber());
        info.setPageSize(page.getPageable().getPageSize());
        info.setPageTotal(page.getTotalPages());
        info.setItemsCount((int) page.getTotalElements());
        info.setHasNextPage(page.hasNext());

        return info;
    }
}
