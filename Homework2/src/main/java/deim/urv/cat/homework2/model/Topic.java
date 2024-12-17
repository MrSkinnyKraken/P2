/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.model;

import java.io.Serializable;

/**
 *
 * @author arnau & rafa
 * 
 */
public class Topic implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Topic)) {
            return false;
        } else{
            Topic other = (Topic) object;
            return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
        }
        
    }

    @Override
    public String toString() {
        return "model.entities.Topic[ id=" + id + " ]";
    }
    
}

