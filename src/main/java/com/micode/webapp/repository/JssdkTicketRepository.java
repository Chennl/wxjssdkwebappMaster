package com.micode.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.micode.webapp.entity.JssdkTicket;
@Repository
public interface JssdkTicketRepository extends JpaRepository<JssdkTicket, Integer> {

}
