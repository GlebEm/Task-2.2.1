package hiber.dao;

import hiber.model.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserDao {

   void add(User user);
   List<User> listUsers();
   void deleteAllUsers();
   User findOwnerByModel(String model,int series);

}
