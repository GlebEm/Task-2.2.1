package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class); //В AppConfig добавим Car в
                                                                        // factoryBean.setAnnotatedClasses

        CarService carService = context.getBean(CarService.class);
        carService.add(new Car("NissanAlmera", 4));
        carService.add(new Car("BMWx3", 2));
        carService.add(new Car("ToyotaCelica", 1));
        carService.add(new Car("DodgeRamTRX", 5));

        List<Car>cars = carService.listCars();

        UserService userService = context.getBean(UserService.class);


        userService.add(new User("Патрик", "Бэйтман", "AmericanPsycho@mail.ru",cars.get(0)));
        userService.add(new User("Майкл", "Бьюри", "TheBigShort@mail.ru",cars.get(1)));
        userService.add(new User("Брюс", "Уэйн", "TheDarkKnight@mail.ru",cars.get(2)));
        userService.add(new User("Тревор", "Резник", "ElMaquinista@mail.ru",cars.get(3)));
        //
//        Car car = new Car("TestCar", 1);
//        User user1 = new User("Test", "Person", "test@mail.ru",car);
//
//        //

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("--------------------------------------------");
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
            System.out.println();
        }
        System.out.println("Ищем DodgeRamTRX, 5ой серии");
        System.out.println(userService.findOwnerByModel("DodgeRamTRX",5));
        //Owning side - та сущность , где НЕ используется mappedBy
        //Создайте несколько пользователей с машинами, добавьте их в базу данных, вытащите обратно.

        context.close();
    }
}
