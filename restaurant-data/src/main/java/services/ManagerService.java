package services;

import dto.ManagerDto;
import model.Manager;

/**
 * Created by ch on 2020-05-19
 */
public interface ManagerService extends CrudService<Manager,Long> {
    Manager registerNewManagerAccount(ManagerDto managerDto);
}
