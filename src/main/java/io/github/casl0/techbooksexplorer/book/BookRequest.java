package io.github.casl0.techbooksexplorer.book;

import org.hibernate.validator.constraints.ISBN;
import org.hibernate.validator.constraints.URL;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 技術書新規作成用のリクエストボディ
 *
 * @param title タイトル
 * @param isbn ISBN
 * @param price 価格
 * @param url URL
 * @param publisher 出版社
 * @param publishedAt 出版日
 */
@Data
public class BookRequest {
    @NotNull
    @NotBlank
    @Size(max = 255)
    private String title;

    @NotNull
    @ISBN(type = ISBN.Type.ISBN_13)
    private String isbn;

    private String price;

    @NotNull
    @NotBlank
    @URL
    private String url;

    @NotNull
    @NotBlank
    private String publisher;

    @NotNull
    @NotBlank
    private String publishedAt;
}
