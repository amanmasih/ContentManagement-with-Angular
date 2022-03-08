package com.niit.usercontent.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Content {
    private int contentId;
    private String contentName;
    private String contentType;
    private String description;
}
