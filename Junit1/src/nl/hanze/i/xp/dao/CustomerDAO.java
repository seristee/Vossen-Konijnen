package nl.hanze.i.xp.dao;

import java.util.*;
import nl.hanze.i.xp.domain.*;
import nl.hanze.i.xp.exception.*;

public interface CustomerDAO {
	public List<Customer> getAllCustomers() throws NotNegativeException ;
}
