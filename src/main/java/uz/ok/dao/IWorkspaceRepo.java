package uz.ok.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ok.entity.Workspace;
import uz.ok.entity.WorkspaceUser;

import java.util.List;
import java.util.UUID;

@Repository
public interface IWorkspaceRepo extends JpaRepository<Workspace, Long> {
    boolean existsByOwnerIdAndName(UUID owner_id, String name);
}
