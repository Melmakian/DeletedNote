import java.util.ArrayList;
import java.util.List;

public class December1 {
    public static void main(String[] args) throws InterruptedException {
        NoteThread noteThread = new NoteThread();
        noteThread.start();
        noteThread.join();

        for (String string:Note.notes) {
            System.out.println(string);
        }
    }
    public static class Note{
         public static final List<String> notes = new ArrayList<>();
         public static void addNote(String note){
             notes.add(0, note);

         }
         public static void removeNote(String threadName){
             String note = notes.remove(0);
             if (note==null){
                 System.out.println("Another thread deleted our note");
             }else if (!note.startsWith(threadName)){
                 System.out.println("Thread ["+threadName+"] deleted someone else's note ["+note+"]");
             }
         }
    }
    public static class NoteThread extends Thread{
        public NoteThread(){
        }

        @Override
        public void run() {
            int index = 1000;
            for (int i = 0; i < index; i++) {
                Note.addNote(getName()+"-Note"+i);
            }
            Note.removeNote(getName());
        }
    }
}
