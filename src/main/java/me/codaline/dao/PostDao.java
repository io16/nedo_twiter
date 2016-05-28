package me.codaline.dao;

import me.codaline.model.Post;
import me.codaline.model.User;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class PostDao {
    @Autowired
    SessionFactory sessionFactory;

    public void save(Post post) {
        sessionFactory.getCurrentSession().save(post);
    }

    public void delete(Post post) {
        sessionFactory.getCurrentSession().delete(post);
    }

    public void update(Post post) {
        sessionFactory.getCurrentSession().saveOrUpdate(post);
    }

    public List<Post> getPosts(String username) {
//        return sessionFactory.getCurrentSession().createCriteria(Post.class).addOrder(Property.forName("id").desc()).list();
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Post.class);
        criteria.add(Restrictions.eq("username",username));
        return criteria.addOrder(Property.forName("id").desc()).list();

    }
    public Post getPost(int id){
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Post.class);
        criteria.add(Restrictions.eq("id", id));
//        Query query = sessionFactory.getCurrentSession().createQuery("select from Users as u where u.email =:email");
//        query.setParameter("email", email);
        return (Post) criteria.uniqueResult();
    }
}
