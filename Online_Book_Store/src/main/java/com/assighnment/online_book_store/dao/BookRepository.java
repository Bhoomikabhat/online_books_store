package com.assighnment.online_book_store.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assighnment.online_book_store.entities.Books;


@Repository
public interface BookRepository extends CrudRepository <Books,Integer>
{
    
}
