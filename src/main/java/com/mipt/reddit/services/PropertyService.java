package com.mipt.reddit.services;

import com.mipt.reddit.model.Property;
import com.mipt.reddit.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PropertyService {
    PropertyRepository propertyRepository;

    public boolean getNewPostsAllowed() {
        return propertyRepository
                .findById("new_posts_allowed")
                .map(Property::getValue)
                .map(Boolean::parseBoolean
                ).orElse(true);
    }
}
