package by.test.newidea.repository.jdbctemplate;

import by.test.newidea.domain.User;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static by.test.newidea.repository.UserTableColumns.*;

@Component
public class UserRowMapper implements RowMapper<User> {

    public static final Logger log = Logger.getLogger(UserRowMapper.class);
    @Override
    public User mapRow(ResultSet rs, int i) throws SQLException {



        log.info("User row mapping start");
        User user = new User();

        user.setId(rs.getLong(ID));
        user.setUserName(rs.getString(NAME));
        user.setSurname(rs.getString(SURNAME));
        user.setBirth(rs.getTimestamp(BIRTH_DATE));
        user.setCreateDate(rs.getTimestamp(CREATED));
        user.setModificationDate(rs.getTimestamp(CHANGED));
        user.setIsDeleted(rs.getBoolean(MYDELETED));

        log.info("User row mapping end");
        return user;

    }
}
