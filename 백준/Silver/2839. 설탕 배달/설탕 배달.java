import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int kg = 0;
		int answer = -1;
		kg = sc.nextInt();		
		
		if ((kg % 5) % 3 == 0) {
			answer = (kg / 5) + (kg % 5) / 3;
		} else if ((kg % 5) % 3 == 1) {
			if (kg == 4) {
				answer = -1;
			} else { 
				answer = ((kg / 5) + (kg % 5) / 3) + 1;						
			}
		} else {
			if (kg == 7) {
				answer = -1;
			} else {
				answer = (kg / 5) + 2;
			}
		}		
			
		System.out.println(answer);
	}

}