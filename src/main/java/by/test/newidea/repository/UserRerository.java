package by.test.newidea.repository;

import by.test.newidea.domain.User;
import by.test.newidea.exception.NoSuchEntityException;
import by.test.newidea.configuration.DatabaseProperties;
import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;

import static by.test.newidea.repository.UserTableColumns.*;
import static by.test.newidea.util.UUIDGenerator.generateUUID;
//import static by.test.newidea.util.DatabasePropertiesReader.DATABASE_LOGIN;
//import static by.test.newidea.util.DatabasePropertiesReader.DATABASE_NAME;
//import static by.test.newidea.util.DatabasePropertiesReader.DATABASE_PASSWORD;
//import static by.test.newidea.util.DatabasePropertiesReader.DATABASE_PORT;
//import static by.test.newidea.util.DatabasePropertiesReader.DATABASE_URL;
//import static by.test.newidea.util.DatabasePropertiesReader.POSTRGES_DRIVER_NAME;

@Repository
//@Primary
@AllArgsConstructor
public class UserRerository implements UserRepositoryInterface {

    private static final Logger log = Logger.getLogger(UserRerository.class);
    private final DatabaseProperties databaseProperties;

    @Override
    public User findById(Long id) {
        final String findByIdQuery = "select * from carshop.users where id = " + id;

        Connection connection;
        Statement statement;
        ResultSet rs;

        try {
            connection = getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(findByIdQuery);
            boolean hasRow = rs.next();
            if (hasRow) {
                return userRowMapping(rs);
            } else {
                throw new NoSuchEntityException("Entity User with id " + id + " does not exist", 404, generateUUID());            }
        } catch (SQLException e) {
            log.error("DB connection process issues", e);
            throw new RuntimeException("SQL Issues!");
        }

    }

    @Override
    public Optional<User> findOne(Long id) {
        return Optional.of(findById(id));
    }

    @Override
    public List<User> findAll(){
        return findAll(DEFAULT_FIND_ALL_LIMIT, DEFAULT_FIND_ALL_OFFSET);
    }
    private Connection getConnection() throws SQLException {
        try {
            String driver = databaseProperties.getDriverName();

            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            log.error("JDBC Driver Cannot be loaded!", e);
            throw new RuntimeException("JDBC Driver Cannot be loaded!");
        }

        String url = databaseProperties.getUrl();
        String login = databaseProperties.getLogin();
        String password = databaseProperties.getPassword();

        return DriverManager.getConnection(url, login, password);
    }

    private User userRowMapping(ResultSet rs) throws SQLException {
        User user = new User();

        user.setId(rs.getLong(ID));
        user.setUserName(rs.getString(NAME));
        user.setSurname(rs.getString(SURNAME));
        user.setBirth(rs.getTimestamp(BIRTH_DATE));
        user.setIsDeleted(rs.getBoolean(MYDELETED));
        user.setCreateDate(rs.getTimestamp(CREATED));
        user.setModificationDate(rs.getTimestamp(CHANGED));

        return user;

/*        return User.builder()
                .id(rs.getLong(ID))
                .userName(rs.getString(NAME))
                .surname(rs.getString(SURNAME))
                .birth(rs.getTimestamp(BIRTH_DATE))
                .creationDate(rs.getTimestamp(CREATED))
                .modificationDate(rs.getTimestamp(CHANGED))
                .weight(rs.getDouble(WEIGHT))
                .build();*/
    }


    @Override
    public User create(User object) {

        final String insertQuery =
                "insert into carshop.users (username, surname, birth, is_deleted, creation_date, modification_date) " +
                        " values (?, ?, ?, ?, ?, ?)";

        Connection connection;
        PreparedStatement statement;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(insertQuery);

            statement.setString(1, object.getUserName());
            statement.setString(2, object.getSurname());
            statement.setTimestamp(3, object.getBirth());
            statement.setBoolean(4, object.getIsDeleted());
            statement.setTimestamp(5, object.getCreateDate());
            statement.setTimestamp(6, object.getModificationDate());


            //executeUpdate - for INSERT, UPDATE, DELETE
            statement.executeUpdate();
            //For select
            //statement.executeQuery();

            /*Get users last insert id for finding new object in DB*/
            ResultSet resultSet = connection.prepareStatement("SELECT currval('carshop.users_id_seq') as last_id").executeQuery();

            resultSet.next();
            long userLastInsertId = resultSet.getLong("last_id");


            return findById(userLastInsertId);
        } catch (SQLException e) {
            log.error("DB connection process issues", e);
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public User update(User object) {

        final String updateQuery =
                "update carshop.users " +
                        "set " +
                        "username = ?, surname = ?, birth = ?, is_deleted = ?, creation_date = ?, modification_date = ? " +
                        " where id = ?";

        Connection connection;
        PreparedStatement statement;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(updateQuery);

            statement.setString(1, object.getUserName());
            statement.setString(2, object.getSurname());
            statement.setTimestamp(3, object.getBirth());
            statement.setBoolean(4, object.getIsDeleted());
            statement.setTimestamp(5, object.getCreateDate());
            statement.setTimestamp(6, object.getModificationDate());
            statement.setLong(7, object.getId());

            statement.executeUpdate();

            return findById(object.getId());
        } catch (SQLException e) {
            log.error("DB connection process issues", e);
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public Long delete(Long id) {

        final String deleteQuery =
                "delete from carshop.users where id = ?";

        Connection connection;
        PreparedStatement statement;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(deleteQuery);
            statement.setLong(1, id);
            statement.executeUpdate();

            return id;
        } catch (SQLException e) {
            log.error("DB connection process issues", e);
 //           System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }


    public List<User> findAll(int limit, int offset){

        final String findAllQuery = "select * from carshop.users order by id limit " + limit + " offset " + offset;

        List<User> result = new ArrayList<>();

        Connection connection;
        Statement statement;
        ResultSet rs;

        try {
            connection = getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(findAllQuery);

            while (rs.next()) {
                result.add(userRowMapping(rs));
            }

            return result;
        } catch (SQLException e) {
//            System.err.println(e.getMessage());
            log.error("DB connection process issues", e);
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public Map<String, Object> getUserStats() {
        final String callFunction =
                "select public.get_users_stats_average_weight(?)";

        Connection connection;
        PreparedStatement statement;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(callFunction);
            statement.setBoolean(1, false);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            double functiionCall = resultSet.getInt(1);

            return Collections.singletonMap("avg", functiionCall);
        } catch (SQLException e) {
            log.error("DB connection process issues", e);
//            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public List<User> findNameSurname(String search) {

        final String findQuery =
                "select * from carshop.users where (username like '%"
                        + search + "%' or surname like '%"+ search+"%')";

        List<User> resultsearch = new ArrayList<>();

        Connection connection;
        Statement statement;
        ResultSet rs;

        try {
            connection = getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(findQuery);

            while (rs.next()) {
                resultsearch.add(userRowMapping(rs));
            }
            return resultsearch;

        } catch (SQLException e) {
            log.error("DB connection process issues", e);
//            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public List<User> findNameSurnameParam(String search) {

        final String findQuery =
                "select * from carshop.users where (username like '%?%' or surname like '%?%')";

        List<User> resultsearch = new ArrayList<>();

        Connection connection;
        PreparedStatement statement;
        ResultSet rs;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(findQuery);

            statement.setString(1, search);
            statement.setString(2, search);
            rs = statement.executeQuery(findQuery);

            while (rs.next()) {
                resultsearch.add(userRowMapping(rs));
            }
            return resultsearch;

        } catch (SQLException e) {
            log.error("DB connection process issues", e);
//            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }



}
