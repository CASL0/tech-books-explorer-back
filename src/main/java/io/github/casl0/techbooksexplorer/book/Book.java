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
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 技術書のデータベースエンティティ
 * 
 * @param title 技術書タイトル
 * @param isbn ISBN
 * @param price 価格
 * @param url 技術書ページのURL
 * @param publisher 出版社
 * @param publishedAt 出版日
 */
@Entity
@Table(name = "books")
@Data
@EqualsAndHashCode(callSuper = true)
public class Book extends BaseEntity {
    @Column(length = 255, nullable = false)
    @NotNull
    @Size(max = 255)
    private String title;

    @Column(length = 20, nullable = false)
    @NotNull
    @Size(max = 20)
    private String isbn;

    @Column(length = 10, nullable = true)
    @Size(max = 10)
    private String price;

    @Column(columnDefinition = "TEXT", nullable = false)
    @NotNull
    private String url;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    @NotNull
    private Publisher publisher;

    @Column(name = "published_at", nullable = false)
    @NotNull
    private Instant publishedAt;
}
