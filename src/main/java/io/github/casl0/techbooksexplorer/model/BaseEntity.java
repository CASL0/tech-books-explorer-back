package io.github.casl0.techbooksexplorer.model;

import java.io.Serializable;
import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * IDプロパティ付きのベースクラス
 * IDを持っているエンティティは本クラスを継承する
 * 
 * @param id ID
 * @param createdAt レコード作成日時
 * @param updatedAt レコード更新日時
 */
@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_at", nullable = false)
    @CreatedDate
    @NotNull
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    @NotNull
    private Instant updatedAt;
}
