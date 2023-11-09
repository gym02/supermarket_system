package com.example.supermarket.entity.vo;

import com.example.supermarket.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RolePermissionVO {
	private Role role;
	private int[] pids;
}
