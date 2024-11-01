/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package dao;

import beans.Person;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;

/**
 *
 * @author Caroline Casteras
 */
public class DAOPersonTest {
//    int size;
//    DAOPerson dao;
//
//    public DAOPersonTest() {
//        dao = DAOFactory.getDAOPerson();
//    }
//
//    @BeforeAll
//    public static void setUpClass() {
//        System.out.println("Test de la classe DAOPerson");
//    }
//
//    @Test
//    public void testAllCrudMethods() {
//        System.out.println("Test de la méthode count");
//        assertDoesNotThrow(() -> size = dao.count(), "Pas d'exception pour compter les enregistrements");
//        System.out.println("Test de la méthode all");
//        assertDoesNotThrow(() -> dao.all(), "Pas d'exception pour récupérer tous les enregistrements");
//        assertEquals(size, dao.all().size());
//        System.out.println("Test de la méthode find");
//        assertDoesNotThrow(() -> dao.find(1L), "Pas d'exception pour trouver un enregistrement existant");
//        assertNotNull(dao.find(1L), "Trouver le 1er enregistrement");
//        assertNull(dao.find(0L), "Trouver un enregistrement inexistant doit provoquer une erreur");
//        
//        //Test create()
//        System.out.println("Test de la méthode create");
//        Person obj = new Person();
//        obj.setLogin("test");
//        obj.setName("test");
//        obj.setPwd("test");
//        obj.setStatus(0);
//        assertDoesNotThrow(() -> dao.create(obj), "Pas d'exception pour créer un enregistrement");
//        Long id = obj.getId();
//        assertNotNull(id, "Objet créé a un id");
//        assertEquals(size + 1, dao.count(), "Augmentation de la taille de la table");
//        assertNotNull(obj.getId(), "Trouver le nouvel enregistrement");
//        
//        //Test update()
//        System.out.println("Test de la méthode update");
//        obj.setName("toto");
//        assertDoesNotThrow(() -> dao.update(obj), "Pas d'exception pour mettre à jour un enregistrement existant");
//        assertEquals("toto", dao.find(obj.getId()).getName(), "modification persistée");
//        System.out.println("Test de la méthode delete");
//        assertDoesNotThrow(() -> dao.delete(obj), "Suppression d'un enregistrement existant");
//        assertNull(dao.find(id), "enregistrement supprimé introuvable");
//    }
//
//    @Test
//    public void testIsNew() {
//        System.out.println("Test IsNew()");
//        Person person = new Person();
//        assertTrue(DAOFactory.getDAOPerson().isNew(person));
//        person.setId(1L);
//        assertFalse(DAOFactory.getDAOPerson().isNew(person));
//    }
    
}
