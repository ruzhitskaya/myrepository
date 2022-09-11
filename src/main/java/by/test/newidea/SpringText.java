package by.test.newidea;

import by.test.newidea.domain.User;
import by.test.newidea.repository.UserRepositoryInterface;
import by.test.newidea.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpringText {
    public static HashMap<String, Integer> statistic = new HashMap<>();

    public static void main(String[] args) {



        // использование xml-файла
//        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:application-context.xml");
//
//        User user1 = classPathXmlApplicationContext.getBean("user1", User.class);
//        User user2 = classPathXmlApplicationContext.getBean("user2", User.class);
//
//        System.out.println(user1);
//        System.out.println(user2);
// использование аннотаций
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext("by.test.newidea");

//        UserRepositoryInterface userRerository = annotationConfigApplicationContext.getBean(
//                "userRerository", UserRepositoryInterface.class);
//
//        for (User user : userRerository.findAll()) {
//            System.out.println(user);
//        }
//      вызываем с использованием спринга метод

       UserService userService = annotationConfigApplicationContext.getBean(UserService.class);

   //     userService.findById(898989898L);

       List<User> all = userService.findAll();
//      //  Map<String, Object> userStats = userService.getUserStats();
//
        for (User user : all) {
           System.out.println(user);
       }

//
//        for (Map.Entry<String, Object> stringObjectEntry : userStats.entrySet()) {
//            System.out.println(stringObjectEntry.getKey() + " : " + stringObjectEntry.getValue());
//        }

        User user = new User();
        user.setUserName("JDBC");
       user.setSurname("Template");
      user.setBirth(new Timestamp(new Date().getTime()));
        user.setCreateDate(new Timestamp(new Date().getTime()));
        user.setModificationDate(new Timestamp(new Date().getTime()));
        user.setIsDeleted(false);
//        user.setWeight(87D);
        System.out.println(user);
//
        User user1 = userService.create(user);
       System.out.println(user1);

        List<User> all1 = userService.findAll();
//      //  Map<String, Object> userStats = userService.getUserStats();
//
        for (User user2 : all1) {
            System.out.println(user2);
        }

        List<User> all3 = userService.findAll();
//      //  Map<String, Object> userStats = userService.getUserStats();
//
        for (User user3 : all3) {
            System.out.println(user3);
        }
        System.out.println("==========статистика:=============");
        System.out.println(statistic);

    }
}
