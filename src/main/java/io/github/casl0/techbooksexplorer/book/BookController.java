package io.github.casl0.techbooksexplorer.book;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.github.casl0.techbooksexplorer.dto.BookDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.hibernate.validator.constraints.ISBN;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 技術書関連のエンドポイントを扱うコントローラー
 */
@RestController
@RequestMapping(path = "/api/v1")
@RequiredArgsConstructor
public class BookController {
    @NonNull
    private final BookService bookService;

    /**
     * 技術書データを新規に作成する
     * 
     * @param request 作成する技術書データ
     * @param result バリデーション結果
     * @return 作成に成功した場合は201
     * @throws BindException バリデーションに失敗すると400を返す
     * @throws ErrorResponseException 出版社が見つからなかった or 不正なDateTime文字列の場合に400エラーを返す
     */
    @PostMapping("/books")
    public ResponseEntity<Void> create(
        @RequestBody
        @Validated
        final BookRequest request,
        final BindingResult result) throws BindException, ErrorResponseException {
        if (result.hasErrors()) {
            throw new BindException(result);
        }

        final var id = bookService.createBook(request);

        return ResponseEntity
            .created(
                ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(id)
                    .toUri())
            .build();
    }

    /**
     * 技術書データを取得する
     * 
     * @param id 取得する技術書のID
     * @return 見つかった技術書
     * @throws ErrorResponseException 見つからなかった場合は404を返す
     */
    @GetMapping("/books/{id}")
    public @ResponseBody BookDto findById(
        @PathVariable(name = "id", required = true)
        final Integer id) throws ErrorResponseException {
        return toBookDto(bookService.findById(id));
    }

    @GetMapping("/books")
    public @ResponseBody BookDto findByIsbn(
        @RequestParam(name = "isbn", required = true)
        @ISBN
        @Validated
        final String isbn) throws ErrorResponseException {
        return toBookDto(bookService.findByIsbn(isbn));
    }

    /**
     * モデルからDTOに変換する
     * 
     * @param book Bookモデル
     * @return Book DTO
     */
    private BookDto toBookDto(final Book book) {
        return new BookDto(
            book.getId(),
            book.getTitle(),
            book.getIsbn(),
            book.getPrice(),
            book.getUrl(),
            book.getPublisher().getName(),
            book.getPublishedAt().toString());
    }
}
