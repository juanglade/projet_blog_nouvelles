package dao;

public class DAOFactory {
    private static final DAOPerson daopers = new DAOPerson();
    private static final DAOStory daostory = new DAOStory();
    private static final DAOComment daocomment = new DAOComment();
    private static final DAOVote daovote = new DAOVote();

    private DAOFactory() {
    }

    public static DAOPerson getDAOPerson() {
        return daopers;
    }

    public static DAOStory getDAOStory() {
        return daostory;
    }

    public static DAOComment getDAOComment() {
        return daocomment;
    }

    public static DAOVote getDAOVote() {
        return daovote;
    }

}
