
import java.awt.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import javax.tools.StandardLocation;

//import com.sun.javafx.webkit.KeyCodeMap.Entry;

public class homework{
	static int[][] aMatrix;
	static int size,livetlno;
	static String line,node1,node2,weight;
	static ArrayList nodeslist=new ArrayList<MyNode>();
	static int[] myparent={};
	static LinkedHashMap<String,ArrayList<MyNode>> adjhash;
	static HashMap<String,String> parentnode;
	static HashMap<MyNode,MyNode> parentnodeUCS;
	static HashMap<String,Integer> sundayList;
	static HashMap<MyNode,MyNode> parentnodeDFS;
	static HashMap<String,Integer> visitedDFS;
	static HashMap<String,MyNode> parentnodeAstar;
	static HashMap<Object,Integer> closedAstar;
	
	/*------BFS-----*/
	
	public static void BFS(String s,String g) throws FileNotFoundException{
		parentnode =new HashMap<String,String>();
		boolean[] visited=new boolean[nodeslist.size()];
		Queue q=new LinkedList<>();
		MyNode start=new MyNode(s,0);
		start.visited=true;
		parentnode.put(s,"-1");
		MyNode goal=new MyNode(g,0);
		q.add(start);
		
		Object front=0;
		
		int count=0,flag=0;
		whileloop:
		while(!q.isEmpty()||flag==0){
			 front=q.remove();
			
			MyNode f=new MyNode(front.toString(),0);
			String a=f.name;
			String b=goal.name;
			
			if(a.equals(b)){
				//System.out.println("Found");
				flag=1;
				break whileloop;
				
			}
			else{
				
				
					//System.out.println(adjhash.get(key));
				
				//System.out.println(adjhash.get(front));
				String key=front.toString();
				
				if(adjhash.containsKey(key)){
					for(int j=0;j<adjhash.get(key).size();j++){
						//System.out.print(adjhash.get(key).get(j)+" ");
						//System.out.println(adjhash.get(key).get(j).weight);
						if(adjhash.get(key).get(j).visited==false){
						Object child=adjhash.get(key).get(j);
						q.add(child);
						//System.out.println(parentnode.containsKey(child.toString()));
						if(parentnode.containsKey(child.toString())==false){
						parentnode.put(child.toString(),front.toString());
						}
						adjhash.get(key).get(j).visited=true;
						}
					}
				}
			}
			
				
			
		}
		
		
		
		Object find=parentnode.get(front.toString());
		Object ans1;
		int ans;
		Stack st=new Stack();
		st.push(front);
		while(!find.equals("-1")){
			
			st.push(find);
			find=parentnode.get(find); //find parent recursively
			
				
		}
		System.setOut(new PrintStream(new FileOutputStream("output.txt")));
		while(!st.isEmpty()){
			ans1=st.pop();
			System.out.println(ans1+" "+count);
			count++;
		}
		
		
		
		
	}
	
	/*-------DFS------*/
	
	public static void DFS(String s,String g) throws FileNotFoundException{
		
		parentnodeDFS =new HashMap<MyNode,MyNode>();
		visitedDFS=new HashMap<String,Integer>();
		
		
		for(int i=0;i<nodeslist.size();i++){
			String v=nodeslist.get(i).toString();
			visitedDFS.put(v,0);
		}
		
		boolean[] visited=new boolean[nodeslist.size()];
		//Queue q=new LinkedList<>();
		Stack ds=new Stack();
		Stack temp=new Stack();
		MyNode start=new MyNode(s,0);
		start.visited=true;
		visitedDFS.put(start.toString(),1);
		parentnodeDFS.put(start, null);
		MyNode goal=new MyNode(g,0);
		ds.push(start);
		
		
		
		Object front=0;
		
		int count=0,flag=0;
		whileloop:
		while(!ds.isEmpty()||flag==0){
			 front=ds.pop();
			
			MyNode f=(MyNode)front;
			String a=f.name;
			String b=goal.name;
			
			if(a.equals(g)){
				//System.out.println("Found");
				flag=1;
				break whileloop;
				
			}
			else{
				
				
					//System.out.println(adjhash.get(key));
				
				//System.out.println(adjhash.get(front));
				String key=front.toString();
				
				if(adjhash.containsKey(key)){
					for(int j=0;j<adjhash.get(key).size();j++){
						//System.out.print(adjhash.get(key).get(j)+" ");
						//System.out.println(adjhash.get(key).get(j).weight);
						//System.out.println(adjhash.get(key).get(j).visited);
						if(adjhash.get(key).get(j).visited==false && visitedDFS.get(adjhash.get(key).get(j).name)==0){
						Object child=adjhash.get(key).get(j);
						MyNode c=(MyNode)child;
						c.visited=true;
						temp.push(c);
						//System.out.println(parentnode.containsKey(child.toString()));
						if(parentnodeDFS.containsKey(c)==false && visitedDFS.get(adjhash.get(key).get(j).name)==0){
						parentnodeDFS.put(c,f);
						visitedDFS.put(c.toString(),1);
						}
						adjhash.get(key).get(j).visited=true;
						//System.out.println(adjhash.get(key).get(j).visited);
						}
					}
				}
				Object temp2;
				while(temp.empty()!=true){
					temp2=temp.pop();
					ds.push(temp2);
				}	
			}
			
				
			
		}
		
		
		
		Object find=parentnodeDFS.get(front);
		Object ans1;
		int ans;
		Stack st=new Stack();
		st.push(front);
		while(find!=null){
			
			st.push(find);
			find=parentnodeDFS.get(find); //find parent recursively
			
				
		}
		System.setOut(new PrintStream(new FileOutputStream("output.txt")));
		while(!st.isEmpty()){
			ans1=st.pop();
			System.out.println(ans1+" "+count);
			count++;
		}
		
		
	}
	
