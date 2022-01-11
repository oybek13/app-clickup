package uz.ok.entity.enums;

import java.util.Arrays;
import java.util.List;

public enum WorkspacePermissionName {
    CAN_ADD_MEMBER("Add members",
            "Gives the user the permission to add members to the Workspace",
            Arrays.asList(WorkspaceRoleName.ROLE_OWNER, WorkspaceRoleName.ROLE_ADMIN)),

    CAN_REMOVE_MEMBER("Remove members",
            "Gives the user the permission to remove members from the Workspace",
            Arrays.asList(WorkspaceRoleName.ROLE_OWNER, WorkspaceRoleName.ROLE_ADMIN)),

    CAN_EDIT_WORKSPACE("CAN_EDIT_WORKSPACE",
                           "Gives the user the permission to edit the Workspace",
                   Arrays.asList(WorkspaceRoleName.ROLE_OWNER, WorkspaceRoleName.ROLE_ADMIN)),

    CAN_ADD_GUEST("CAN_ADD_GUEST",
            "Gives the user the permission to add a guest",
            Arrays.asList(WorkspaceRoleName.ROLE_OWNER, WorkspaceRoleName.ROLE_ADMIN)),

    CAN_SEE_TIME_ESTIMATED("CAN_SEE_TIME_ESTIMATED",
            "Gives the user the permission to see the time estimated",
            Arrays.asList(WorkspaceRoleName.ROLE_OWNER, WorkspaceRoleName.ROLE_ADMIN)),

    CAN_SEE_TIME_SPENT("CAN_SEE_TIME_SPENT",
            "Gives the user the permission to see time-spent",
            Arrays.asList(WorkspaceRoleName.ROLE_OWNER, WorkspaceRoleName.ROLE_ADMIN)),

    CAN_CREATE_SPACES("CAN_CREATE_SPACES",
            "Gives the user the permission to create the Spaces",
            Arrays.asList(WorkspaceRoleName.ROLE_OWNER, WorkspaceRoleName.ROLE_ADMIN)),

    CAN_CREATE_FOLDER("CAN_CREATE_FOLDER",
            "Gives the user the permission to create the Folder",
            Arrays.asList(WorkspaceRoleName.ROLE_OWNER, WorkspaceRoleName.ROLE_ADMIN)),

    CAN_CREATE_LISTS("CAN_CREATE_LISTS",
            "Gives the user the permission to create lists",
            Arrays.asList(WorkspaceRoleName.ROLE_OWNER, WorkspaceRoleName.ROLE_ADMIN)),

    CAN_DELETE_COMMENTS("CAN_DELETE_COMMENTS",
            "Gives the user the permission to delete comments",
            Arrays.asList(WorkspaceRoleName.ROLE_OWNER, WorkspaceRoleName.ROLE_ADMIN)),

    CAN_DELETE_ITEMS("CAN_DELETE_ITEMS",
            "Gives the user the permission to delete items",
            Arrays.asList(WorkspaceRoleName.ROLE_OWNER, WorkspaceRoleName.ROLE_ADMIN)),

    CAN_EDIT_DESCRIPTION("CAN_EDIT_DESCRIPTION",
            "Gives the user the permission to edit description",
            Arrays.asList(WorkspaceRoleName.ROLE_OWNER, WorkspaceRoleName.ROLE_ADMIN)),

    CAN_ADD_LIST_STATUSES("CAN_ADD_LIST_STATUSES",
            "Gives the user the permission to add list statuses",
            Arrays.asList(WorkspaceRoleName.ROLE_OWNER, WorkspaceRoleName.ROLE_ADMIN)),

    CAN_EDIT_LIST_STATUSES("CAN_EDIT_LIST_STATUSES",
            "Gives the user the permission to edit list statuses",
            Arrays.asList(WorkspaceRoleName.ROLE_OWNER, WorkspaceRoleName.ROLE_ADMIN)),

    CAN_EDIT_TEAM("CAN_EDIT_TEAM",
            "Gives the user the permission to edit team",
            Arrays.asList(WorkspaceRoleName.ROLE_OWNER, WorkspaceRoleName.ROLE_ADMIN)),

    CAN_EXPORT_TASKS("CAN_EXPORT_TASKS",
            "Gives the user the permission to export tasks",
            Arrays.asList(WorkspaceRoleName.ROLE_OWNER, WorkspaceRoleName.ROLE_ADMIN)),

    CAN_EDIT_TEAM_OWNER("CAN_EDIT_TEAM_OWNER",
            "Gives the user the permission to edit team owner",
            Arrays.asList(WorkspaceRoleName.ROLE_OWNER, WorkspaceRoleName.ROLE_ADMIN)),

    CAN_MANAGE_TAGS("CAN_MANAGE_TAGS",
            "Gives the user the permission to manage tags",
            Arrays.asList(WorkspaceRoleName.ROLE_OWNER, WorkspaceRoleName.ROLE_ADMIN, WorkspaceRoleName.ROLE_MEMBER)),

    CAN_SHARE("CAN_SHARE",
            "Gives the user the permission to share",
            Arrays.asList(WorkspaceRoleName.ROLE_OWNER, WorkspaceRoleName.ROLE_ADMIN, WorkspaceRoleName.ROLE_MEMBER)),

    CAN_MANAGE_STATUSES("CAN_MANAGE_STATUSES",
            "Gives the user the permission to manage statuses",
            Arrays.asList(WorkspaceRoleName.ROLE_OWNER, WorkspaceRoleName.ROLE_ADMIN)),

    CAN_SEE_TEAM_MEMBERS("CAN_SEE_TEAM_MEMBERS",
            "Gives the user the permission to see team members",
            Arrays.asList(WorkspaceRoleName.ROLE_OWNER, WorkspaceRoleName.ROLE_ADMIN)),

    CAN_ADD_ROLE("CAN_ADD_ROLE",
            "Gives the user the permission to add role",
            Arrays.asList(WorkspaceRoleName.ROLE_OWNER, WorkspaceRoleName.ROLE_ADMIN)),

    CAN_CHANGE_PERMISSION("CAN_CHANGE_PERMISSION",
            "Gives the user the permission to change permission",
            Arrays.asList(WorkspaceRoleName.ROLE_OWNER, WorkspaceRoleName.ROLE_ADMIN));

    private String name;
    private String description;
    private List<WorkspaceRoleName> workspaceRoleNames;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<WorkspaceRoleName> getWorkspaceRoleNames() {
        return workspaceRoleNames;
    }

    WorkspacePermissionName(String name, String description, List<WorkspaceRoleName> workspaceRoleNames) {
        this.name = name;
        this.description = description;
        this.workspaceRoleNames = workspaceRoleNames;
    }

}
