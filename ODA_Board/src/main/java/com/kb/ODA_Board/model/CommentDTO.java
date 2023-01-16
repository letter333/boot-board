package com.kb.ODA_Board.model;

import lombok.Data;

@Data
public class CommentDTO {
    private int cno;
    private String comment;
    private String member_id;
    private int comment_bno;
}
