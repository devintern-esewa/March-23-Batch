package springCore.beanLifeCycle;

import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        StudentDao dao = new StudentDao();
        dao.selectAllRows(); // will not work. only for testing purpose
    }
}
