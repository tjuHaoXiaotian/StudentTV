package cn.edu.tju.scs.service.impl;

import cn.edu.tju.scs.dao.AdminDao;
import cn.edu.tju.scs.domain.Admin;
import cn.edu.tju.scs.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 管理员用户操作 impl
 * Created by jack on 2016/3/16.
 */

@Service(value = "adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminDao adminDao;

    @Override
    public void saveAdmin(Admin admin) {
       adminDao.save(admin);
    }

    @Override
    public void updateAdmin(Admin admin) {
        adminDao.update(admin);
    }

    @Override
    public void deleteAdmin(Integer id) {
        adminDao.deleteById(id);
    }

    @Override
    public boolean hasMatchAdmin(String adminName, String password) {
        String query = "select count(*) from Admin where adminName = ? and password = ?";
        return adminDao.countByHql(query,adminName,password) == 1;
    }
}
