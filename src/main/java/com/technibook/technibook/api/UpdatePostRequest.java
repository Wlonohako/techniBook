package com.technibook.technibook.api;


import com.technibook.technibook.model.PostVisibility;

import lombok.Data;

@Data
public class UpdatePostRequest {
    private String content;
    private PostVisibility visibility;
}
