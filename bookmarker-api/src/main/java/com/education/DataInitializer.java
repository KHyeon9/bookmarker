package com.education;

import com.education.domain.Bookmark;
import com.education.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final BookmarkRepository bookmarkRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Bookmark> bookmarks = getSampleBookmarks();
        bookmarkRepository.saveAll(bookmarks);
    }

    public static List<Bookmark> getSampleBookmarks() {
        return List.of(
                new Bookmark(null, "Google", "https://www.google.com", Instant.parse("2025-10-01T09:00:00Z")),
                new Bookmark(null, "YouTube", "https://www.youtube.com", Instant.parse("2025-10-02T10:15:00Z")),
                new Bookmark(null, "Facebook", "https://www.facebook.com", Instant.parse("2025-10-03T11:30:00Z")),
                new Bookmark(null, "Twitter", "https://www.twitter.com", Instant.parse("2025-10-04T12:45:00Z")),
                new Bookmark(null, "Instagram", "https://www.instagram.com", Instant.parse("2025-10-05T14:00:00Z")),
                new Bookmark(null, "LinkedIn", "https://www.linkedin.com", Instant.parse("2025-10-06T15:15:00Z")),
                new Bookmark(null, "Reddit", "https://www.reddit.com", Instant.parse("2025-10-07T16:30:00Z")),
                new Bookmark(null, "Stack Overflow", "https://stackoverflow.com", Instant.parse("2025-10-08T17:45:00Z")),
                new Bookmark(null, "GitHub", "https://github.com", Instant.parse("2025-10-09T09:00:00Z")),
                new Bookmark(null, "Medium", "https://medium.com", Instant.parse("2025-10-10T10:15:00Z")),
                new Bookmark(null, "Netflix", "https://www.netflix.com", Instant.parse("2025-10-11T11:30:00Z")),
                new Bookmark(null, "Amazon", "https://www.amazon.com", Instant.parse("2025-10-12T12:45:00Z")),
                new Bookmark(null, "Wikipedia", "https://www.wikipedia.org", Instant.parse("2025-10-13T14:00:00Z")),
                new Bookmark(null, "Kakao", "https://www.kakao.com", Instant.parse("2025-10-14T15:15:00Z")),
                new Bookmark(null, "Naver", "https://www.naver.com", Instant.parse("2025-10-15T16:30:00Z"))
        );
    }
}
