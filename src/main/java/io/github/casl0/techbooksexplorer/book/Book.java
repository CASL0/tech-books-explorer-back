package io.github.casl0.techbooksexplorer.book;

import java.time.Instant;

import io.github.casl0.techbooksexplorer.model.BaseEntity;
import io.github.casl0.techbooksexplorer.publisher.Publisher;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 技術書のデータベースエンティティ
 */
@Entity
@Table(name = "books")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Book extends BaseEntity {
    /**
     * 技術書タイトル
     */
    @Column(length = 255, nullable = false)
    @NotNull
    @Size(max = 255)
    private String title;

    /**
     * ISBN
     */
    @Column(length = 20, nullable = false)
    @NotNull
    @Size(max = 20)
    private String isbn;

    /**
     * 価格
     */
    @Column(length = 10, nullable = true)
    @Size(max = 10)
    private String price;

    /**
     * 技術書ページのURL
     */
    @Column(columnDefinition = "TEXT", nullable = false)
    @NotNull
    private String url;

    /**
     * 出版社
     */
    @ManyToOne
    @JoinColumn(name = "publisher_id")
    @NotNull
    private Publisher publisher;

    /**
     * 出版日
     */
    @Column(name = "published_at", nullable = false)
    @NotNull
    private Instant publishedAt;
}
