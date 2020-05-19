package services;

import dto.WaiterDto;
import model.Waiter;

/**
 * Created by ch on 2020-05-19
 */
public interface WaiterService extends CrudService<Waiter,Long>{
    Waiter registerNewWaiterAccount(WaiterDto waiterDto);
}
