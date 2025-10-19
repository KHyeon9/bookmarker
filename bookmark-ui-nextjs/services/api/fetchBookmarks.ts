import Bookmark from '@/app/components/Bookmark';
import { BookmarksResponse } from '@/app/types/bookmark';
import axios from 'axios';
import React from 'react'

// const API_BASE_URL = 'http://localhost:8080';
const getApiUrl = () => {
  // 서버 사이드 URL(도커로 배포된 서버 사이트 호출시 사용)
  const serverApiUrl = process.env.SERVER_SIDE_API_BASE_URL;
  // 클라이언트 사이드 URL(브라우저에서 호출할 공개용 API)
  const clientApiUrl = process.env.NEXT_PUBLIC_CLIENT_SIDE_API_BASE_URL;

  if (typeof window === 'undefined') {
    console.log("🏷️ 서버 사이드 실행 - 사용 URL : ", serverApiUrl);
    // 서버용 환경 변수 없으면 클라이언트 URL 반환
    return serverApiUrl || clientApiUrl;
  }

  console.log("🏷️ 클라이언트 사이드 실행 - 사용 URL : ", clientApiUrl);
  return clientApiUrl;
}

export const fetchBookmarks = async (page : number, query ?: string):Promise<BookmarksResponse> => {
  let apuUrl = getApiUrl();
  // 북마크들의 정보 가져오기
  const resp  = await axios.get<BookmarksResponse>(`${apuUrl}/api/bookmarks?page=${page}&query=${query}`);

  return resp.data;
}

export const saveBookmark = async (bookmark: { title: string, url: string }) => {
  try {
    let apuUrl = getApiUrl();
    const resp = await axios.post(`${apuUrl}/api/bookmarks`, bookmark);
    return resp.data; // 성공시 응답 데이터 반환
  } catch (error) {
    // 에러가 발생한 경우 에러 메시지 처리
    console.log('Error Saving Bookmark fail', error);
    throw new Error("Bookmark 저장에 실패하였습니다."); // 오류를 throw하여 호출자에게 전달
  }
}

