package uz.ok.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.ok.entity.enums.WorkspacePermissionName;
import uz.ok.entity.template.AbsUUIDEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class WorkspacePermission extends AbsUUIDEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private WorkspaceRole workspaceRole; // O'rinbosar

    @Enumerated(EnumType.STRING)
    private WorkspacePermissionName permission; // add member, remove member
}
