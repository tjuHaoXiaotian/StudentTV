package cn.edu.tju.scs.service.impl;

import cn.edu.tju.scs.dao.CollectionDao;
import cn.edu.tju.scs.dao.UserDao;
import cn.edu.tju.scs.domain.Collection;
import cn.edu.tju.scs.domain.User;
import cn.edu.tju.scs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User Service impl
 * Created by jack on 2016/3/28.
 */

@Service(value = "userService")
public class UserServiceImpl implements UserService{


    @Autowired
    CollectionDao collectionDao;

    @Autowired
    UserDao userDao;

    @Override
    public int collect(int userId, int videoId) {
        Collection collection = new Collection(userId,videoId);
        collectionDao.save(collection);
        return collection.getCollectionId();
    }

    @Override
    public User getUser(int userId) {
        return userDao.get(userId);
    }

    @Override
    public void deCollect(int collectionId) {
        collectionDao.deleteById(collectionId);
    }

    @Override
    public List<Collection> getAllCollection(int userId) {
        return collectionDao.getListByHQL("from Collection where userId = ?", userId);
    }
}
