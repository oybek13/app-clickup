package uz.ok.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.ok.entity.template.AbsUUIDEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Workspace extends AbsUUIDEntity {

    private String name;

    private String color;

    @ManyToMany
    private List<User> owner_id;

    private String initial_letter;

    @OneToOne
    private Attachment avatar;
}
