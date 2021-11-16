package com.MateStudy.MateStudy.service;

public interface AccountService {
    String login(Long id, String pwd) throws Exception;

    void modify(Long id, String pwd);

    String getName(Long id) throws Exception;

}
