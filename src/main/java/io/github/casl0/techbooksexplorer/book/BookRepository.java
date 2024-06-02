package io.github.casl0.techbooksexplorer.book;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Bookオブジェクトへのエントリポイントとなるリポジトリ
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    /**
     * ISBNから技術書を取得する
     * 
     * @param isbn ISBN
     * @return 見つかった技術書、見つからなかった場合はnull
     */
    @Transactional(readOnly = true)
    public Optional<Book> findByIsbn(final String isbn);
}
