package digipen.cet3049_cap.model;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "employees")
@Getter
@Setter
@ToString
public class Employees {
    @Id
    @Column(name = "emp_no", length = 11)
    private Long empNo;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "first_name", length = 14)
    private String firstName;

    @Column(name = "last_name",  length = 16)
    private String lastName;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    public enum Gender { M, F }

    @Column(name = "hire_date")
    private LocalDate hireDate;

    // relationship with other tables
    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<DeptEmp> deptEmp;

    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<DeptManager> deptManager;

    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Salaries> salaries;

    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Titles> titles;
}
