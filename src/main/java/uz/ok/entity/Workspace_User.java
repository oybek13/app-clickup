package uz.ok.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import uz.ok.entity.template.AbsUUIDEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Workspace_User extends AbsUUIDEntity {

    @OneToOne
    private Workspace workspace;

    @OneToOne
    private User user;

    @OneToOne
    private Workspace_Role workspace_role;

    @CreatedDate
    private Date date_invited;

    private Date date_joined;

}
