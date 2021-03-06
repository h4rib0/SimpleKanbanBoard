package kanban.haraldwolz.de.simplekanbanboard;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    public static String TAG = "MainActivity";

    private LinearLayout readyColumn;
    private LinearLayout doingColumn;
    private LinearLayout doneColoumn;
    private EditText textInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
    }

    private void setupViews() {
        setupColumns();
        setupTextInput();
        setupAddButton();
        setupUpdateButton();
        setupDeleteButton();
    }

    private void setupColumns() {

        readyColumn = (LinearLayout) findViewById(R.id.ready);
        doingColumn = (LinearLayout) findViewById(R.id.doing);
        doneColoumn = (LinearLayout) findViewById(R.id.done);

        readyColumn.setOnDragListener(new DragAndDropDragListener(this));
        doingColumn.setOnDragListener(new DragAndDropDragListener(this));
        doneColoumn.setOnDragListener(new DragAndDropDragListener(this));
    }

    private void setupTextInput(){
        textInput = (EditText) findViewById(R.id.editTextKanbanCardInput);
    }

    private void setupAddButton() {
        ImageView add = (ImageView) findViewById(R.id.imageViewAdd);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputText = getTextFromInputField();

                if(!TextUtils.isEmpty(inputText)) {
                    addTextViewToReadyColumn(inputText);
                    finishInput();
                }else{
                    showErrorMessageEmptyText();
                }
            }
        });
    }



    private void setupUpdateButton() {
        ImageView update = (ImageView) findViewById(R.id.imageViewUpdate);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateSelectedTextView();
            }
        });
    }

    private void setupDeleteButton() {
        ImageView delete = (ImageView) findViewById(R.id.imageViewDelete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteSelectedTextView();
            }
        });
    }

    private TextView createNewTextView(String text) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setBackgroundResource(R.drawable.shape_textview);

        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        llp.setMargins(5, 5, 5, 5); // llp.setMargins(left, top, right, bottom);

        textView.setLayoutParams(llp);

        textView.setOnTouchListener(new DragAndDropTouchListener());
        return textView;
    }

    private String getTextFromInputField(){
        return textInput.getText().toString();
    }

    private void addTextViewToReadyColumn(String inputText){
        readyColumn.addView(createNewTextView(inputText));
    }

    private void finishInput() {
        textInput.setText("");
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(textInput.getWindowToken(), 0);
    }


    private void updateSelectedTextView() {
        Log.d(TAG, "updateTextView");
    }

    private void deleteSelectedTextView() {
        Log.d(TAG, "deleteTextView");
    }

    private void showErrorMessageEmptyText() {
        Snackbar snackbar = Snackbar.make(findViewById(R.id.editTextKanbanCardInput), R.string.insert_text, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}
