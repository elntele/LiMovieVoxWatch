package com.knowtest.limovievoxwatch.model;

public class User {
        private String name;
    private String urlprofessionalNetWork;
    private String senha;
    private String email;
    private String profession;

    public User() {
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlprofessionalNetWork() {
        return urlprofessionalNetWork;
    }

    public void setUrlprofessionalNetWork(String urlprofessionalNetWork) {
        this.urlprofessionalNetWork = urlprofessionalNetWork;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
