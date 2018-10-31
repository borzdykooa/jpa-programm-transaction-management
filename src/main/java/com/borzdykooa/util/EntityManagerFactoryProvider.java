package com.borzdykooa.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
Класс, отвечающий за создание единственного экземпляра EntityManagerFactory и предоставление глобальной точки доступа к нему
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntityManagerFactoryProvider {

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("JPADemo");

    public static EntityManagerFactory getEntityManagerFactory() {
        return ENTITY_MANAGER_FACTORY;
    }
}
