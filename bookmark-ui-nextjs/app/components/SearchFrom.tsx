// 이벤트가 있기 때문에 선언
'use client'

import { useRouter } from 'next/navigation';
import React, { useState } from 'react'

const SearchFrom = () => {
  const router = useRouter();
  const [query, setQuery] = useState("");

  // 모든 타입을 포함하는 상위 이벤트 타입 React.SyntheticEvent
  const handleSearch = async (e : React.SyntheticEvent) => {
    e.preventDefault(); // 화면 안넘어가게 셋팅
    if (query === '') {
      router.push('/bookmarks');
      return "";
    }
    router.push('/bookmarks?page=1&query=' + query);
  }

  return (
    <div className='pb-4'>
      <form className='d-flex' role='search' onSubmit={handleSearch}>
        <input 
          className='form-control me-2' 
          type='search' 
          placeholder='Search' 
          value={query} 
          onChange={ e => setQuery(e.target.value) } 
        />
        <button className='btn btn-outline-success' type='submit'>Search</button>
      </form>
    </div>
  )
}

export default SearchFrom
