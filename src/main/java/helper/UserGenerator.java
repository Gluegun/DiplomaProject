package helper;

import app.model.User;

import java.util.Date;
import java.util.List;
import java.util.Random;

public class UserGenerator {

    public static User createRandomUser() {
        List<String> names = List.of("Bob", "Charlie", "John", "Paul", "Mark");

        byte isModerator;
        Date registerTime;
        String name;
        String email;
        String password;
        String code = null;
        String photoLink = null;

        Random random = new Random();
        isModerator = (byte) random.nextInt(2);
        registerTime = new Date();
        name = names.get(random.nextInt(names.size()));
        email = name + "@" + name + ".com";
        password = name.toUpperCase();

        return new User(isModerator, registerTime, name, email, password, code, photoLink);

    }

}
