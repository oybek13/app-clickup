package uz.ok.service.impl;

import uz.ok.dto.request.WorkspaceRoleDto;
import uz.ok.dto.response.MemberDto;
import uz.ok.dto.request.WorkspaceDto;
import uz.ok.dto.response.ApiResponse;
import uz.ok.entity.User;
import uz.ok.entity.Workspace;
import uz.ok.entity.WorkspaceUser;

import java.util.List;
import java.util.UUID;

public interface WorkspaceService {

    ApiResponse addWorkspace(WorkspaceDto workspaceDto, User user);

    ApiResponse editWorkspace(WorkspaceDto workspaceDto);

    ApiResponse changeOwnerWorkspace(Long id, UUID ownerId);

    ApiResponse deleteWorkspace(Long id);

    ApiResponse addOrEditOrRemoveWorkspace(Long id, MemberDto memberDto);

    ApiResponse joinToWorkspace(Long id, User user);

    List<MemberDto> getMemberAndGuest(Long id);

    List<WorkspaceDto> getMyWorkspaces(User user);


    ApiResponse addOrRemovePermissionToRole(WorkspaceRoleDto workspaceRoleDto);

}