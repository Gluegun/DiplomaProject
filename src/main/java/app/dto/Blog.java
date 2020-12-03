package app.dto;

import lombok.Data;

@Data
public class Blog {

    private final String title = "DevPub";
    private final String subtitle = "Рассказы разработчиков";
    private final String phone = "+79058879544";
    private final String email = "kirill.yudintsev@gmail.com";
    private final String copyright = "Кирилл Юдинцев";
    private final String copyrightFrom = "2020";

}
