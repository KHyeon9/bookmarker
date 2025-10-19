import React from 'react'
// 이름이 같아서 type으로 지정
import type { Bookmark } from '../types/bookmark'
import Link from 'next/link'

interface BookmarkProps {
  bookmark: Bookmark;
}

const Bookmark = ({ bookmark } : BookmarkProps) => {
  return (
    <div>
      <div className='alert alert-primary' role='alert'>
        <h5>
          <Link href={bookmark.url}>{bookmark.title}</Link>
        </h5>
      </div>
    </div>
  )

}

export default Bookmark
