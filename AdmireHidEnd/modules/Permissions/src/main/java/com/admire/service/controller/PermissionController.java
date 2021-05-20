package com.admire.service.controller;



import com.admire.service.entity.Permission;
import com.admire.service.service.PermissionService;
import com.admire.utils.utils.ReturnValue;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 权限 菜单管理
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
@RestController
@RequestMapping("/admin/acl/permission")
//@CrossOrigin
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    //获取全部菜单
    @ApiOperation(value = "查询所有菜单")
    @GetMapping
    public ReturnValue indexAllPermission() {
        List<Permission> list =  permissionService.queryAllMenu();
        return ReturnValue.ok().data("children",list);
    }

    @ApiOperation(value = "给角色分配权限")
    @PostMapping("/doAssign")
    public ReturnValue doAssign(String roleId, String[] permissionId) {
        permissionService.saveRolePermissionRealtionShipGuli(roleId,permissionId);
        return ReturnValue.ok();
    }

    @ApiOperation(value = "根据角色获取菜单")
    @GetMapping("toAssign/{roleId}")
    public ReturnValue toAssign(@PathVariable String roleId) {
        List<Permission> list = permissionService.selectAllMenu(roleId);
        return ReturnValue.ok().data("children", list);
    }

}

