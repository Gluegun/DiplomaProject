package app.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "global_settings")
public class GlobalSetting extends AbstractEntity {

    @Column (name = "code", nullable = false)
    private String code;

    @Column (name = "name", nullable = false)
    private String name;

    @Column (name = "value", nullable = false)
    private String value;

}
