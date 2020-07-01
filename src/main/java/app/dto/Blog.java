package app.dto;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;


@Data
public final class Blog {

    private final String title = "DevPub";
    private final String subTitle = "Рассказы разработчиков";
    private final String phone = "+7 903 666-44-55";
    private final String email = "mail@mail.ru";
    private final String copyright = "Дмитрий Сергеев";
    private final String copyrightFrom = "2005";

    @Bean
    @Scope("singleton")
    public Blog blog() {
       return new Blog();
    }
}
