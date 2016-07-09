package kanban.haraldwolz.de.simplekanbanboard;

import android.content.ClipData;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by micro on 09.07.2016.
 * Listener for views that will be draged
 */
public class DragAndDropTouchListener implements View.OnTouchListener {
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
            view.startDrag(data, shadowBuilder, view, 0);
            view.setVisibility(View.INVISIBLE);
            return true;
        } else {
            return false;
        }
    }
}