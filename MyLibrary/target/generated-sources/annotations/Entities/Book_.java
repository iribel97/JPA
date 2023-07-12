package Entities;

import Entities.Author;
import Entities.Editorial;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-07-12T00:38:46", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Book.class)
public class Book_ { 

    public static volatile SingularAttribute<Book, Editorial> editorial;
    public static volatile SingularAttribute<Book, Integer> remaininCopies;
    public static volatile SingularAttribute<Book, Integer> year;
    public static volatile SingularAttribute<Book, Author> author;
    public static volatile SingularAttribute<Book, Long> isbn;
    public static volatile SingularAttribute<Book, Integer> borrowedCopies;
    public static volatile SingularAttribute<Book, Integer> copy;
    public static volatile SingularAttribute<Book, String> title;
    public static volatile SingularAttribute<Book, Boolean> register;

}