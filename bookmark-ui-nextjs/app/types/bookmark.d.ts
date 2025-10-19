// 북마크 응답 타임 정의
export interface BookmarksResponse {
  data: Bookmark[],
  totalElements: number,
  totalPages: number,
  currentPage: number,
  isFirst: boolean,
  isLast: boolean,
  hasNext: boolean,
  hasPrevious: boolean
}

// 북마크 타입 정의
export interface Bookmark {
  id: number,
  title: string,
  url: string,
  created_at: Date,
  updated_at: Date
}