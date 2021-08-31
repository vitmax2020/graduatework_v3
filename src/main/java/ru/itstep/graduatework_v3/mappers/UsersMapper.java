package ru.itstep.graduatework_v3.mappers;

import ru.itstep.graduatework_v3.model.Users;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersMapper  implements RowMapper<Users>{
    public static final String BASE_SQL //
            = "Select u.Id, u.Name, u.Password, Roule, Isactive From Users u ";

    @Override
    public Users mapRow(ResultSet rs, int rowNum) throws SQLException {

        Integer id = rs.getInt("Id");
        String name = rs.getString("Name");
        String password = rs.getString("Password");
        Integer roule = rs.getInt("Roule");
        Integer isactive = rs.getInt("Isactive");

        return new Users(id, name, password, roule, isactive);
    }
}
