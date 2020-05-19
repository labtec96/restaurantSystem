package services;

import dto.CookDto;
import model.Cook;

/**
 * Created by ch on 2020-05-19
 */
public interface CookService extends CrudService<Cook,Long>{
    Cook registerNewCookAccount(CookDto cookDto);
}
