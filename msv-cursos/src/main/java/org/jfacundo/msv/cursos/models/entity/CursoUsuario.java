package org.jfacundo.msv.cursos.models.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table (name = "cursos_usuarios")
@Data
public class CursoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "usuario_id", unique = true)
    private Long usuarioId;

    @Override
    public boolean equals(Object obj){
        if (this == obj){
            return true;
        }
        if (!(obj instanceof CursoUsuario)){
            return false;
        }
        CursoUsuario objeto= (CursoUsuario) obj;
        return usuarioId!=null && usuarioId.equals(objeto.getUsuarioId());
    }
}
