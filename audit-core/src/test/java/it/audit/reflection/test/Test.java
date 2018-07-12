package it.audit.reflection.test;
 

import java.util.Date;

import eng.tz.ms.la.core.AuditManager;
import eng.tz.ms.la.model.MetaField;
import eng.tz.ms.la.model.MetaLine;
import eng.tz.ms.la.model.custom.AuditMeta;
import eng.tz.ms.la.model.custom.CustomSettyng;

public class Test {

	public static void main(String[] args){
		

		ClassLogTest clReflec=new ClassLogTest();
		clReflec.setCognome("Liberto");
		clReflec.setNome("Luca");
		clReflec.setEta(10.0);
		clReflec.setData(new Date());
		
		MetaLine metaLine=new MetaLine(new MetaField("NOME","NICOLA"));
		metaLine
		.addField("VIA","NAZIONE 354",true)
		.addField("CAP", "80056")
		.addField(new MetaField("1",2,Integer.class));
		
		
		AuditManager.audit().addInfo("OPERAZIONE","UPLOAD DICHIARAZIONE").log(clReflec).log(metaLine).setActor("LUCA").laSettyng(CustomSettyng.class)
		.log(metaLine).build(true);
		
		// Result:
		// 11-07-2018 19:59:41.341 Origin==>127.0.0.1 Actor==>Salvo | MetaInfo=[Operazione==>Test1 ], MetaField=[nome==>salvatore ],
		
	 
	 	
		
		
	} 
	
	
}
