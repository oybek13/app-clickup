package uz.ok.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.ok.entity.enums.WorkspaceRoleName;
import uz.ok.entity.template.AbsUUIDEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class WorkspaceRole extends AbsUUIDEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Workspace workspace;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private WorkspaceRoleName extendsRole;

}
