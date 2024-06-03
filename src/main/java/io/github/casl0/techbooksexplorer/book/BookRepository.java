package io.github.casl0.techbooksexplorer.book;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    /**
     * ページ内の技術書をすべて取得する
     * 
     * @param pageable ページ
     * @return 技術書ページ
     */
    @Transactional(readOnly = true)
    public Page<Book> findAllByOrderByPublishedAtDesc(final Pageable pageable);

    /**
     * ページ内の技術書をタイトルで検索する
     * 
     * @param keyword 検索するキーワード
     * @param pageable ページ
     * @return 技術書ページ
     */
    @Transactional(readOnly = true)
    public Page<Book> findByTitleContainingOrderByPublishedAtDesc(
        final String keyword,
        final Pageable pageable);
}
