package com.education.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

// Bookmark Entity 객체와 Pageable 정보를 가지고 있는 dto
@Getter
@Setter
public class BookmarksDto {
    private List<BookmarkDto> data; // 조회된 북마크 집합
    private long totalElements;
    private int totalPages;
    private int currentPage;
    @JsonProperty(value = "isFirst")
    private boolean first;
    @JsonProperty(value = "isLast")
    private boolean last;
    private boolean hasNext;
    private boolean hasPrevious;

    public BookmarksDto(Page<BookmarkDto> bookmarksPage) {
        this.setData(bookmarksPage.getContent());
        this.setTotalElements(bookmarksPage.getTotalElements());
        this.setTotalPages(bookmarksPage.getTotalPages());
        this.setCurrentPage(bookmarksPage.getNumber() + 1);
        this.setFirst(bookmarksPage.isFirst());
        this.setLast(bookmarksPage.isLast());
        this.setHasNext(bookmarksPage.hasNext());
        this.setHasPrevious(bookmarksPage.hasPrevious());
    }
}
