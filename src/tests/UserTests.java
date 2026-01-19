package tests;

import model.Role;
import model.User;
import repository.InMemoryUserRepository;
import service.UserService;
import utils.TestAssertions;

public class UserTests {

    public static void main(String[] args) {
        UserService service = new UserService(new InMemoryUserRepository());

        User user = new User("Michael", "1234", Role.USER);
        service.save(user);

        TestAssertions.assertTrue(
                service.exists("Michael"),
                "User should exist after save"
        );

        TestAssertions.assertEquals(
                Role.USER,
                service.findByUsername("Michael").getRole(),
                "Role should be USER"
        );

        service.delete("Michael");

        TestAssertions.assertTrue(
                !service.exists("Michael"),
                "User should not exist after delete"
        );

        System.out.println("UserTests PASSED");
    }
}