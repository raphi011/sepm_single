package sepm.ws14.e0828630.dao;

public class DAOException extends Exception {

    public DAOException() { super(); }

    public DAOException(String text) { super(text); }

    public DAOException(Exception e) { super(e); }

    public DAOException(String text, Exception e) { super(text,e); }
}
