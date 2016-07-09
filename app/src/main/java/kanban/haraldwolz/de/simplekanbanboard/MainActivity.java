package kanban.haraldwolz.de.simplekanbanboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.left).setOnDragListener(new DragAndDropDragListener(this)) ;
        findViewById(R.id.middle).setOnDragListener(new DragAndDropDragListener(this)) ;
        findViewById(R.id.right).setOnDragListener(new DragAndDropDragListener(this)) ;


        findViewById(R.id.textView1).setOnTouchListener(new DragAndDropTouchListener());
        findViewById(R.id.textView2).setOnTouchListener(new DragAndDropTouchListener());
        findViewById(R.id.textView3).setOnTouchListener(new DragAndDropTouchListener());
        findViewById(R.id.textView4).setOnTouchListener(new DragAndDropTouchListener());



    }
}
