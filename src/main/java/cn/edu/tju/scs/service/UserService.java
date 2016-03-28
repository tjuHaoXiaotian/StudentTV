package cn.edu.tju.scs.service;

import cn.edu.tju.scs.domain.Collection;
import cn.edu.tju.scs.domain.User;

import java.util.List;

/**
 * User Service Interface
 * Created by jack on 2016/3/28.
 */
public interface UserService {

    public int collect(int userId,int videoId);

    public User getUser(int userId);

    public void deCollect(int collectionId);

    public List<Collection> getAllCollection(int userId);
}
