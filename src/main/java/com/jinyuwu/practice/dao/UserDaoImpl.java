package com.jinyuwu.practice.dao;

import javax.persistence.EntityManager;

import com.jinyuwu.practice.entity.User;
import com.jinyuwu.practice.user.CrmUser;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    public User findByUserName(String theUserName) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<User> theQuery = currentSession.createQuery("from User where userName=:uName", User.class);
        theQuery.setParameter("uName", theUserName);
        User theUser = null;
        try {
            theUser = theQuery.getSingleResult();
        } catch (Exception e) {
            theUser = null;
        }
        return theUser;
    }

    @Override
    public User findByUserId(long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<User> theQuery = currentSession.createQuery("from User where id=:uId", User.class);
        theQuery.setParameter("uId", id);
        User theUser = null;
        try {
            theUser = theQuery.getSingleResult();
        } catch (Exception e) {
            theUser = null;
        }
        return theUser;
    }

    @Override
    public void update(User theUser) {
        Session currentSession = entityManager.unwrap(Session.class);
        String updateQuery = "update User set firstName=:firstName, lastName=:lastName, email=:email," +
                " introduction=:introduction, classes=:classes, goals=:goals, funStuff=:funStuff, " +
                "otherStuff=:otherStuff," +
                " links=:links where id=:uId";
        Query theQuery = currentSession.createQuery(updateQuery);
        theQuery.setParameter("uId", theUser.getId());
        theQuery.setParameter("firstName", theUser.getFirstName());
        theQuery.setParameter("lastName", theUser.getLastName());
        theQuery.setParameter("email", theUser.getEmail());
        theQuery.setParameter("introduction", theUser.getIntroduction());
        theQuery.setParameter("classes", theUser.getClasses());
        theQuery.setParameter("goals", theUser.getGoals());
        theQuery.setParameter("funStuff", theUser.getFunStuff());
        theQuery.setParameter("otherStuff", theUser.getOtherStuff());
        theQuery.setParameter("links", theUser.getLinks());
        theQuery.executeUpdate();
    }

    @Override
    public void save(User theUser) {
        // get current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // create the user ... finally LOL
        currentSession.saveOrUpdate(theUser);
    }
}
