public class ArrayHeapRec<T>{
	Object[] arrheap;
	int last;
	
	public ArrayHeapRec(){this(100);}
	public ArrayHeapRec(int sz){
		arrheap = new Object[sz];
		last=-1; //last data value
	}
	
	public int getSize(){return last+1;}
	
	public void enqueue(T data){
		this.enqueue(data,0);
	}
	
	public void enqueue(T data, int p){
		PriorityBlock<T> pb = new PriorityBlock(data,p);
		
		if(last==arrheap.length-1){
			Object[] temp = new Object[(last+1)*2];
			for(int i=0; i<=last; i++)
				temp[i]=arrheap[i];
			arrheap=temp;
		}
		arrheap[++last] = pb;
		swapUp(last);
	}
	
	private void swapUp(int index){
		if(index==0) return;
		
		int parenInd = (index-1)/2;
		int pPri = ((PriorityBlock<T>)arrheap[parenInd]).getPriority();
		int myPri = ((PriorityBlock<T>)arrheap[index]).getPriority();
		if(pPri<myPri){ //swap!
			 Object temp = arrheap[parenInd];
			 arrheap[parenInd] = arrheap[index];
			 arrheap[index] = temp;
			 swapUp(parenInd);
		}
	}
	
	public T dequeue(){
		T dataToRet = ((PriorityBlock<T>)arrheap[0]).getData();
		arrheap[0] = arrheap[last--];
		
		swapDown(0);
		
		return dataToRet;
	}
	
	private void swapDown(int index){
		if(index>last) return;
		
		int leftInd = 2*index+1;
		int rightInd = 2*index+2;
		
		int myPri = ((PriorityBlock<T>)arrheap[index]).getPriority();
		
		if(leftInd > last) return; //I am a leaf
		if(rightInd>last) { // have to check left only
			int leftPri = ((PriorityBlock<T>)arrheap[leftInd]).getPriority();
			
			if(myPri<leftPri){ //swap with left
				Object temp = arrheap[leftInd];
				arrheap[leftInd] = arrheap[index];
				arrheap[index] = temp;
			}
		} else { //compare Priorities, both exist
			int rightPri = ((PriorityBlock<T>)arrheap[rightInd]).getPriority();
			int leftPri = ((PriorityBlock<T>)arrheap[leftInd]).getPriority();
			
			if(rightPri>leftPri && myPri<rightPri){ //swap with right
				Object temp = arrheap[rightInd];
				arrheap[rightInd] = arrheap[index];
				arrheap[index] = temp;
				swapDown(rightInd);
			}
			if(rightPri<leftPri && myPri<leftPri){ //swap with left
				Object temp = arrheap[leftInd];
				arrheap[leftInd] = arrheap[index];
				arrheap[index] = temp;
				swapDown(leftInd);
			}
			
		}
		
		
		
	}
	
	
	public String toString(){
		String s = "";
		for(int i=0; i<=last; i++)
			s+=arrheap[i]+"\n";
		return s;
	}
	
	
	



	class PriorityBlock<T>{
		T data;
		int priority;
		public PriorityBlock(T d, int p){
			data=d;
			priority=p;
		}
		public T getData(){return data;}
		public int getPriority(){return priority;}
		public void changePriority(int p){
			priority=p;
		}
		public String toString(){
			return data.toString()+" - "+priority;
		}
	}
	
	
	
	public static void main(String[] args){
		ArrayHeapRec<String> ar = new ArrayHeapRec(1);
		ar.enqueue("Hi",5);
		ar.enqueue("Good",6);
		ar.enqueue("test",3);
		ar.enqueue("this",1);
		ar.enqueue("What",5);
		ar.enqueue("Hello",7);
		ar.enqueue("Bye",0);
		ar.enqueue("YAY",2);
		System.out.println("--"+ar.dequeue()+"--");
		System.out.println(ar);
	}

}