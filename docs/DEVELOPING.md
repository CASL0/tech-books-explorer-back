# 開発用ドキュメント

## ビルドする

DevContainer の使用を推奨する。

コンテナの構成は以下のようになっている。

- `web_server`
  - `SpringBoot` アプリケーション開発用コンテナ
  - [Base Development Container Images](https://hub.docker.com/r/microsoft/devcontainers-base)に `Features` で `Java` と `Gradle` をインストールしている
- `db`
  - `SpringBoot` アプリケーションからアクセスするデータベース用の `MySQL` コンテナ
- `prometheus`
  - `SpringBoot` アプリケーションのメトリクス収集用の `Prometheus` コンテナ
- `grafana`
  - 上記で収集したメトリクスのダッシュボード用の `Grafana` コンテナ

アプリの実行方法は公式の資料を参照すること。

- <https://spring.pleiades.io/spring-boot/gradle-plugin/running.html>

## 静的解析

`Gradle`プラグインから[error-prone](https://github.com/google/error-prone)を使って静的解析している。

コンパイルにフックするので静的解析でエラーとなった場合は、コンパイルエラーとなる。

## Formatter

[Spotless plugin for Gradle](https://github.com/diffplug/spotless/tree/main/plugin-gradle)経由で各言語向けの formatter を実行している。

- Java: [google-java-format](https://github.com/google/google-java-format)
- Gradle: [groovy-eclipse](https://github.com/groovy/groovy-eclipse)
- SQL: [dbeaver](https://dbeaver.io/)
- JSON: [prettier](https://prettier.io/)
- YAML: [prettier](https://prettier.io/)
- Markdown: [prettier](https://prettier.io/)

## 依存ライブラリ

dependency locking している。新しく依存関係を追加する際は、以下のコマンドを実行すること。

```sh
./gradlew dependencies --write-locks
```

パッケージのアップデートの管理は[renovate](https://github.com/renovatebot/renovate)を使っている。

[dependabot](https://docs.github.com/ja/code-security/dependabot)は`gradle.lockfile`の更新をしてくれなかったので不採用。

## ドキュメンテーション

### アプリ

[Doxygen](https://www.doxygen.nl/)を使って、`javadoc` コメントから生成している。

`javadoc` と異なりクラス関係や呼び出し関係が分かるため `doxygen` を使っている。

### データベース

[tbls](https://github.com/k1LoW/tbls)を使って、データベースから ER 図を生成している。

### API

`openapi` で作成している。
[springdoc-openapi](https://springdoc.org/)を使うこともできるがコメントで同等の内容を書く必要があるため、現状は素で yaml を編集している。
