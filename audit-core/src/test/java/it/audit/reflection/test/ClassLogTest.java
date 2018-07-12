package it.audit.reflection.test;

import java.util.Date;

import eng.tz.ms.la.annotation.AuditClass;
import eng.tz.ms.la.annotation.AuditConfig;
import eng.tz.ms.la.annotation.AuditField;
import eng.tz.ms.la.annotation.AuditMethod;
import eng.tz.ms.la.annotation.AuditRequestParam;
 

@AuditConfig
@AuditClass
public class ClassLogTest {

	 @AuditField
	private String nome;
	 @AuditField
	private String cognome;
	
	 @AuditField
	private Double eta;
	
	 @AuditField
	private Date data; 
	
	 @AuditField
	 private ClassLogTestField classLogTestField;
	 
	 
	public ClassLogTest() {
		classLogTestField=new ClassLogTestField();	 
	}

	 
	public String testParam(@AuditRequestParam(name="nome") String nome){
 
	return "Hello "+nome;
	}
	
 
 
	@AuditMethod(key="userTest")
	public String getDenominazione(){
	 return nome+" "+cognome;
 }

	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	public void setEta(Double eta) {
		this.eta = eta;
	}
	
	@AuditMethod(method={"getName","getPippo"})
	public ClassLogTestField getTestLog(){
		return new ClassLogTestField();
	}
	
}
