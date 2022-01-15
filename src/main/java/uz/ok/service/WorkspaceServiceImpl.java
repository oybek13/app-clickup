package uz.ok.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import uz.ok.dao.*;
import uz.ok.dto.request.WorkspaceRoleDto;
import uz.ok.dto.response.MemberDto;
import uz.ok.dto.request.WorkspaceDto;
import uz.ok.dto.response.ApiResponse;
import uz.ok.entity.*;
import uz.ok.entity.enums.AddType;
import uz.ok.entity.enums.WorkspacePermissionName;
import uz.ok.entity.enums.WorkspaceRoleName;
import uz.ok.service.impl.WorkspaceService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkspaceServiceImpl implements WorkspaceService {

    private final IWorkspaceRepo iWorkspaceRepo;
    private final IAttachmentRepo iAttachmentRepo;
    private final IWorkspaceUserRepo iWorkspaceUserRepo;
    private final IWorkspaceRoleRepo iWorkspaceRoleRepo;
    private final IWorkspacePermissionRepo iWorkspacePermissionRepo;
    private final IUserRepo iUserRepo;

    @Override
    public ApiResponse addWorkspace(WorkspaceDto workspaceDto, User user) {

        /*TODO WORKSPACE CREATED*/
        if (iWorkspaceRepo.existsByOwnerIdAndName(user.getId(), workspaceDto.getName()))
            return new ApiResponse("You got a workspace like that name!", false);
        Workspace workspace = new Workspace(
                workspaceDto.getName(),
                workspaceDto.getColor(),
                user,
                workspaceDto.getAvatarId() == null ? null :
                        iAttachmentRepo.findById(workspaceDto.getAvatarId())
                                .orElseThrow(() -> new ResourceNotFoundException("attachment"))
        );
        iWorkspaceRepo.save(workspace);

        /*TODO WORKSPACE ROLE CREATED*/
        WorkspaceRole ownerRole = iWorkspaceRoleRepo.save(new WorkspaceRole(
                workspace,
                WorkspaceRoleName.ROLE_OWNER.name(),
                null
        ));
        WorkspaceRole adminRole = iWorkspaceRoleRepo.save(new WorkspaceRole(
                workspace,
                WorkspaceRoleName.ROLE_ADMIN.name(),
                null
        ));
        WorkspaceRole memberRole = iWorkspaceRoleRepo.save(new WorkspaceRole(
                workspace,
                WorkspaceRoleName.ROLE_MEMBER.name(),
                null
        ));
        WorkspaceRole guestRole = iWorkspaceRoleRepo.save(new WorkspaceRole(
                workspace,
                WorkspaceRoleName.ROLE_GUEST.name(),
                null
        ));


        /*TODO WORKSPACE PERMISSION FOR ROLE CREATED*/
        WorkspacePermissionName[] workspacePermissionNames = WorkspacePermissionName.values();
        List<WorkspacePermission> workspacePermissions = new ArrayList<>();
        for (WorkspacePermissionName workspacePermissionName : workspacePermissionNames) {
            WorkspacePermission workspacePermission = new WorkspacePermission(
                    ownerRole,
                    workspacePermissionName);
            workspacePermissions.add(workspacePermission);
            if (workspacePermissionName.getWorkspaceRoleNames().contains(WorkspaceRoleName.ROLE_ADMIN)) {
                workspacePermissions.add(new WorkspacePermission(
                        adminRole,
                        workspacePermissionName
                ));
                if (workspacePermissionName.getWorkspaceRoleNames().contains(WorkspaceRoleName.ROLE_MEMBER)) {
                    workspacePermissions.add(new WorkspacePermission(
                            memberRole,
                            workspacePermissionName
                    ));
                    if (workspacePermissionName.getWorkspaceRoleNames().contains(WorkspaceRoleName.ROLE_GUEST)) {
                        workspacePermissions.add(new WorkspacePermission(
                                guestRole,
                                workspacePermissionName
                        ));
                    }

                }
                iWorkspacePermissionRepo.saveAll(workspacePermissions);


                /*TODO WORKSPACE USER CREATED*/
                iWorkspaceUserRepo.save(new WorkspaceUser(
                        workspace,
                        user,
                        ownerRole,
                        new Timestamp(System.currentTimeMillis()),
                        new Timestamp(System.currentTimeMillis())
                ));
                return new ApiResponse("Ishxona saqlandi!", true);
            }
        }
        return null;
    }

    @Override
    public ApiResponse editWorkspace(WorkspaceDto workspaceDto) {
        return null;
    }

    @Override
    public ApiResponse changeOwnerWorkspace(Long id, UUID ownerId) {
        return null;
    }

    @Override
    public ApiResponse deleteWorkspace(Long id) {
        try {
            iWorkspaceRepo.deleteById(id);
            return new ApiResponse("Successfully deleted!", true);
        } catch (Exception e) {
            return new ApiResponse("Error while deleting!", false);
        }
    }


    @Override
    public ApiResponse addOrEditOrRemoveWorkspace(Long id, MemberDto memberDto) {
        if (memberDto.getAddType() == AddType.ADD) {
            WorkspaceUser workspaceUser = new WorkspaceUser(
                    iWorkspaceRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("id")),
                    iUserRepo.findById(memberDto.getId()).orElseThrow(() -> new ResourceNotFoundException("id")),
                    iWorkspaceRoleRepo.findById(memberDto.getRoleId()).orElseThrow(() -> new ResourceNotFoundException("id")),
                    new Timestamp(System.currentTimeMillis()),
                    null
            );
            iWorkspaceUserRepo.save(workspaceUser);
        } else if (memberDto.getAddType() == AddType.EDIT) {
            WorkspaceUser workspaceUser =
                    iWorkspaceUserRepo.findByWorkspaceIdAndUserId(id, memberDto.getId()).orElseGet(WorkspaceUser::new);
                    workspaceUser.setWorkspaceRole(iWorkspaceRoleRepo.findById(memberDto.getRoleId()).orElseThrow(() -> new ResourceNotFoundException("id")));
                    iWorkspaceUserRepo.save(workspaceUser);
        } else if (memberDto.getAddType() == AddType.REMOVE) {
            iWorkspaceUserRepo.deleteByWorkspaceIdAndUserId(id, memberDto.getId());
        }

      return new ApiResponse("Successful", true);
    }

    @Override
    public ApiResponse joinToWorkspace(Long id, User user) {
        Optional<WorkspaceUser> optionalWorkspaceUser = iWorkspaceUserRepo.findByWorkspaceIdAndUserId(id, user.getId());
        if (optionalWorkspaceUser.isPresent()){
            WorkspaceUser workspaceUser = optionalWorkspaceUser.get();
            workspaceUser.setDateJoined(new Timestamp(System.currentTimeMillis()));
            iWorkspaceUserRepo.save(workspaceUser);
            return new ApiResponse("Success", true);
        }
        return new ApiResponse("Error", false);
    }

    @Override
    public List<MemberDto> getMemberAndGuest(Long id) {
        List<WorkspaceUser> workspaceUsers = iWorkspaceUserRepo.findAllByWorkspaceId(id);
        /* List<MemberDto> members = new ArrayList<>();
        for (WorkspaceUser workspaceUser : workspaceUsers) {
            MemberDto memberDto = mapWorkspaceUserToMemberDto(workspaceUser);
            members.add(memberDto);
        }
        return members; */
        return workspaceUsers.stream().map(this::mapWorkspaceUserToMemberDto).collect(Collectors.toList());
    }

    @Override
    public List<WorkspaceDto> getMyWorkspaces(User user) {
        List<WorkspaceUser> workspaceUsers = iWorkspaceUserRepo.findAllByUserId(user.getId());
        return workspaceUsers.stream().map(workspaceUser
                -> mapWorkspaceUserToWorkspaceDto(workspaceUser.getWorkspace())).collect(Collectors.toList());
    }

    @Override
    public ApiResponse addOrRemovePermissionToRole(WorkspaceRoleDto workspaceRoleDto) {
        WorkspaceRole workspaceRole = iWorkspaceRoleRepo.findById(workspaceRoleDto.getId()).orElseThrow(() -> new ResourceNotFoundException("workspaceRole"));
        Optional<WorkspacePermission> optionalWorkspacePermission = iWorkspacePermissionRepo.findByWorkspaceRoleIdAndPermission(workspaceRole.getId(), workspaceRoleDto.getPermissionName());
        if (workspaceRoleDto.getAddType().equals(AddType.ADD)){
            if (optionalWorkspacePermission.isPresent())
                return new ApiResponse("Already exists, no need to add again!", false);
            WorkspacePermission workspacePermission = new WorkspacePermission(workspaceRole, workspaceRoleDto.getPermissionName());
            iWorkspacePermissionRepo.save(workspacePermission);
            return new ApiResponse("Successfully added!", true);
        }else if (workspaceRoleDto.getAddType().equals(AddType.REMOVE)){
            if (optionalWorkspacePermission.isPresent()){
                iWorkspacePermissionRepo.delete(optionalWorkspacePermission.get());
                return new ApiResponse("Successfully deleted!", true);
            }
            return new ApiResponse("Sorry this object not found!", false);
        }
        return new ApiResponse("This request not found!", false);
    }

    public WorkspaceDto mapWorkspaceUserToWorkspaceDto(Workspace workspace){
        WorkspaceDto workspaceDto = new WorkspaceDto();
        workspaceDto.setId(workspace.getId());
        workspaceDto.setName(workspace.getName());
        workspaceDto.setInitialLetter(workspace.getInitialLetter());
        workspaceDto.setAvatarId(workspace.getAvatar() == null ? null : workspace.getAvatar().getId());
        workspaceDto.setColor(workspace.getColor());
        return workspaceDto;
    }


    public MemberDto mapWorkspaceUserToMemberDto(WorkspaceUser workspaceUser){
        MemberDto memberDto = new MemberDto();
        memberDto.setId(workspaceUser.getUser().getId());
        memberDto.setFullName(workspaceUser.getUser().getFullName());
        memberDto.setEmail(workspaceUser.getUser().getEmail());
        memberDto.setRoleName(workspaceUser.getWorkspaceRole().getName());
        memberDto.setLastActive(workspaceUser.getUser().getLastActiveTime());
        return memberDto;
    }
}
