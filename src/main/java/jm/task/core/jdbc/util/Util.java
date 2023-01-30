package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/dbtest";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "outy";

    private Connection connection;

    private static SessionFactory sessionFactory;

    public Connection getConnection() {
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Driver is not loaded.");
            e.printStackTrace();
        }
        return connection;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties stg = new Properties();
                stg.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                stg.put(Environment.URL, URL);
                stg.put(Environment.USER, USERNAME);
                stg.put(Environment.PASS, PASSWORD);
                stg.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                stg.put(Environment.SHOW_SQL, "true");
                stg.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                stg.put(Environment.HBM2DDL_AUTO, "");

                configuration.setProperties(stg);
                configuration.addAnnotatedClass(User.class);


                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
