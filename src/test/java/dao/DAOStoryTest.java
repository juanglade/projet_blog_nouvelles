//package dao;   //SID
//
//import beans.Story;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.BeforeAll;
//import java.time.LocalDate;
//
///**
// * Classe de test pour DAOStory.
// */
//public class DAOStoryTest {
//
//    int size;
//    DAOStory dao;
//
//    public DAOStoryTest() {
//        dao = DAOFactory.getDAOStory();
//    }
//
//    @BeforeAll
//    public static void setUpClass() {
//        System.out.println("Test classe DAOStory");
//    }
//
//    @Test
//    public void testAllCrudMethods() {
//        System.out.println("Test de la méthode count");
//        assertDoesNotThrow(() -> size = dao.count(), "Pas d'exception pour compter les enregistrements");
//
//        System.out.println("Test de la méthode find");
//        assertDoesNotThrow(() -> dao.find(1L), "Pas d'exception pour trouver un enregistrement existant");
//        assertNotNull(dao.find(1L), "Trouver le 1er enregistrement");
//        assertNull(dao.find(0L), "Trouver un enregistrement inexistant doit retourner null");
//
//        System.out.println("Test de la méthode create");
//        Story story = new Story();
//        story.setTitle("Test Story");
//        story.setContent("Ceci est une histoire de test.");
//        story.setCreated(LocalDate.now());
//        story.setId_person(1L); // Id d'un auteur existant dans la base de données
//        assertDoesNotThrow(() -> dao.create(story), "Pas d'exception pour créer un enregistrement");
//
//        Long id = story.getId();
//        assertNotNull(id, "Objet créé a un id");
//        assertEquals(size + 1, dao.count(), "Augmentation de la taille de la table");
//        assertNotNull(dao.find(id), "Trouver le nouvel enregistrement");
//
//        System.out.println("Test de la méthode update");
//        story.setTitle("Titre modifié");
//        assertDoesNotThrow(() -> dao.update(story), "Pas d'exception pour mettre à jour un enregistrement existant");
//        assertEquals("Titre modifié", dao.find(story.getId()).getTitle(), "modification persistée");
//
//        System.out.println("Test de la méthode delete");
//        assertDoesNotThrow(() -> dao.delete(story), "Suppression d'un enregistrement existant");
//        assertNull(dao.find(id), "Enregistrement supprimé introuvable");
//    }
//
//    @Test
//    public void testIsNew() {
//        System.out.println("testIsNew DAOStory");
//        Story story = new Story();
//        assertTrue(dao.isNew(story), "C'est une nouvelle histoire !");
//        story.setId(1L);
//        assertFalse(dao.isNew(story), "Une histoire avec un ID existant ne doit pas être considérée comme nouvelle");
//    }
//}
