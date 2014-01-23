import java.util.*;
import org.xbill.DNS.*;

public class lookup {

public static void
printAnswer(String name, Record [] answer) {
	System.out.print(name + ":");
	if (answer == null)
		System.out.println("null");
	else
		for (int i = 0; i < answer.length; i++)
			System.out.println(answer[i]);
}

public static void main(String [] args) throws Exception {

	SimpleResolver myResolver= new SimpleResolver();
	try {
		myResolver.setDefaultResolver("134.36.34.8");
		dns.setResolver(myResolver);
	}catch (Exception et){
		System.out.println("Can not set resolver");
		System.out.println("Error: "+et);
		System.exit(-1);
	}
	String SearchPath[] = FindServer.servers();
	
	for (int i=0; i <SearchPath.length; i++){
		System.out.println("Search Path: "+ SearchPath[i]);
	}

	Name Host=null;
	for (int i = 0; i < args.length; i++){
		//printAnswer("SOA: "+args[i], dns.getAnyRecords(args[i], Type.SOA));
		Record answer[]=dns.getAnyRecords(args[i], Type.SOA);
		printAnswer("SOA: "+args[i], answer);
		if (answer != null){
			SOARecord record=(SOARecord)answer[i];
			System.out.println("Admin: "+record.getAdmin() );
			System.out.println("Expire: "+record.getExpire() );
			System.out.println("Primary Host: "+record.getHost() );
			Host=record.getHost();
			String tmp=Host.toString();
			Record answer2[]=dns.getAnyRecords(tmp, Type.A);
			
			if (answer2 != null){
				ARecord record2=(ARecord)answer2[i];
				System.out.println("Host IP: "+record2.getAddress() );
			}
		}
		
	}
	for (int i = 0; i < args.length; i++){
		Record answer[]=dns.getAnyRecords(args[i], Type.A);
		printAnswer("A: "+args[i], answer);
		if (answer != null){
			ARecord record=(ARecord)answer[i];
			System.out.println("IP: "+record.getAddress() );
		}
	}

	for (int i = 0; i < args.length; i++)
		printAnswer("MX: "+args[i], dns.getAnyRecords(args[i], Type.MX));
	for (int i = 0; i < args.length; i++)
		printAnswer("NS: "+args[i], dns.getAnyRecords(args[i], Type.NS));

	for (int i = 0; i < args.length; i++)
		printAnswer("PTR: "+args[i], dns.getAnyRecords(args[i], Type.PTR));
}

}
