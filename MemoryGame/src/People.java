import java.io.*;
public class People implements Serializable,Comparable{//–Ú¡–ªØ
	String name=null;
	int time=0;
	public People(String name,int t){
		this.name=name;
		time=t;
	}
	public int getTime(){
		return time;
	}
	public String getName(){
		return name;
	}
	public int compareTo(Object b){
		People p=(People)b;
		if((this.time-p.time)==0)
			return 1;
		else
			return(this.time-p.time);
	}

}
