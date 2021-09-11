package ru.itstep.graduatework_v3.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.itstep.graduatework_v3.model.Profile;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileMapper implements RowMapper<Profile> {
        public Profile mapRow(ResultSet rs, int rowNum) throws SQLException {
            Profile pf = new Profile();
            pf.setProfileId(rs.getInt("ProfileId"));
            pf.setUserId(rs.getInt("UserId"));
            pf.setPhoto(rs.getBytes("Photo"));
            pf.setEmail(rs.getString("email"));
            pf.setSex(rs.getString("sex"));
            pf.setDataReg(rs.getDate ("DataReg"));
            pf.setAge(rs.getDate ("age"));
            return pf;
        }
}
