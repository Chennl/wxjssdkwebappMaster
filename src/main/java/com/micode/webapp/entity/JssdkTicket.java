package com.micode.webapp.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="wxjssdk_ticket",uniqueConstraints = { @UniqueConstraint(columnNames = { "ticket" }) })
@Entity
public class JssdkTicket {	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonProperty("id")
	private Integer id;

	@Column(name="ticket",length=255)
    private String ticket;
	
	@JsonProperty("expires_in")
	@Column(name="expires_in")
    private int expireIn;
	
	@Column(name="errmsg")
	@JsonProperty("errmsg")
    private String errmsg;
	
	@JsonProperty("errcode")
	@Column(name="errcode")
    private int errcode;
	
	@JsonIgnore
	@Column(name="create_date",length=50)
	private String createDate;
 }

//{
//"errcode":0,
//"errmsg":"ok",
//"ticket":"bxLdikRXVbTPdHSM05e5u5sUoXNKdvsdshFKA",
//"expires_in":7200
//}
