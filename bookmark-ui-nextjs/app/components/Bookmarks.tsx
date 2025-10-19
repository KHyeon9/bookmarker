import React from 'react'
import { BookmarksResponse } from '../types/bookmark'
import Bookmark from './Bookmark'

// Prop타입 정의
interface BookmarksProps {
  bookmarks: BookmarksResponse;
}

const Bookmarks = ({ bookmarks } : BookmarksProps) => {
  return (
    <>
      {bookmarks.data.map(bookmark => (
        <Bookmark key={bookmark.id} bookmark={bookmark} />
      ))}
    </>
  )
}

export default Bookmarks
