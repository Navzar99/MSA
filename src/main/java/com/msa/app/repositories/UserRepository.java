package com.msa.app.repositories;

import com.msa.app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

// entity that manipulates respective DB table
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
