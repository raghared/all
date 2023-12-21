package employee.repository;

import employee.model.Employee;
import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(value = "SELECT e FROM Employee e WHERE e.firstName LIKE %:keyword% OR e.lastName LIKE %:keyword% OR e.email LIKE %:keyword% OR e.salary LIKE %:keyword%")
    List<Employee> search(@Param("keyword") String keyword);
    @Query(value = "SELECT e FROM Employee e WHERE e.email=:email")
    Optional<Employee> getEmail(@Param("email")String email);

    /*@Query(value = "SELECT e FROM Employee e WHERE e.firstName IN :keywords")
    List<Employee> searchByKeywords(@Param("keywords") String keywords);*/

    List<Employee> findByFirstNameContaining(String keyword);

    //@Query("SELECT e FROM Employee e WHERE LOWER(e.firstName) LIKE LOWER(CONCAT('%', ?1, '%'))")
    //@Query(value = "SELECT e FROM Employee e WHERE e.gender LIKE %:keyword% OR e.lastName LIKE %:keyword% OR e.email LIKE %:keyword% OR e.salary LIKE %:keyword%")
    /*@Query("SELECT e FROM Employee e WHERE " +
            "LOWER(e.firstName) LIKE %:keyword% OR " +
            "LOWER(e.lastName) LIKE %:keyword% OR " +
            "LOWER(e.email) LIKE %:keyword% OR " +
            "LOWER(CAST(e.salary AS string)) LIKE %:keyword%")
    List<Employee> findByKeywords(@Param("keyword") String keywords);*/
}
