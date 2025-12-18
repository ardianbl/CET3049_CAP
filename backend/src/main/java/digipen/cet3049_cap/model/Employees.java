package digipen.cet3049_cap.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

/**
 * JPA entity representing an employee and its relationships to other tables.
 */
@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
public class Employees {
    /** Employee number (primary key) */
    @Id
    @Column(name = "emp_no", length = 11)
    private Long empNo;

    /** Birthdate */
    @Column(name = "birth_date")
    private LocalDate birthDate;

    /** First name */
    @Column(name = "first_name", length = 14)
    private String firstName;

    /** Last name */
    @Column(name = "last_name",  length = 16)
    private String lastName;

    /** Gender enum value */
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    public enum Gender { M, F }

    /** Hire date */
    @Column(name = "hire_date")
    private LocalDate hireDate;

    /** Department assignments */
    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER)
    private List<DeptEmp> deptEmp;

    /** Department manager assignments */
    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER)
    private List<DeptManager> deptManager;

    /** Salary history */
    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER)
    private List<Salaries> salaries;

    /** Title history */
    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER)
    private List<Titles> titles;
}
