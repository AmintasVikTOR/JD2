package by;

import by.domain.Dealer;
import by.domain.User;
import by.service.DealerService;
import by.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringDemo {
    /*3. Create logger object*/
    private static final Logger log = Logger.getLogger(by.SpringDemo.class);

    public static void main(String[] args) {

        //1. Download pom xml dependencies
        //2. Create application-context.xml or use annotation @Component
        //3. Use @Autowired for DI
        //4. Use @Qualifier for bean name definition
        //5. For test purpose only: use ClassPathXmlApplicationContext or AnnotationConfigApplicationContext
        //   for Spring Context calling and getting bean


        //ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application-context.xml");
        ApplicationContext context = new AnnotationConfigApplicationContext("by");
        ApplicationContext context2 = new AnnotationConfigApplicationContext("by");
//        User user = (User) context.getBean("user");
//        TestSpring testSpring = (TestSpring) context.getBean("testSpring");
//        HttpClient httpClient = (HttpClient) context.getBean("httpClient");
//
//        NotBeanByDefault bean = context.getBean(NotBeanByDefault.class);
//        Role role = (Role) context.getBean("role");

//        System.out.println(user);
//        System.out.println(role);
//        System.out.println(testSpring);
//        System.out.println(httpClient);
//        System.out.println(bean);

        //6. Getting bean by name (possible get bean by class name)
//        UserDao userDaoImpl = (UserDao) context.getBean("userDaoImpl");
//        UserDao userDaoImplByClassName = context.getBean(UserDao.class);
//        UserService userService = context.getBean(UserService.class);
//
//        //7. Call method as usual
//        String login = userDaoImpl.findOne(Long.parseLong("6")).getLogin();
//        String login2 = userDaoImplByClassName.findOne(Long.parseLong("6")).getLogin();
//        System.out.println(login);
//        System.out.println(login2);
//        System.out.println(userService.findOne(Long.parseLong("6")).getLogin());
//
//        DataSource bean = context.getBean(DataSource.class);
//        System.out.println(bean);

//        UserDao userRepositoryJdbcTemplate = (UserDao) context.getBean("userRepositoryJdbcTemplate");
//        for (User user : userRepositoryJdbcTemplate.findAll()) {
//            //System.out.println(user);
//            log.info(user.getLogin());
//        }

//        UserDao userRepositoryJdbcTemplate = (UserDao) context.getBean("userRepositoryJdbcTemplate");
//        for (User user : userRepositoryJdbcTemplate.findAll()) {
//            System.out.println(user);
//        }
        UserService userService = (UserService) context.getBean("userService");
        for (User user : userService.findAll()) {
            //System.out.println(user);
            log.info(user.getLogin());
        }
//
//        //added dealer
//        DealerDao dealerDaoRepositoryJdbcTemplate = (DealerDao) context2.getBean("dealerRepositoryJdbcTemplate");
//        for (Dealer dealer : dealerDaoRepositoryJdbcTemplate.findAll()) {
//            System.out.println(dealer);
//        }
        DealerService dealerService = (DealerService) context2.getBean("dealerService");
        for (Dealer dealer : dealerService.findAll()) {
            //System.out.println(user);
            log.info(dealer.getName());
        }

    }
}
