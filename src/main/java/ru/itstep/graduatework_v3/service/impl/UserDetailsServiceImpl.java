package ru.itstep.graduatework_v3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itstep.graduatework_v3.dao.UsersDao;
import ru.itstep.graduatework_v3.dao.impl.UsersDaoImpl;
import ru.itstep.graduatework_v3.model.Users;
import org.springframework.security.core.userdetails.User;
import ru.itstep.graduatework_v3.service.UsersService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsersService userDAO;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        System.out.println("UserDetails userName   "+userName);
        Users user = this.userDAO.getUsersByName(userName);

        if (user == null) {
            System.out.println("Пользователь не найден! " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }

        System.out.println("Found User: " + user);

        List<String> roleNames = new ArrayList<>();
        Integer roleId = this.userDAO.getUserRole(user.getId());
     //   System.out.println("roleId: "+roleId);
        //TODO т.к. ролей всего две, то решил не заводить для них таблицу, а захардкодить
        if(roleId==1)
            roleNames.add("ROLE_USER");
        else
            roleNames.add("ROLE_ADMIN");
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleNames != null) {
            for (String role : roleNames) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }

        UserDetails userDetails = (UserDetails) new User(user.getName(), //
                user.getPassword(), grantList);

        return userDetails;
    }
}
