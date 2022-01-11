package uz.ok.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ok.entity.WorkspaceRole;

import java.util.UUID;

@Repository
public interface IWorkspaceRoleRepo extends JpaRepository<WorkspaceRole, UUID> {
}
