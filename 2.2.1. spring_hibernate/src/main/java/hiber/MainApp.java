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
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

      Car carBMW1 = new Car("BMW", 1);
      Car carAudi2 = new Car("Audi", 2);
      Car carToyota5 = new Car("Toyota", 5);
      Car carNissan4 = new Car("Nissan", 4);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      user1.setUserCar(carBMW1);
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      user2.setUserCar(carAudi2);
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      user3.setUserCar(carToyota5);
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");
      user4.setUserCar(carNissan4);

      carService.add(carBMW1);
      carService.add(carAudi2);
      carService.add(carToyota5);
      carService.add(carNissan4);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      System.out.println("______________________________________________________________________________________");
      System.out.println(carService.getUserByCarModelAndSeries("Toyota", 5));
      context.close();
   }
}
