package app.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "captcha_codes")
@NoArgsConstructor
public class CaptchaCode extends AbstractEntity {

    @Column(name = "time", nullable = false)
    private LocalDateTime time;

    @Column(name = "code", nullable = false, columnDefinition = "tinytext")
    private String code;

    @Column(name = "secret_code", nullable = false, columnDefinition = "tinytext")
    private String secretCode;

}
