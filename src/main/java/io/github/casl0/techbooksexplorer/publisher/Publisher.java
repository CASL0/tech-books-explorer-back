package io.github.casl0.techbooksexplorer.publisher;

import io.github.casl0.techbooksexplorer.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 出版社のデータベースエンティティ
 */
@Entity
@Table(name = "publishers")
@Data
@EqualsAndHashCode(callSuper = true)
public class Publisher extends BaseEntity {
    /**
     * 出版社名
     */
    @Column(columnDefinition = "TEXT", nullable = false)
    @NotNull
    private String name;

    /**
     * 出版社の説明
     */
    @Column(columnDefinition = "TEXT", nullable = true)
    private String description;

    /**
     * 技術書ページのURL
     */
    @Column(columnDefinition = "TEXT", nullable = false)
    @NotNull
    private String url;
}
