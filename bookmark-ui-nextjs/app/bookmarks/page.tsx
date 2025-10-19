import { fetchBookmarks } from '@/services/api/fetchBookmarks';
import React from 'react'
import Bookmarks from '../components/Bookmarks';
import Pagination from '../components/Pagination';
import SearchFrom from '../components/SearchFrom';

// 서버를 요청하는 컴포넌트

// Next.js 15에서는 searchParam이 동기 객체가 아니라 Promise 형태이기 때문에 Promise로 처리
// url로 들어오는 page 받기
const page = async ({ searchParams }: {searchParams : Promise<{ page ?: string, query ?: string }>}) => { 
  const { page, query } = await searchParams;

  const pageNumber = page ? parseInt(page, 10): 1; // default 1로 잡고 int형으로 변환
  const queryString = query ? String(query) : "";

  const bookmarks = await fetchBookmarks(pageNumber, queryString);

  return (
    <div>
      <h2>Welcome to Bookmarks</h2>
      <SearchFrom />
      <Pagination bookmarks={ bookmarks } query={ queryString } />
      <ul>
        <Bookmarks bookmarks={ bookmarks } />
      </ul>
    </div>
  )
}

export default page
