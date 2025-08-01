CREATE
    TABLE
        IF NOT EXISTS books(
            id INT AUTO_INCREMENT PRIMARY KEY,
            title VARCHAR(255) NOT NULL COMMENT '技術書タイトル',
            isbn VARCHAR(20) NOT NULL UNIQUE COMMENT 'ISBN',
            price VARCHAR(10) COMMENT '価格',
            url TEXT NOT NULL COMMENT '技術書ページのURL',
            publisher_id INT NOT NULL COMMENT '出版社ID',
            published_at TIMESTAMP NOT NULL COMMENT '出版日',
            created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
            updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON
            UPDATE
                CURRENT_TIMESTAMP,
                INDEX(title),
                FOREIGN KEY(publisher_id) REFERENCES publishers(id)
        );
