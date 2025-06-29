package io.github.casl0.techbooksexplorer.book;

import io.github.casl0.techbooksexplorer.publisher.PublisherRepository;
import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;

/**
 * 技術書データ関連のビジネスロジックを扱うサービス
 */
@Service
@RequiredArgsConstructor
public class BookService {
  /**
   * 技術書データへのエントリポイント
   */
  @NonNull private final BookRepository books;

  /**
   * 出版社データへのエントリポイント
   */
  @NonNull private final PublisherRepository publishers;

  /**
   * 技術書を新規登録
   *
   * @param bookRequest 登録する技術書情報
   * @return 登録した技術書ID
   * @throws ErrorResponseException
   *             出版社が見つからなかった or 不正なDateTime文字列の場合に400エラーを返す
   *             登録済みのISBNの場合に409エラーを返す
   */
  public Integer createBook(final BookRequest bookRequest) throws ErrorResponseException {
    try {
      final var book = toModel(bookRequest);
      return books.save(book).getId();
    } catch (NoSuchElementException e) {
      e.printStackTrace();
      final var problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
      problemDetail.setDetail("出版社が見つかりませんでした");
      throw new ErrorResponseException(HttpStatus.BAD_REQUEST, problemDetail, null);
    } catch (DateTimeParseException e) {
      e.printStackTrace();
      final var problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
      problemDetail.setDetail("出版日時の形式が誤っています");
      throw new ErrorResponseException(HttpStatus.BAD_REQUEST, problemDetail, null);
    } catch (DataIntegrityViolationException e) {
      e.printStackTrace();
      final var problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
      problemDetail.setDetail("既に登録済みのISBNです");
      throw new ErrorResponseException(HttpStatus.CONFLICT, problemDetail, null);
    }
  }

  /**
   * IDから技術書を取得します
   *
   * @param id 技術書のID
   * @return 見つかった技術書
   * @throws ErrorResponseException 見つからなかった場合は404を返す
   */
  public Book findById(final Integer id) throws ErrorResponseException {
    return books.findById(id).orElseThrow(() -> new ErrorResponseException(HttpStatus.NOT_FOUND));
  }

  /**
   * ISBNから技術書を取得します
   *
   * @param isbn 技術書のISBN
   * @return 見つかった技術書
   * @throws ErrorResponseException 見つからなかった場合は404を返す
   */
  public Book findByIsbn(final String isbn) throws ErrorResponseException {
    return books
        .findByIsbn(isbn)
        .orElseThrow(() -> new ErrorResponseException(HttpStatus.NOT_FOUND));
  }

  /**
   * ページ内の技術書を取得します
   *
   * @param page ページ番号
   * @param pageSize 1ページのサイズ
   * @param keyword 検索キーワード
   * @return 技術書のページ
   * @throws ErrorResponseException 技術書が見つからなかった場合
   */
  public Page<Book> findPaginated(
      final Integer page, final Integer pageSize, final Optional<String> keyword)
      throws ErrorResponseException {
    final var result =
        keyword
            .map(
                item ->
                    books.findByTitleContainingOrderByPublishedAtDesc(
                        item, PageRequest.of(page, pageSize)))
            .orElse(books.findAllByOrderByPublishedAtDesc(PageRequest.of(page, pageSize)));
    if (result.isEmpty()) {
      throw new ErrorResponseException(HttpStatus.NOT_FOUND);
    }
    return result;
  }

  /**
   * リクエストボディをエンティティに変換する
   *
   * @param bookRequest リクエストボディ
   * @return 変換後のエンティティ
   * @throws NoSuchElementException 出版社が見つからない場合にスローする
   * @throws DateTimeParseException 不正なDateTimeの場合にスローする
   */
  private Book toModel(final BookRequest bookRequest)
      throws NoSuchElementException, DateTimeParseException {
    final var publisher = publishers.findFirstByName(bookRequest.getPublisher()).orElseThrow();
    return new Book(
        bookRequest.getTitle(),
        bookRequest.getIsbn(),
        bookRequest.getPrice(),
        bookRequest.getUrl(),
        publisher,
        Instant.parse(bookRequest.getPublishedAt()));
  }
}
