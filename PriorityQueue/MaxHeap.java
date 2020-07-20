package PriorityQueue;

public class MaxHeap<T extends Comparable> implements PriorityQueueInterface<T> {
    HeapNode[] arr ;
    int size;
    int timestamp;
    class HeapNode<T>{
        T element;
        int time;
        HeapNode(T element, int time){
            this.element = element;
            this.time=time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public int getTime() {
            return time;
        }

        public T getElement() {
            return element;
        }
    }

    public MaxHeap(){
        arr = new HeapNode[10000];
        size=0;
        timestamp=0;
    }

    private  int leftChild(int a){
        return 2*a+1;
    }
    private  int rightChild(int a){
        return 2*a+2;
    }
    private  int parent(int a){
        return (a-1)/2;
    }

    @Override
    public void insert(T element) {
        timestamp++;
        HeapNode node = new HeapNode(element,timestamp);
        insertHelp(node);
    }
    public void insertHelp(HeapNode node){
        arr[size] = node;
        int index = size;
        while (((T)arr[index].getElement()).compareTo(arr[parent(index)].getElement())>0 ){
            HeapNode temp = arr[parent(index)];
            arr[parent(index)] = arr[index];
            arr[index] = temp;
            index = parent(index);
        }
        size=size+1;
    }
    public void insert(T element, int time){
        HeapNode node = new HeapNode(element,time);
        this.arr[size] = node;
        int index = this.size;
        while (((T)arr[index].getElement()).compareTo(arr[parent(index)].getElement())>=0 && arr[index].getTime()!=arr[parent(index)].getTime() ){
            if(((T)arr[index].getElement()).compareTo(arr[parent(index)].getElement())==0){
                if(arr[index].getTime()<arr[parent(index)].getTime()){
                    HeapNode temp = arr[parent(index)];
                    arr[parent(index)] = arr[index];
                    arr[index] = temp;
                }

                else break;
            }
            else {
                HeapNode temp = arr[parent(index)];
                arr[parent(index)] = arr[index];
                arr[index] = temp;
            }
            index = parent(index);
        }

        this.size++;
    }


    @Override
    public T extractMax() {
        if(size>0) {

            HeapNode removed_;
            HeapNode removed = arr[0];
            removed_ = FiFo(removed);
            arr[0] = arr[size-1];
            heapify(0);
            size--;
            return (T)removed_.getElement();
        }
        else return null;
    }
    public HeapNode FiFo(HeapNode node){
        int min =1000000;
        int indexcalc=0;
        for(int i=0;i<size;i++){
            if(arr[i].getTime()<min && arr[i].getElement().equals(node.getElement())){
                min = arr[i].getTime();
                indexcalc = i;
            }
        }
        HeapNode temp = arr[indexcalc];
        arr[indexcalc] = node;
        return temp;
    }

    public void heapify(int index){
        if(index>=(size-2)/2) return;

        if(((T)arr[index].getElement()).compareTo(arr[leftChild(index)].getElement())<0 || ((T)arr[index].getElement()).compareTo(arr[rightChild(index)].getElement())<0){
            if(((T)arr[leftChild(index)].getElement()).compareTo(arr[rightChild(index)].getElement())>0){
                HeapNode temp = arr[index];
                arr[index] =arr[leftChild(index)];
                arr[leftChild(index)] = temp;
                heapify(leftChild(index));
            }
            else {
                HeapNode temp = arr[index];
                arr[index] = arr[rightChild(index)];
                arr[rightChild(index)] = temp;
                heapify(rightChild(index));
            }
        }
    }

    public int getSize() {
        return size;
    }

}