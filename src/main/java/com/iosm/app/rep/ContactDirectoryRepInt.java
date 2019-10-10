package com.iosm.app.rep;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iosm.app.vo.UserContact;

@Repository
public interface ContactDirectoryRepInt extends JpaRepository<UserContact, Long>{
}




/*
importar  org.springframework.data.jpa.repository.JpaRepository ;
importar  org.springframework.stereotype.Repository ;

import  java.util.List ;

@Repositorio
 interfaz  pública BlogRespository  extiende  JpaRepository < Blog , Integer > {

    // consulta personalizada para buscar publicaciones en el blog por título o contenido
    Lista < Blog >  findByTitleContainingOrContentContaining ( cadena  de texto , Cadena  textAgain );
    
}
 */