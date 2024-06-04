package io.github.casl0.techbooksexplorer.book;

import org.hibernate.validator.constraints.ISBN;
import org.hibernate.validator.constraints.URL;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 技術書新規作成用のリクエストボディ
 */
@Data
public class BookRequest {
    /**
     * タイトル
     */
    @NotNull
    @NotBlank
    @Size(max = 255)
    private String title;

    /**
     * ISBN
     */
    @NotNull
    @ISBN(type = ISBN.Type.ISBN_13)
    private String isbn;

    /**
     * 価格
     */
    private String price;

    /**
     * URL
     */
    @NotNull
    @NotBlank
    @URL
    private String url;

    /**
     * 出版社
     */
    @NotNull
    @NotBlank
    private String publisher;

    /**
     * 出版日
     */
    @NotNull
    @NotBlank
    private String publishedAt;
}
