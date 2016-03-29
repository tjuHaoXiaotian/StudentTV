package cn.edu.tju.scs.service.impl;

import cn.edu.tju.scs.dao.CommentDao;
import cn.edu.tju.scs.domain.Comment;
import cn.edu.tju.scs.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Comment Service Impl
 * Created by jack on 2016/3/28.
 */

@Service(value = "commentService")
public class CommentServiceImpl implements CommentService{

    @Autowired
    CommentDao commentDao;


    @Override
    public List<Comment> getAll(int videoId) {
        return commentDao.getListByHQL("from Comment where ref_video_Id = ?",videoId);
    }

    @Override
    public void add(Comment comment) {
        commentDao.save(comment);
    }

    @Override
    public void removeComment(int commentId) {
        commentDao.deleteById(commentId);
    }
}
