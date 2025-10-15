package com.education.repository;

import com.education.domain.Bookmark;
import com.education.dto.BookmarkDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    // jpql을 통해 dto로 변경해 반환
    @Query("""
        select 
            new com.education.dto.BookmarkDto(
                b.id,
                b.title,
                b.url,
                b.createdAt    
            )
        from 
            Bookmark b
    """)
    Page<BookmarkDto> findByBookmarks(Pageable pageable);
}
