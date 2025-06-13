package io.github.casl0.techbooksexplorer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

/**
 * 技術書のDTOオブジェクト
 */
@Data
@AllArgsConstructor
public class BookDto {
  /**
   * 技術書のID
   */
  @NonNull private Integer id;

  /**
   * 技術書のタイトル
   */
  @NonNull private String title;

  /**
   * 技術書のISBN
   */
  @NonNull private String isbn;

  /**
   * 技術書の価格
   */
  private String price;

  /**
   * 技術書のURL
   */
  @NonNull private String url;

  /**
   * 技術書の出版社
   */
  @NonNull private String publisher;

  /**
   * 技術書の出版日
   */
  @NonNull private String publishedAt;
}
