package uz.ok.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.ok.entity.enums.Permission;
import uz.ok.entity.template.AbsUUIDEntity;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Workspace_Permission extends AbsUUIDEntity {

    @ManyToOne
    private Workspace_Role workspace_role;

    @Enumerated
    private Permission permission;
}
