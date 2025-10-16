package com.education.controller;

import com.education.domain.Bookmark;
import com.education.repository.BookmarkRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.Instant;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// test를 위해 무작위 포트 사용
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// mvc 테스트를 위한 자동 설정
@AutoConfigureMockMvc
// postgresql로 testcontainer 사용해 테스트 진행
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:tc:postgresql:14-alpine:///demo"
})
class BookmarkControllerTest {
    // test를 위한 MockMvc 객체를 주입
    @Autowired
    private MockMvc mvc;

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @BeforeEach
    void setUp() {
        bookmarkRepository.deleteAllInBatch();

        List<Bookmark> bookmarks = List.of(
                new Bookmark(null, "Google", "https://www.google.com", Instant.now()),
                new Bookmark(null, "YouTube", "https://www.youtube.com", Instant.now()),
                new Bookmark(null, "Facebook", "https://www.facebook.com", Instant.now()),
                new Bookmark(null, "Twitter", "https://www.twitter.com", Instant.now()),
                new Bookmark(null, "Instagram", "https://www.instagram.com", Instant.now()),
                new Bookmark(null, "LinkedIn", "https://www.linkedin.com", Instant.now()),
                new Bookmark(null, "Reddit", "https://www.reddit.com", Instant.now()),
                new Bookmark(null, "Stack Overflow", "https://stackoverflow.com", Instant.now()),
                new Bookmark(null, "GitHub", "https://github.com", Instant.now()),
                new Bookmark(null, "Medium", "https://medium.com", Instant.now()),
                new Bookmark(null, "Netflix", "https://www.netflix.com", Instant.now()),
                new Bookmark(null, "Amazon", "https://www.amazon.com", Instant.now()),
                new Bookmark(null, "Wikipedia", "https://www.wikipedia.org", Instant.now()),
                new Bookmark(null, "Kakao", "https://www.kakao.com", Instant.now()),
                new Bookmark(null, "Naver", "https://www.naver.com", Instant.now())
        );

        bookmarkRepository.saveAll(bookmarks);
    }

    @Disabled
    @Test
    void shoudBookmarks() throws Exception {
        // mvc.perform(MockMvcRequestBuilders.get("/api/bookmarks"))
        //      .andExpect(status().isOk());
        mvc.perform(MockMvcRequestBuilders.get("/api/bookmarks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(15))
                .andExpect(jsonPath("$.totalPages").value(2))
                .andExpect(jsonPath("$.currentPage").value(1))
                .andExpect(jsonPath("$.isFirst").value(true))
                .andExpect(jsonPath("$.isLast").value(false))
                .andExpect(jsonPath("$.hasNext").value(true))
                .andExpect(jsonPath("$.hasPrevious").value(false));
    }
    // 파라미터 테스트
    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1, 15, 2, 1, true, false, true, false",
            "2, 15, 2, 2, false, true, false, true"
    })
    void shoudBookmarks_param(
            int pageNo,
            int totalElements,
            int totalPages,
            int currentPage,
            boolean isFirst,
            boolean isLast,
            boolean hasNext,
            boolean hasPrevious
    ) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/bookmarks?page=" + pageNo))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(totalElements))
                .andExpect(jsonPath("$.totalPages").value(totalPages))
                .andExpect(jsonPath("$.currentPage").value(currentPage))
                .andExpect(jsonPath("$.isFirst").value(isFirst))
                .andExpect(jsonPath("$.isLast").value(isLast))
                .andExpect(jsonPath("$.hasNext").value(hasNext))
                .andExpect(jsonPath("$.hasPrevious").value(hasPrevious));
    }

    @Test
    void createBookmarkErrorCheck() throws Exception {
        MvcResult result = this.mvc.perform(
                MockMvcRequestBuilders
                        .post("/api/bookmarks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "url": "http://www.naver.com"
                                }
                            """)
                ).andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.field", is("title")))
                .andExpect(jsonPath("$.message", is("제목은 필수 입력 값입니다.")))
                .andExpect(jsonPath("$.status", is(400)))
                .andReturn();

        String contentType = result.getResponse().getContentType();
        String responseBody = result.getResponse().getContentAsString();

        System.out.println("반환된 결과의 contentType : " + contentType);
        System.out.println("반환된 결과의 내용 JSON : " + responseBody);
    }
}