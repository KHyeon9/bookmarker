package com.education.controller;

import com.education.dto.BookmarkDto;
import com.education.dto.BookmarksDto;
import com.education.dto.request.CreateBookmarkRequest;
import com.education.service.BookmarkService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {
    public final BookmarkService bookmarkService;

    @GetMapping
    public BookmarksDto getBookmarks(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "query", defaultValue = "") String query
    ) {
        if (query == null || query.trim().length() == 0) {
            return bookmarkService.getBookmarks(page);
        }
        return bookmarkService.searchBookmarks(query, page);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED) // 실행 성공시 201 반환
    public BookmarkDto createBookmark(
            @RequestBody @Valid CreateBookmarkRequest request
    ) {
        return bookmarkService.createBookmark(request);
    }
}
