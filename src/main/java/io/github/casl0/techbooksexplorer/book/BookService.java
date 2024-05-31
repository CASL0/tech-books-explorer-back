package io.github.casl0.techbooksexplorer.book;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;

import io.github.casl0.techbooksexplorer.publisher.PublisherRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * 技術書データ関連のビジネスロジックを扱うサービス
 */
@Service
@RequiredArgsConstructor
public class BookService {
    /**
     * 技術書データへのエントリポイント
     */
    @NonNull
    private final BookRepository books;

    /**
     * 出版社データへのエントリポイント
     */
    @NonNull
    private final PublisherRepository publishers;

    /**
     * 技術書を新規登録
     * 
     * @param bookRequest 登録する技術書情報
     * @return 登録した技術書ID
     * @throws ErrorResponseException 出版社が見つからなかった or 不正なDateTime文字列の場合に400エラーを返す
     */
    public Integer createBook(BookRequest bookRequest) throws ErrorResponseException {
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
        }
    }

    /**
     * リクエストボディをエンティティに変換する
     * 
     * @param bookRequest リクエストボディ
     * @return 変換後のエンティティ
     * @throws NoSuchElementException 出版社が見つからない場合にスローする
     * @throws DateTimeParseException 不正なDateTimeの場合にスローする
     */
    private Book toModel(BookRequest bookRequest) throws NoSuchElementException, DateTimeParseException {
        final var publisher = publishers.findFirstByName(bookRequest.getPublisher()).orElseThrow();
        final var book = new Book();
        book.setTitle(bookRequest.getTitle());
        book.setIsbn(bookRequest.getIsbn());
        book.setPrice(bookRequest.getPrice());
        book.setUrl(bookRequest.getUrl());
        book.setPublisher(publisher);
        book.setPublishedAt(Instant.parse(bookRequest.getPublishedAt()));
        return book;
    }
}
