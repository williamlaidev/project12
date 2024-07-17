package main.java.app.dao;

import main.java.entity.CommonUser;
import java.util.List;

public interface UserDao {
    void save(CommonUser user);
    CommonUser findById(int userId);
    List<CommonUser> findAll();
    void update(CommonUser user);
    void delete(int userId);
}
