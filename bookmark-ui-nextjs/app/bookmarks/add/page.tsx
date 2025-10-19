// 기능이 있으므로 추가
'use client'

import { saveBookmark } from '@/services/api/fetchBookmarks';
import React, { useState } from 'react'

const page = () => {
  const [title, setTitle] = useState('');
  const [url, setUrl] = useState('');
  const [message, setMessage] = useState<String|null>(null);

  const handleSubmit = (e : React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    // 서버 호출할 지점

    // 유효성 검사
    if (!url) {
      alert("URL을 입력해주세요.");
      return ;
    }

    const payload = {
      title,
      url
    }

    try {
      saveBookmark(payload)
        .then(response => {
          console.log('Save Bookmark Response', response);
          setTitle('');
          setUrl('');
          setMessage('새로운 Bookmark를 저장하였습니다.');
        })
        .catch(error => {
          setMessage(error.message || '새로운 Bookmark 저장에 실패하였습니다.');
        });
    } catch (error) {
      // Promis가 reject된 경우
      setMessage('새로운 Bookmark 저장에 실패하였습니다.');
    }
  }

  return (
    <div>
      {message && <div className='alert alert-primary' role='alert'>{ message }</div>}
      <form onSubmit={ e => handleSubmit(e) }>
        <legend>새로운 Bookmark 등록</legend>
        <div className='mb-3'>
          <label htmlFor='title' className='form-lable'>제목</label>
          <input 
            type='text' 
            id='title' 
            className='form-control' 
            placeholder='Title' 
            value={ title } 
            onChange={ e => setTitle(e.target.value) }
          />
        </div>
        <div className='mb-3'>
          <label htmlFor='url' className='form-lable'></label>
          <input
            type='text'
            id='url'
            className='form-control'
            placeholder='URL'
            value={ url } 
            onChange={ e => setUrl(e.target.value) }
          />
        </div>
        <div className='d-grid gap-2 col-6 mx-auto'>
          <button type='submit' className='btn btn-primary'>Submit</button>
        </div>
      </form>
    </div>
  )
}

export default page