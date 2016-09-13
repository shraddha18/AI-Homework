package homework1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
public class homework {
	
	static int[][] aMatrix;
	static int size,livetlno;
	static String line,node1,node2,weight;
	static ArrayList nodeslist=new ArrayList<>();
	static int[] myparent={};
	
	public static void BFS(String s,String g){
		myparent=new int[nodeslist.size()];
		boolean[] visited=new boolean[nodeslist.size()];
		Queue q=new LinkedList<>();
		q.add(s);
		int rootindex=nodeslist.indexOf(s);
		myparent[rootindex]=-1;
		visited[rootindex]=true;
		Object goal=(Object)g;
		Object start=(Object)s;
		int count=0,flag=0,p,p1;
		while(!q.isEmpty()||flag==0){
			Object front=q.remove();
			//System.out.println("Front is "+front); //prints the frontmost element
			int frontindex=nodeslist.indexOf(front);
			//System.out.println("Frontindex is "+frontindex); //prints the index from the nodelist	
			//System.out.println(front+" "+count);
			if(front.equals(goal)){
				//System.out.println("Found");
				flag=1;
				break;
			}
			else{
				for(int k=0;k<nodeslist.size();k++){
					if(aMatrix[frontindex][k]==1){
						Object child=nodeslist.get(k);
						//System.out.println(child);
						if(visited[k]==false){
							q.add(child);
							visited[k]=true;
							myparent[k]=frontindex;	
							
						}
						//System.out.println(q);
					}
				}
			}
			/*for(int i=0;i<myparent.length;i++){
				System.out.print(myparent[i]+" ");
			}*/		
			
		}
		
		int find=nodeslist.indexOf(g);
		Object ans1;
		int ans;
		Stack st=new Stack();
		st.push(find);
		while(myparent[find]!=-1){
			find=myparent[find];  //find parent recursively
			st.push(find);
				
		}
		while(!st.isEmpty()){
			ans=(int)st.pop();
			//System.out.println(ans);
			ans1=nodeslist.get(ans);
			System.out.println(ans1+" "+count);
			count++;
		}
		
		
		
		
	}
	
	public static void DFS(String s,String g){
		myparent=new int[nodeslist.size()];
		boolean[] visited=new boolean[nodeslist.size()];
		int count=0;
		Stack ds=new Stack();
		Stack temp=new Stack();
		ds.push(s);
		int rootindex=nodeslist.indexOf(s);
		myparent[rootindex]=-1;
		visited[rootindex]=true;
		Object goal=(Object)g;
		Object start=(Object)s;
		int flag=0;
		while(!ds.isEmpty()||flag==0){
			Object top=ds.pop();
			int topindex=nodeslist.indexOf(top);
			visited[topindex]=true;
			if(top.equals(goal)){
				//System.out.println("Found");
				flag=1;
				break;
			}
			else{
				for(int k=0;k<nodeslist.size();k++){
					if(aMatrix[topindex][k]==1){
						Object child=nodeslist.get(k);
						//System.out.println(child);
						if(visited[k]==false){
							temp.push(child);
							
							myparent[k]=topindex;	
							
						}
						//System.out.println(q);
					}
				}
				Object temp2;
				while(temp.empty()!=true){
					temp2=temp.pop();
					ds.push(temp2);
				}
			}
			/*for(int i=0;i<myparent.length;i++){
				System.out.print(myparent[i]+" ");
			}*/			
			
		}
		
		int find=nodeslist.indexOf(g);
		Object ans1;
		int ans;
		Stack st=new Stack();
		st.push(find);
		while(myparent[find]!=-1){
			find=myparent[find];  //find parent recursively
			st.push(find);
				
		}
		while(!st.isEmpty()){
			ans=(int)st.pop();
			//System.out.println(ans);
			ans1=nodeslist.get(ans);
			System.out.println(ans1+" "+count);
			count++;
		}
		
	}
	
	public void creatematrix() throws IOException{
		
		
	}
	
