CREATE
    TABLE
        IF NOT EXISTS publishers(
            id INT AUTO_INCREMENT PRIMARY KEY,
            name TEXT NOT NULL COMMENT '出版社名',
            description TEXT COMMENT '出版社の説明',
            url TEXT NOT NULL COMMENT '技術書ページのURL',
            created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
            updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON
            UPDATE
                CURRENT_TIMESTAMP
        );
