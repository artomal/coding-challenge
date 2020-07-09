package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import java.util.List;
public class CircularQueue {

  public static class Queue {

    private Integer[] queue;
    private int tail;
    private int head;
    int elements = 0;

    public Queue(int capacity) {
      this.queue = new Integer[capacity];
      this.tail = this.queue.length - 1;
      this.head = this.tail;
    }

    public void enqueue(Integer x) {
//      printIdx("enqueue");
      if(tail < 0)
        tail = this.queue.length - 1;

      queue[tail--] = x;
      elements++;

      if(queue.length == elements){
        System.out.println("resize up");
        resize();
      }

      return;
    }

    public Integer dequeue() {
//      printIdx("dequeue");
      if(elements < 1)
        return 0;

      if(head < 0)
        head = this.queue.length - 1;

      int elm = queue[head];
      queue[head--] = null;
      elements--;

      if(queue.length / 2 < elements)
        System.out.println("resize down");

      return elm;
    }

    private void resize(){
      Integer[] temp = new Integer[queue.length * 2];

      int idx = temp.length - 1;
      int headIterator = head;
      int iterations = elements;

//      System.out.println("HEAD ITERATOR " + headIterator);
//      System.out.println("TAIL ITERATOR " + tail);
      while(iterations-- > 0){
        if(headIterator < 0)
          headIterator = queue.length - 1;

        Integer elm = queue[headIterator--];
//        System.out.println(" -- COPYING " + elm);
        temp[idx--] = elm;
      }
      this.queue = temp;
      this.head = queue.length - 1;
      this.tail = head - elements;
//      printIdx("resize");
    }

    private void printIdx(String action){
      System.out.println("[" + action + "][ELMENTS] " + elements);
      System.out.println("[" + action + "][HEAD IDX] " + head);
      System.out.println("[" + action + "][TAIL IDX] " + tail);
      printQueue();
    }

    private void printQueue(){
      System.out.print("[");
      for(int i = 0; i < queue.length - 1; i++){
        System.out.print(queue[i] + ", ");
      }
      System.out.print(queue[queue.length-1]);
      System.out.println("]");
    }

    public int size() {
      return this.elements;
    }
    @Override
    public String toString() {
      // TODO - you fill in here.
      return super.toString();
    }
  }
  @EpiUserType(ctorParams = {String.class, int.class})
  public static class QueueOp {
    public String op;
    public int arg;

    public QueueOp(String op, int arg) {
      this.op = op;
      this.arg = arg;
    }

    @Override
    public String toString() {
      return op;
    }
  }

  @EpiTest(testDataFile = "circular_queue.tsv")
  public static void queueTest(List<QueueOp> ops) throws TestFailure {
    Queue q = new Queue(1);
    int opIdx = 0;
    for (QueueOp op : ops) {
      switch (op.op) {
      case "Queue":
        q = new Queue(op.arg);
        break;
      case "enqueue":
        q.enqueue(op.arg);
        break;
      case "dequeue":
        int result = q.dequeue();
        if (result != op.arg) {
          throw new TestFailure()
              .withProperty(TestFailure.PropertyName.STATE, q)
              .withProperty(TestFailure.PropertyName.COMMAND, op)
              .withMismatchInfo(opIdx, op.arg, result);
        }
        break;
      case "size":
        int s = q.size();
        if (s != op.arg) {
          throw new TestFailure()
              .withProperty(TestFailure.PropertyName.STATE, q)
              .withProperty(TestFailure.PropertyName.COMMAND, op)
              .withMismatchInfo(opIdx, op.arg, s);
        }
        break;
      }
      opIdx++;
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "CircularQueue.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
