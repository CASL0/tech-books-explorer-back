package io.github.casl0.techbooksexplorer.publisher;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Publisherオブジェクトへのエントリポイントとなるリポジトリ
 */
@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
    /**
     * 出版社名からPublisherオブジェクトを取得します
     * 
     * @param name 出版社名
     * @return 見つかったPublisherオブジェクト、見つからなかった場合はnull
     */
    Optional<Publisher> findFirstByName(final String name);
}
