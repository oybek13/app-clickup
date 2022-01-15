package uz.ok.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.ok.entity.enums.AddType;
import uz.ok.entity.enums.WorkspacePermissionName;
import uz.ok.entity.enums.WorkspaceRoleName;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkspaceRoleDto {

    private UUID id;

    private String name;

    private WorkspaceRoleName extendsRole;

    private WorkspacePermissionName permissionName;

    private AddType addType;
}
