package rekkisoft.trongvu.com.note;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_NewNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        btn_NewNote = findViewById(R.id.btnnewtab);
        btn_NewNote.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnnewtab:
                Intent intent = new Intent(this,NewNoteActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_back,R.anim.anim_exit);

                break;
            default:
                break;
        }
    }
}
