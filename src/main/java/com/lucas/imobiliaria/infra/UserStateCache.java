package com.lucas.imobiliaria.infra;

import com.lucas.imobiliaria.model.domain.users.Usuarios;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserStateCache {

    private final Map<String, Usuarios> userStateMap = new ConcurrentHashMap<>();

    public void saveUserState(String username, Usuarios user) {
        userStateMap.put(username, user);
    }

    public Usuarios getUserState(String username) {
        return userStateMap.get(username);
    }

    public void removeUserState(String username) {
        userStateMap.remove(username);
    }
}
