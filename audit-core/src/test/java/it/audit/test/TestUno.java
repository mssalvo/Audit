package it.audit.test;
 

import eng.tz.ms.la.core.AuditManager;
import eng.tz.ms.la.model.MetaField;
import eng.tz.ms.la.model.MetaLine;
import eng.tz.ms.la.model.custom.AuditMeta;
import eng.tz.ms.la.model.custom.CustomSettyng;

public class TestUno {

	public static void main(String[] args){
		
		
		
		MetaLine metaLine=new MetaLine(new MetaField("NOME","NICOLA"));
		metaLine
		.addField("VIA","NAZIONE 354",true)
		.addField("CAP", "80056")
		.addField(new MetaField("1","2",Integer.class));
		AuditManager.audit().addInfo("OPERAZIONE","UPLOAD DICHIARAZIONE").setActor("Salvo").laSettyng(CustomSettyng.class)
		.log(metaLine).build(true);
		
		// Result:
		// 11-07-2018 19:59:41.341 Origin==>127.0.0.1 Actor==>Salvo | MetaInfo=[Operazione==>Test1 ], MetaField=[nome==>salvatore ],
		
		
		//OPPURE
		
		AuditManager.audit().addInfo("OPERAZIONE","UPLOAD DICHIARAZIONE").setActor("Giovanni").laSettyng(CustomSettyng.class)
		.addMetaField("Nome", "Salvatore").addMetaField("Cognome", "Mariniello").build();
		// Result:
		//11-07-2018 20:07:08.679 Origin==>127.0.0.1 Actor==>Salvo | MetaInfo=[Operazione==>Test1 ], MetaField=[Nome==>Salvatore Type:java.lang.String, Cognome==>Mariniello Type:java.lang.String], 
		
	
		
		AuditManager.audit().addInfo("OPERAZIONE","UPLOAD DICHIARAZIONE").setActor("Nicola").laSettyng(CustomSettyng.class)
		.addMetaField("Nome", "Salvatore").addMetaField("Cognome", "Mariniello").build();
		// Result:
		//11-07-2018 20:07:08.679 Origin==>127.0.0.1 Actor==>Salvo | MetaInfo=[Operazione==>Test1 ], MetaField=[Nome==>Salvatore Type:java.lang.String, Cognome==>Mariniello Type:java.lang.String], 
		
		
		
		
	} 
	
	
}
