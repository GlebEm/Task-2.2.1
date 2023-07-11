package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {


    private final SessionFactory sessionFactory; //внедрили через конструктор

    @Autowired
    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public void deleteAllUsers() {
        List<User> users = listUsers();
        for (User user : users) {
            sessionFactory.getCurrentSession().delete(user);
        }
    }

    @Override

    public User findOwnerByModel(String model, int series) {
        Query findCarQuery = sessionFactory.getCurrentSession().createQuery("FROM Car WHERE model=:car_model AND series=:car_series")
                .setParameter("car_model", model)
                .setParameter("car_series", series);
        List resultList = findCarQuery.getResultList();

//        if (!resultList.isEmpty()) {
            Car findCar = (Car) resultList.get(0);
            List<User> userList = listUsers();
            User FindUser = userList.stream()
                    .filter(user -> user.getCar().equals(findCar))
                    .findFirst()
                    .orElse(null);
            return FindUser;
//        }return null;
    }
}
