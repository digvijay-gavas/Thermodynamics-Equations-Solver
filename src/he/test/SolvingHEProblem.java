package he.test;

public class SolvingHEProblem {
	public static void main(String[] args) {
		
		double Q, Area, E, NTU, Th1=90, Th2=60,Tc1=20,Tc2,Ch,Cc,mh=10,mc=4.082;
		double Cph=2118, Cpc=4179, dT, dt1, dt2, P, R, u=262, F=0.85, C, Cmin, Cmax;
		
		Ch = mh * Cph;
				
		Cc = mc * Cpc;
		
		Q = Ch * ( Th1 - Th2);
		
		Tc2 = Q / Cc + Tc1;
		
		dt1 = Th1 - Tc2;
		dt2 = Th2 - Tc1;
		dT = ( dt1 - dt2)/Math.log(dt1/dt2);
				
		P = (Tc2 - Tc1)/(Th1 - Tc1);
		R = (Th1 - Th2)/(Tc2 - Tc1);
		
		Area = Q / (u * F * dT);
		System.out.print("Area sqrm LMTD method : ");
		System.out.println(Area);
		
		if (Ch>Cc)
			{Cmin = Cc;
			Cmax =Ch;
			}
		else
		{Cmax = Cc;
		Cmin =Ch;
		}
		
		C = Cmin /Cmax;
		E = (Tc2 - Tc1)/ (Th1 - Tc1);
				
		NTU = (1/(Math.sqrt(1 + C*C)))*Math.log((2-E*(1+C-(Math.sqrt(1 + C*C))))/(2-E*(1+C+(Math.sqrt(1 + C*C)))));
		
		Area = Cmin * NTU / u;
		System.out.print("Area sqrm NTU method :");
		System.out.println(Area);
	}
}
