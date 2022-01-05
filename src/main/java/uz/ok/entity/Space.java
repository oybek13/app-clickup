package uz.ok.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.ok.entity.template.AbsUUIDEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Space extends AbsUUIDEntity {

    private String name;

    private String color;

    @ManyToOne
    private Workspace workspace;

    private String initial_letter;

    @OneToOne
    private Icon icon;

    @OneToOne
    private Attachment avatar;

    private boolean access_type;

    @ManyToMany
    private List<User> user;

    @ManyToMany
    private List<Click_Apps> clickAppsList;

    @ManyToMany
    private List<View> viewList;

}
