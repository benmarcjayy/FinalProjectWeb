package ca.sait.services;

import ca.sait.dataaccess.RoleDB;
import ca.sait.models.Role;
import java.util.List;

/**
 *
 * @author Benmarc Agulto
 */
public class RoleService {

    public List<Role> getAll() {
        RoleDB roleDb = new RoleDB();

        return roleDb.getAll();
    }
}
