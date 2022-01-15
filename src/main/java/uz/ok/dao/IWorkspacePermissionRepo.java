package uz.ok.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ok.entity.WorkspacePermission;
import uz.ok.entity.enums.WorkspacePermissionName;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IWorkspacePermissionRepo extends JpaRepository<WorkspacePermission, UUID> {
    Optional<WorkspacePermission> findByWorkspaceRoleIdAndPermission(UUID workspaceRole_id, WorkspacePermissionName permission);
}
