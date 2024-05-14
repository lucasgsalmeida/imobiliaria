package com.lucas.imobiliaria.infra;

import com.lucas.imobiliaria.model.domain.users.Usuario;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserStateCache {

    private final Map<String, Usuario> userStateMap = new ConcurrentHashMap<>();

    public void saveUserState(String username, Usuario user) {
        userStateMap.put(username, user);
    }

    public Usuario getUserState(String username) {
        return userStateMap.get(username);
    }

    public void removeUserState(String username) {
        userStateMap.remove(username);
    }
}
