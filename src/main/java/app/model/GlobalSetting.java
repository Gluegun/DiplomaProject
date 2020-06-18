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

    private String code;

    @Enumerated(EnumType.STRING)
    private GlobalSettingName name;

    @Enumerated(EnumType.STRING)
    private GlobalSettingValue value;

    public GlobalSetting() {

    }

    public GlobalSetting(String code, GlobalSettingName name, GlobalSettingValue value) {
        this.code = code;
        this.name = name;
        this.value = value;
    }
}
