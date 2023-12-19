package com.mipt.reddit.repository;

import com.mipt.reddit.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, String> {
}
