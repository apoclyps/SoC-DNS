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

	
	for (int i = 0; i < args.length; i++){
		//printAnswer("SOA: "+args[i], dns.getAnyRecords(args[i], Type.SOA));
		Record answer[]=dns.getAnyRecords(args[i], Type.SOA);
		printAnswer("SOA: "+args[i], answer);
	}		
		

}

}
