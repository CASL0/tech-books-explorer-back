package io.github.casl0.techbooksexplorer.book;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
        BookRequest request,
        BindingResult result) throws BindException, ErrorResponseException {
        if (result.hasErrors()) {
            throw new BindException(result);
        }

        var id = bookService.createBook(request);

        return ResponseEntity
            .created(
                ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(id)
                    .toUri())
            .build();
    }

}
