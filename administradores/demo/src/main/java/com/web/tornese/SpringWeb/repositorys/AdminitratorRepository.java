package com.web.tornese.SpringWeb.repositorys;
import com.web.tornese.SpringWeb.models.Administrator;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AdminitratorRepository extends CrudRepository<Administrator, Integer> {
  @Query (value = "SELECT CASE WHEN COUNT(id) THEN 'true' ELSE 'false' END FROM tbl_admisntrators WHERE id = :id", nativeQuery = true)
  public boolean exists(int id);

  @Query (value = "SELECT * FROM tbl_admisntrators WHERE email = :email AND PASSWORD = :password ", nativeQuery = true)
  public Administrator login(String email, String password);

}
