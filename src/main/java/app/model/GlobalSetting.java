package app.model;

import app.model.enums.GlobalSettingName;
import app.model.enums.GlobalSettingValue;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "global_settings")
@Data
public class GlobalSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GlobalSettingName name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GlobalSettingValue value;

    public GlobalSetting() {

    }

    public GlobalSetting(String code, GlobalSettingName name, GlobalSettingValue value) {
        this.code = code;
        this.name = name;
        this.value = value;
    }
}
