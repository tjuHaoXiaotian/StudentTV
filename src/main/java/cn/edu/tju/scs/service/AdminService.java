package cn.edu.tju.scs.service;

import cn.edu.tju.scs.domain.Admin;

/**
 * 管理员用户操作
 * Created by jack on 2016/3/16.
 */
public interface AdminService {
    public void saveAdmin(Admin admin);
    public void updateAdmin(Admin admin);
    public void deleteAdmin(Integer id);
    public boolean hasMatchAdmin(String adminName,String password);
}
