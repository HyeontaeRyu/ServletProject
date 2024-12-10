package com.board;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDTO {

  private int num;
  private String writer;
  private String email;
  private String subject;
  private String pass;
  private int ref;
  private int step;
  private int depth;
  private int readCount;
  private Timestamp regDate;
  private String content;
  private String ip;

}
