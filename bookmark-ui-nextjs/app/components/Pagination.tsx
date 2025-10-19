import React from 'react'
import { BookmarksResponse } from '../types/bookmark'
import Link from 'next/link';

interface PaginationProps {
  bookmarks: BookmarksResponse;
  query ?: string;
}

const Pagination = ({ bookmarks, query }: PaginationProps) => {
  const path = "/bookmarks";
  const queryParam = (query === undefined || query === '') ? {} : { query: query }

  // const firstPage = { pathName: path, query: { page: 1 } };
  // const nextPage = { pathName: path, query: { page: bookmarks.currentPage + 1 } };
  const previousPage = { pathName: path, query: { page: bookmarks.currentPage - 1, ...queryParam } };
  const nextPage = { pathName: path, query: { page: bookmarks.totalPages, ...queryParam } };
  return (
    <div>
      <nav aria-label="Page navigation">
        <ul style={ { display: 'flax', justifyContent: 'space-between', padding: 0, listStyle: 'none' } } className="pagination">
          <li className={ "page-item" + (bookmarks.hasPrevious? "" : "disalbe") }>
            <Link className="page-link" href={ previousPage }>Previous</Link>
          </li>
          <li className={ "page-item" + (bookmarks.hasNext? "" : "disalbe") }>
            <Link className="page-link" href={ nextPage }>Next</Link>
          </li>
        </ul>
      </nav>
    </div>
  )
}

export default Pagination
