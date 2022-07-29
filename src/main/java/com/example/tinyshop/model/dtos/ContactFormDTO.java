package com.example.tinyshop.model.dtos;

import javax.validation.constraints.Size;

public class ContactFormDTO {

    private String subject;

    private String content;

    public  ContactFormDTO(){

    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