	public static void main(String args[]) throws IOException{
		
		int stlno=0;
	
		
			FileReader fr=new FileReader("input.txt");
			BufferedReader br=new BufferedReader(fr);
			String type=Files.readAllLines(Paths.get("input.txt")).get(0); //Reads the type of algo
			//System.out.println(type);
			String start=Files.readAllLines(Paths.get("input.txt")).get(1); //Reads the start node
			//System.out.println(start);
			String goal=Files.readAllLines(Paths.get("input.txt")).get(2); //Reads the goal node
			//System.out.println(goal);
			String livetl=Files.readAllLines(Paths.get("input.txt")).get(3); //Reads the no. of live traffic lines
			//System.out.println(livetl);
			 livetlno=Integer.parseInt(livetl.trim());
			//System.out.println(livetlno);
			
			
			for(int i=4;i<=4+livetlno-1;i++){
				String livetrafficlines=Files.readAllLines(Paths.get("input.txt")).get(i);
				//System.out.println(livetrafficlines);	//Reads live traffic line by line
				String[] liveTrafficArray=livetrafficlines.split(" ");
				node1= liveTrafficArray[0];
				node2= liveTrafficArray[1];
				weight= liveTrafficArray[2];
				if(nodeslist.contains(node1)!=true){
					nodeslist.add(node1);	//adds node to list if not present
				}
				
				
				if(nodeslist.contains(node2)!=true){	
					nodeslist.add(node2);	//adds node to list if not present
				}
				
				int n2=nodeslist.indexOf(node2); //node2 index for array
				int n1=nodeslist.indexOf(node1); 
				
					
			}
			
			homework h=new homework();
			//h.creatematrix();
			
				//CREATE ADJACENCY MATRIX
				size=nodeslist.size();	
				aMatrix=new int[size][size];
				for(int i=4;i<=4+livetlno-1;i++){
					String livetrafficlines=Files.readAllLines(Paths.get("input.txt")).get(i);
					//System.out.println(livetrafficlines);	//Reads live traffic line by line
					String[] liveTrafficArray=livetrafficlines.split(" ");
					node1= liveTrafficArray[0];
					node2= liveTrafficArray[1];
					weight= liveTrafficArray[2];
					//System.out.println(nodeslist.indexOf(node1));
					//System.out.println(nodeslist.indexOf(node2));
					int beginNo=nodeslist.indexOf(node1); //index of 1st node
					int endNo=nodeslist.indexOf(node2); //index of second node
					aMatrix[beginNo][endNo]=1;
					//aMatrix[endNo][beginNo]=1;
					
				}
				
			/*for(int i=0;i<aMatrix.length;i++){
				for(int j=0;j<aMatrix.length;j++){
					System.out.print(aMatrix[i][j]);
				}
				System.out.println();
			}*/
			
			//System.out.println(nodeslist);
			
			if(type.equals("BFS")){
				BFS(start,goal);
			}
			if(type.equals("DFS")){
			DFS(start,goal);
			}
			
			
			
			
			/* CODE FOR SUNDAY TRAFFIC
			
			int getsundaytraffic= 4+livetlno; //Line number for sunday traffic
			//System.out.println(getsundaytraffic);
			String sundaytl=Files.readAllLines(Paths.get("input.txt")).get(getsundaytraffic); //Reads the no. of live traffic lines
			//System.out.println(sundaytl);
			int sundaytlno=Integer.parseInt(sundaytl.trim());
			System.out.println(sundaytlno);
			for(int i=getsundaytraffic;i<getsundaytraffic+sundaytlno;i++){
				String sundaytrafficlines=Files.readAllLines(Paths.get("input.txt")).get(i+1); //Reads Sunday traffic lines
				System.out.println(sundaytrafficlines);
				
			}
			 END OF CODE FOR SUNDAY TRAFFIC */
			/*while((line=br.readLine())!=null){
				System.out.println(line);
				
				
			}*/
	
		
		
		
	}

}
class MyNode{
	
	String name;
	boolean explored=false;
	public MyNode(String n){
		this.name=n;
	}
	public String toString(){
		return name;
		
	}
	
}
