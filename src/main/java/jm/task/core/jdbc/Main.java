package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //Закомментировано использование UserService через UserDaoJDBCImpl

        /*UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Connor", "Ibragimov", (byte) 22);
        userService.saveUser("Denis", "Shefer", (byte) 24);
        userService.saveUser("Timur", "Odovan", (byte) 33);
        userService.saveUser("Anna", "Volkova", (byte) 19);

        List<User> listOfUsers = userService.getAllUsers();
        for (User u : listOfUsers) {
            System.out.println(u);
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();*/

        /*
        Использование UserService через UserDaoHibernateImpl
         */

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Peter", "Laiv", (byte) 30);

        userService.removeUserById(1);

        List<User> userList = userService.getAllUsers();
        for (User u : userList) {
            System.out.println(u);
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
