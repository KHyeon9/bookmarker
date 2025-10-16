package com.education.dto;

import com.education.domain.Bookmark;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
// @NoArgsConstructor
@AllArgsConstructor
public class BookmarkDto {
    private Long id;
    private String title;
    private String url;
    private Instant createdAt;

    public static BookmarkDto fromEntity(Bookmark bookmark) {
        return new BookmarkDto(
                bookmark.getId(),
                bookmark.getTitle(),
                bookmark.getUrl(),
                bookmark.getCreatedAt()
        );
    }
}
