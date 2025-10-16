package com.education.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

// 입력을 위한 요청 처리 객체 + 유효값 처리
@Getter
@Setter
public class CreateBookmarkRequest {
    @NotEmpty(message = "제목은 필수 입력 값입니다.")
    private String title;
    @NotEmpty(message = "URL은 필수 입력 값입니다.")
    private String url;
}
