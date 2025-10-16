package com.education.service;

import com.education.domain.Bookmark;
import com.education.dto.BookmarkDto;
import com.education.dto.BookmarksDto;
import com.education.dto.request.CreateBookmarkRequest;
import com.education.repository.BookmarkRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@Transactional
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;

    @Transactional(readOnly = true)
    public BookmarksDto getBookmarks(Integer page) {
        int pageNo = page < 1 ? 0 : page - 1;
        Pageable pageable =
                PageRequest.of(pageNo, 10, Sort.Direction.DESC, "createdAt");
        // return new BookmarksDto(bookmarkRepository.findAll(pageable));
        // 엔티티와 직접적인 연결을 끊기 위한 dto 변경
        // Page<BookmarkDto> bookmarkPage =
        //         bookmarkRepository
        //                 .findAll(pageable)
        //                 .map(BookmarkDto::fromEntity);
        Page<BookmarkDto> bookmarkPage =
                bookmarkRepository
                    .findByBookmarks(pageable);
        return new BookmarksDto(bookmarkPage);
    }

    // 북마크 검색
    @Transactional(readOnly = true)
    public BookmarksDto searchBookmarks(String query, Integer page) {
        int pageNo = page < 1 ? 0 : page - 1;
        Pageable pageable =
                PageRequest.of(pageNo, 10, Sort.Direction.DESC, "createdAt");
        // Page<BookmarkDto> bookmarkPage = bookmarkRepository.searchBookmark(query, pageable);
        Page<BookmarkDto> bookmarkPage = bookmarkRepository.findByTitleContainsIgnoreCase(query, pageable);
        return new BookmarksDto(bookmarkPage);
    }
    
    // 북마크 생성
    public BookmarkDto createBookmark(@Valid CreateBookmarkRequest request) {
        Bookmark bookmark
                = new Bookmark(null, request.getTitle(), request.getUrl(), Instant.now());
        Bookmark savedBookmark = bookmarkRepository.save(bookmark);
        return BookmarkDto.fromEntity(savedBookmark);
    }
}
