package com.micode.webapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="wxjssdk_accesstoken",uniqueConstraints = { @UniqueConstraint(columnNames = { "access_token" }) })
@Entity
public class AccessToken {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("access_token")
	@Column(name="access_token",length=255)
    private String accessToken;
	
	@JsonProperty("expires_in")
	@Column(name="expires_in")
    private int expireIn;
	
	@Column(name="errmsg",length=255)
	@JsonProperty("errmsg")
    private String errmsg;
	
	@JsonProperty("errcode")
	@Column(name="errcode")
    private int errcode;
	
	@JsonIgnore
	@Column(name="create_date",length=50)
	private String createDate;
}

 