package com.example.spring.spring.repository;

import com.example.spring.spring.dao.UserTb;

public interface UserTbRepositoryCustom {

    public boolean LoginCheck(UserTb userTb);

    public UserTb getUserTbByAccount(String account);

    public String getNameByAccount(String account);

    public String getNameByPk(int pk);

}
