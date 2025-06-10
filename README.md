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

[DEVELOPING.md](docs/DEVELOPING.md)をご覧ください。
