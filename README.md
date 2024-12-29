[![Build](https://github.com/CASL0/tech-books-explorer-back/actions/workflows/build.yml/badge.svg)](https://github.com/CASL0/tech-books-explorer-back/actions/workflows/build.yml)
[![License](https://img.shields.io/badge/license-MIT-blue)](https://opensource.org/license/mit)

# tech-books-explorer-back

技術書をブラウジングための API サーバーです。

## Getting started

技術書をキーワード検索します。

```sh
curl -X 'GET' \
  'https://localhost:8091/api/v1/books?q=java' \
  -H 'accept: application/json'
```

## Document

- [javadoc](https://casl0.github.io/tech-books-explorer-back/)
- [openapi](./docs/api/openapi.yaml)
- [DB](./docs/db/README.md)

## Developing

Java21 をインストールしてください。

以下のコマンドで開発サーバーを起動します。

```sh
./gradlew bootRun
```

### dependency の追加

dependency locking しています。新しく依存関係を追加する際は、以下のコマンドを実行してください。

```sh
./gradlew dependencies --write-locks
```
