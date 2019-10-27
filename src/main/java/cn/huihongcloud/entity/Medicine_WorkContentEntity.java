package cn.huihongcloud.entity;

public class Medicine_WorkContentEntity {
    private Long id;

    private String name;

    public Medicine_WorkContentEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Medicine_WorkContentEntity() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}