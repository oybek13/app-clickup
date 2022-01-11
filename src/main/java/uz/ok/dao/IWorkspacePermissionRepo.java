package uz.ok.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ok.entity.WorkspacePermission;

import java.util.UUID;

@Repository
public interface IWorkspacePermissionRepo extends JpaRepository<WorkspacePermission, UUID> {
}
