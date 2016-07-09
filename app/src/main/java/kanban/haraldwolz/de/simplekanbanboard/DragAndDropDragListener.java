package kanban.haraldwolz.de.simplekanbanboard;

import android.app.Activity;
import android.content.Context;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.io.Serializable;

/**
 * Created by micro on 09.07.2016.
 * Listener for moving views
 */
public class DragAndDropDragListener implements View.OnDragListener {

    Drawable enterShape;
    Drawable normalShape;

    public DragAndDropDragListener(Context context) {
        enterShape = context.getResources().getDrawable(R.drawable.shape_droptarget);
        normalShape = context.getResources().getDrawable(R.drawable.shape);
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        int action = event.getAction();
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                // do nothing
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                v.setBackground(enterShape);
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                v.setBackground(normalShape);
                break;
            case DragEvent.ACTION_DROP:
                // Dropped, reassign View to ViewGroup
                View view = (View) event.getLocalState();
                ViewGroup owner = (ViewGroup) view.getParent();
                owner.removeView(view);
                LinearLayout container = (LinearLayout) v;
                container.addView(view);
                view.setVisibility(View.VISIBLE);
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                v.setBackground(normalShape);
            default:
                break;
        }
        return true;
    }


}
