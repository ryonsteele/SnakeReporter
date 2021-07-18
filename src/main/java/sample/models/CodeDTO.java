package sample.models;


import javax.persistence.*;

@Entity
@Table(name = "defectcodes")
public class CodeDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "code")
    private String code;
    @Column(name = "code_name")
    private String name;
    @Column(name = "code_desc", length = 10000)
    private String desc;


    public CodeDTO(String code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    public CodeDTO(){}

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
