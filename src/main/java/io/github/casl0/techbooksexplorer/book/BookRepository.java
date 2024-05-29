package io.github.casl0.techbooksexplorer.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Bookオブジェクトへのエントリポイントとなるリポジトリ
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