	/*-------------UNIFORM COST SEARCH------------*/
	
	public static void UCS(String s,String g) throws FileNotFoundException {
		
		 parentnodeUCS =new HashMap<MyNode,MyNode>();
		Queue<MyNode> open=new PriorityQueue<MyNode>(new Comparator<MyNode>() {
			@Override
			public int compare(MyNode o1, MyNode o2) {
				if(o1.cost<o2.cost){
					return -1;
				}
				else if(o1.cost>o2.cost){
					return 1;
				}
				else
					return o1.timestamp-o2.timestamp;
				
			}
			
		});
		Queue closed=new LinkedList<MyNode>();
		Queue childQ=new LinkedList<MyNode>();
		MyNode start=new MyNode(s,0);
		start.visited=true;
		start.cost=0;
		parentnodeUCS.put(start,null);
		int flag=0,inc=0,count=0;
		MyNode goal=new MyNode(g,0);
		open.add(start);
		start.setTS(inc);
		MyNode front=null,c,n = null;
		whileloop:
			while(!open.isEmpty()||flag==0){
				 front=open.remove();
				 int prevWeight=front.weight;    //to be added
				
				MyNode f=new MyNode(front.toString(),0);
				String a=f.name;
				String b=goal.name;
				
				if(a.equals(b)){
					//System.out.println("Found");
					flag=1;
					break whileloop;
					
				}
				else{
					
					String key=front.toString();
					
					if(adjhash.containsKey(key)){
						for(int j=0;j<adjhash.get(key).size();j++){
							
							if(adjhash.get(key).get(j).visited==false){
							MyNode child=adjhash.get(key).get(j);   

							
							childQ.add(child);  //children <-- Expand(currnode, Operators[problem])
							child.cost=front.cost+child.weight;
							//System.out.println(childQ);
							
							if(parentnodeUCS.containsKey(child)==false){
							parentnodeUCS.put(child,front);
							}
							adjhash.get(key).get(j).visited=true;
							}
						}
							
						
					}
					while(!childQ.isEmpty()){
						Object chu=childQ.remove();
						c=(MyNode)chu;
						
						if(!(open.contains(chu)&&closed.contains(chu))){	//if no node in open or closed has child’s state
							c.setTS(inc);
							inc++;
							open.add(c);
							
						}
						else if(open.contains(chu)){
							if(nodeslist.contains(chu)==true){
								int index1=nodeslist.indexOf(chu);
								 n=(MyNode)nodeslist.get(index1);
							}
							MyNode x=new MyNode();
							int compAns=x.compare(n,c);
							if(compAns>0){
								open.remove(n);
								c.setTS(inc);
								inc++;
								open.add(c);
							}
							 
						}
						else if(closed.contains(chu)){
							if(nodeslist.contains(chu)==true){
								int index1=nodeslist.indexOf(chu);
								 n=(MyNode)nodeslist.get(index1);
							}
							MyNode x=new MyNode();
							int compAns=x.compare(n,c);
							if(compAns>0){
								closed.remove(n);
								c.setTS(inc);
								inc++;
								open.add(c);
								
							}
							
						}
					}
					closed.add(front);
						
				}
				
			}
		
		//System.out.println(adjhash);
		//System.out.println(nodeslist);
		//System.out.println(parentnodeUCS);
		MyNode find=parentnodeUCS.get(front);
		MyNode ans1;
		int ans;
		Stack<MyNode> st=new Stack();
		st.push(front);
		while(find!=null){
			
			st.push(find);
			find=parentnodeUCS.get(find); //find parent recursively
			
				
		}
		System.setOut(new PrintStream(new FileOutputStream("output.txt")));
		while(!st.isEmpty()){
			ans1=st.pop();
			System.out.println(ans1+" "+ans1.cost);
			
		}
		
	}
	
