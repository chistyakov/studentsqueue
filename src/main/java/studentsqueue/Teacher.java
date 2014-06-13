/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studentsqueue;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author teamdevelopment
 */
@Entity
@DiscriminatorValue("T")
@Table(name = "TEACHER")
public class Teacher extends User {
}
