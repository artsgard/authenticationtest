
CREATE TABLE IF NOT EXISTS test_result (
  id BIGINT NOT NULL AUTO_INCREMENT,
  url VARCHAR(255) NOT NULL,
  content_type VARCHAR(255) NOT NULL,
  field_content1 VARCHAR(255) NOT NULL,
  field_content2 VARCHAR(255) NOT NULL,
  field_name1 VARCHAR(255) NOT NULL,
  field_name2 VARCHAR(255) NOT NULL,
  server_error_response VARCHAR(255) NOT NULL,
  server_response VARCHAR(255) NOT NULL,
  status_code INT NOT NULL,
  test_date DATETIME NOT NULL,
  positive BIT(1) NOT NULL,
  PRIMARY KEY (id)
);