	/*---------- A*------------*/
	
public static void Astar(String s,String g) throws FileNotFoundException{
		
	parentnodeUCS =new HashMap<MyNode,MyNode>();
	Queue<MyNode> open=new PriorityQueue<MyNode>(new Comparator<MyNode>() {
		@Override
		public int compare(MyNode o1, MyNode o2) {
			if(o1.sundaycost<o2.sundaycost){
				return -1;
			}
			else if(o1.sundaycost>o2.sundaycost){
				return 1;
			}
			else
				return o1.timestamp-o2.timestamp;
			
		}
		
	});
	Queue closed=new LinkedList<MyNode>();
	Queue childQ=new LinkedList<MyNode>();
	MyNode start=new MyNode(s,0);
	start.visited=true;
	start.cost=0;
	parentnodeUCS.put(start,null);
	int flag=0,inc=0,count=0;
	MyNode goal=new MyNode(g,0);
	open.add(start);
	start.setTS(inc);
	MyNode front=null,c,n = null;
	whileloop:
		while(!open.isEmpty()||flag==0){
			 front=open.poll();
			 int prevWeight=front.weight;    //to be added
			
			MyNode f=new MyNode(front.toString(),0);
			String a=f.name;
			String b=goal.name;
			
			if(a.equals(b)){
				//System.out.println("Found");
				flag=1;
				break whileloop;
				
			}
			else{
				
				String key=front.toString();
				
				if(adjhash.containsKey(key)){
					for(int j=0;j<adjhash.get(key).size();j++){
						
						if(adjhash.get(key).get(j).visited==false){
						MyNode child=adjhash.get(key).get(j);   

						
						childQ.add(child);  //children <-- Expand(currnode, Operators[problem])
						int sCost= sundayList.get(child.toString());
						int fCost=sundayList.get(front.toString());
						if(parentnodeUCS.get(front)==null){
							child.cost=front.cost+child.weight;
						}
						else{
							child.cost=front.cost+child.weight;
						}
						child.sundaycost=child.cost+sCost;
						//System.out.println(childQ);
						
						if(parentnodeUCS.containsKey(child)==false){
						parentnodeUCS.put(child,front);
						}
						adjhash.get(key).get(j).visited=true;
						}
					}
						
					
				}
				while(!childQ.isEmpty()){
					Object chu=childQ.remove();
					c=(MyNode)chu;
					
					if(!(open.contains(chu)&&closed.contains(chu))){	//if no node in open or closed has child’s state
						c.setTS(inc);
						inc++;
						open.add(c);
						
					}
					else if(open.contains(chu)){
						if(nodeslist.contains(chu)==true){
							int index1=nodeslist.indexOf(chu);
							 n=(MyNode)nodeslist.get(index1);
						}
						MyNode x=new MyNode();
						int compAns=x.compare(n,c);
						if(compAns>0){
							open.remove(n);
							c.setTS(inc);
							inc++;
							open.add(c);
						}
						 
					}
					else if(closed.contains(chu)){
						if(nodeslist.contains(chu)==true){
							int index1=nodeslist.indexOf(chu);
							 n=(MyNode)nodeslist.get(index1);
						}
						MyNode x=new MyNode();
						int compAns=x.compare(n,c);
						if(compAns>0){
							closed.remove(n);
							c.setTS(inc);
							inc++;
							open.add(c);
							
						}
						
					}
				}
				closed.add(front);
					
			}
			
		}
	
	//System.out.println(adjhash);
	//System.out.println(nodeslist);
	//System.out.println(parentnodeUCS);
	MyNode find=parentnodeUCS.get(front);
	MyNode ans1;
	int ans;
	Stack<MyNode> st=new Stack();
	st.push(front);
	while(find!=null){
		
		st.push(find);
		find=parentnodeUCS.get(find); //find parent recursively
		
			
	}
	System.setOut(new PrintStream(new FileOutputStream("output.txt")));
	while(!st.isEmpty()){
		ans1=st.pop();
		System.out.println(ans1+" "+ans1.cost);
		
	}
	
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
			
			
			
				//CREATE ADJACENCY MATRIX
				size=nodeslist.size();	
			//System.out.println(nodeslist);
				
				//USING ARRAY LIST
				adjhash=new LinkedHashMap<String,ArrayList<MyNode>>();
				for(int i=4;i<=4+livetlno-1;i++){
					String livetrafficlines=Files.readAllLines(Paths.get("input.txt")).get(i);
					//System.out.println(livetrafficlines);	//Reads live traffic line by line
					String[] liveTrafficArray=livetrafficlines.split(" ");
					node1= liveTrafficArray[0];
					node2= liveTrafficArray[1];
					weight= liveTrafficArray[2];
					int wt=Integer.parseInt(weight);
					
					if(adjhash.containsKey(node1)){
						adjhash.get(node1).add(new MyNode(node2,wt)); //if its already present
					}
					else{
						ArrayList newlist=new ArrayList<MyNode>();
						newlist.add(new MyNode(node2,wt));
						adjhash.put(node1,newlist);
					}
					
				}
				
				
				/* PRINT THE HASH LIST
				 Set keys=adjhash.keySet();
				for(Iterator i= keys.iterator(); i.hasNext();){
					
					String key=(String)i.next();
					System.out.println(adjhash.get(key));
					for(int j=0;j<adjhash.get(key).size();j++){
						System.out.print(adjhash.get(key).get(j)+" ");
						System.out.println(adjhash.get(key).get(j).weight);
					}
					
				}*/
				
			
			
			
			// CODE FOR SUNDAY TRAFFIC
			sundayList= new HashMap<String,Integer>();
			int getsundaytraffic= 4+livetlno; //Line number for sunday traffic
			//System.out.println(getsundaytraffic);
			String sundaytl=Files.readAllLines(Paths.get("input.txt")).get(getsundaytraffic); //Reads the no. of live traffic lines
			//System.out.println(sundaytl);
			int sundaytlno=Integer.parseInt(sundaytl.trim());
			//System.out.println(sundaytlno);
			for(int i=getsundaytraffic;i<getsundaytraffic+sundaytlno;i++){
				String sundaytrafficlines=Files.readAllLines(Paths.get("input.txt")).get(i+1); //Reads Sunday traffic lines
				//System.out.println(sundaytrafficlines);
				String[] sundayTrafficArray=sundaytrafficlines.split(" ");
				node1= sundayTrafficArray[0];
				//node2= liveTrafficArray[1];
				weight= sundayTrafficArray[1];
				int wt=Integer.parseInt(weight);
				sundayList.put(node1,wt);
				
			}
			//System.out.println(sundayList);
			
			
			if(type.equals("BFS")){
				BFS(start,goal);
			}
			if(type.equals("DFS")){
			DFS(start,goal);
			}
			if(type.equals("UCS")){
			UCS(start,goal);
			}
			if(type.equals("A*")){
				Astar(start,goal);
			}	
			
			 //END OF CODE FOR SUNDAY TRAFFIC 
			
			/*while((line=br.readLine())!=null){
				System.out.println(line);
				
				
			}*/
			
			//System.out.println("This is a test file");
		
		
		
	}


}




class MyNode implements Comparator<MyNode> {
	
	String name;
	int weight;
	boolean visited=false;
	int cost;
	int timestamp;
	int sundaycost;
	public void setSundayCost(int sundaycost){
		this.sundaycost=sundaycost;
	}
	public MyNode(String n,int w){
		this.name=n;
		this.weight=w;
		this.timestamp=0;
	}
	public String toString(){
		return name;
		
	}
	public MyNode(){
		
	}
	
	public void setTS(int timestamp){
		this.timestamp= timestamp;
	}
	
	@Override
	public int compare(MyNode o1, MyNode o2) {
		// TODO Auto-generated method stub
		if(o1.cost<o2.cost){
			return -1;
		}
		else if(o1.cost>o2.cost){
			return 1;
		}
		else
			return o1.timestamp-o2.timestamp;
		
		//return 0;
	}
	
	
}


	

